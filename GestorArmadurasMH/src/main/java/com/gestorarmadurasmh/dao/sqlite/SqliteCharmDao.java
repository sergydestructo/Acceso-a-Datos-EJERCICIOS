package com.gestorarmadurasmh.dao.sqlite;

import com.gestorarmadurasmh.config.DatabaseConnectionProvider;
import com.gestorarmadurasmh.dao.exception.DaoException;
import com.gestorarmadurasmh.dao.interfaces.CharmDao;
import com.gestorarmadurasmh.domain.enums.Language;
import com.gestorarmadurasmh.domain.model.Charm;
import com.gestorarmadurasmh.domain.model.CharmRank;
import com.gestorarmadurasmh.domain.model.CraftingMaterial;
import com.gestorarmadurasmh.domain.model.Item;
import com.gestorarmadurasmh.domain.model.SkillRank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteCharmDao implements CharmDao {

    private static final String SELECT_CHARM_BY_ID = """
            SELECT id, game_id, randomized
            FROM charm
            WHERE id = ?
            """;

    private static final String SELECT_ALL_CHARMS = """
            SELECT id, game_id, randomized
            FROM charm
            ORDER BY id
            """;

    private static final String SELECT_CHARM_RANKS = """
            SELECT cr.id, cr.charm_id, cr.level, cr.rarity, cr.craftable, cr.crafting_zenny, cri.name, cri.description
            FROM charm_rank cr
            JOIN charm_rank_i18n cri ON cri.charm_rank_id = cr.id
            WHERE cr.charm_id = ? AND cri.lang = ?
            ORDER BY cr.level, cr.id
            """;

    private static final String SELECT_CHARM_RANK_SKILLS = """
            SELECT sr.id, sr.skill_id, sr.level, sr.set_pieces_required, sri.name, sri.description
            FROM charm_rank_skill crs
            JOIN skill_rank sr ON sr.id = crs.skill_rank_id
            JOIN skill_rank_i18n sri ON sri.skill_rank_id = sr.id
            WHERE crs.charm_rank_id = ? AND sri.lang = ?
            ORDER BY sr.skill_id, sr.level
            """;

    private static final String SELECT_CHARM_RANK_MATERIALS = """
            SELECT crcm.quantity, i.id, i.game_id, i.rarity, i.value, i.carry_limit, i.icon_id, i.icon_kind,
                   i.icon_color_id, i.icon_color, ii.name, ii.description
            FROM charm_rank_crafting_material crcm
            JOIN item i ON i.id = crcm.item_id
            JOIN item_i18n ii ON ii.item_id = i.id
            WHERE crcm.charm_rank_id = ? AND ii.lang = ?
            ORDER BY ii.name
            """;

    private final DatabaseConnectionProvider connectionProvider;

    public SqliteCharmDao(DatabaseConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public Optional<Charm> findById(int id, Language language) {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_CHARM_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapCharm(connection, resultSet, language));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Error al consultar el amuleto con id " + id, e);
        }
    }

    @Override
    public List<Charm> findAll(Language language) {
        List<Charm> charms = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CHARMS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                charms.add(mapCharm(connection, resultSet, language));
            }
            return charms;
        } catch (SQLException e) {
            throw new DaoException("Error al consultar los amuletos", e);
        }
    }

    private Charm mapCharm(Connection connection, ResultSet resultSet, Language language) throws SQLException {
        int charmId = resultSet.getInt("id");
        return new Charm(
                charmId,
                getNullableInteger(resultSet, "game_id"),
                resultSet.getInt("randomized") == 1,
                findRanks(connection, charmId, language)
        );
    }

    private List<CharmRank> findRanks(Connection connection, int charmId, Language language) throws SQLException {
        List<CharmRank> ranks = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CHARM_RANKS)) {
            statement.setInt(1, charmId);
            statement.setString(2, language.getCode());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int rankId = resultSet.getInt("id");
                    ranks.add(new CharmRank(
                            rankId,
                            resultSet.getInt("charm_id"),
                            resultSet.getInt("level"),
                            resultSet.getInt("rarity"),
                            resultSet.getInt("craftable") == 1,
                            getNullableInteger(resultSet, "crafting_zenny"),
                            findRankSkills(connection, rankId, language),
                            findRankMaterials(connection, rankId, language),
                            resultSet.getString("name"),
                            resultSet.getString("description")
                    ));
                }
            }
        }
        return ranks;
    }

    private List<SkillRank> findRankSkills(Connection connection, int rankId, Language language) throws SQLException {
        List<SkillRank> skills = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CHARM_RANK_SKILLS)) {
            statement.setInt(1, rankId);
            statement.setString(2, language.getCode());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    skills.add(new SkillRank(
                            resultSet.getInt("id"),
                            resultSet.getInt("skill_id"),
                            resultSet.getInt("level"),
                            getNullableInteger(resultSet, "set_pieces_required"),
                            resultSet.getString("name"),
                            resultSet.getString("description")
                    ));
                }
            }
        }
        return skills;
    }

    private List<CraftingMaterial> findRankMaterials(Connection connection, int rankId, Language language) throws SQLException {
        List<CraftingMaterial> materials = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CHARM_RANK_MATERIALS)) {
            statement.setInt(1, rankId);
            statement.setString(2, language.getCode());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Item item = new Item(
                            resultSet.getInt("id"),
                            getNullableInteger(resultSet, "game_id"),
                            resultSet.getInt("rarity"),
                            resultSet.getInt("value"),
                            resultSet.getInt("carry_limit"),
                            getNullableInteger(resultSet, "icon_id"),
                            resultSet.getString("icon_kind"),
                            getNullableInteger(resultSet, "icon_color_id"),
                            resultSet.getString("icon_color"),
                            resultSet.getString("name"),
                            resultSet.getString("description")
                    );
                    materials.add(new CraftingMaterial(item, resultSet.getInt("quantity")));
                }
            }
        }
        return materials;
    }

    private Integer getNullableInteger(ResultSet resultSet, String column) throws SQLException {
        int value = resultSet.getInt(column);
        return resultSet.wasNull() ? null : value;
    }
}
