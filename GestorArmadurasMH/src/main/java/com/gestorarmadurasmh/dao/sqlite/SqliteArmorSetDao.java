package com.gestorarmadurasmh.dao.sqlite;

import com.gestorarmadurasmh.config.DatabaseConnectionProvider;
import com.gestorarmadurasmh.dao.exception.DaoException;
import com.gestorarmadurasmh.dao.interfaces.ArmorPieceDao;
import com.gestorarmadurasmh.dao.interfaces.ArmorSetDao;
import com.gestorarmadurasmh.domain.enums.BonusKind;
import com.gestorarmadurasmh.domain.enums.Language;
import com.gestorarmadurasmh.domain.enums.SkillKind;
import com.gestorarmadurasmh.domain.model.ArmorSet;
import com.gestorarmadurasmh.domain.model.ArmorSetBonus;
import com.gestorarmadurasmh.domain.model.Skill;
import com.gestorarmadurasmh.domain.model.SkillRank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteArmorSetDao implements ArmorSetDao {

    private static final String SELECT_SET_BY_ID = """
            SELECT s.id, s.game_id, si.name
            FROM armor_set s
            JOIN armor_set_i18n si ON si.armor_set_id = s.id
            WHERE s.id = ? AND si.lang = ?
            """;

    private static final String SELECT_ALL_SETS = """
            SELECT s.id, s.game_id, si.name
            FROM armor_set s
            JOIN armor_set_i18n si ON si.armor_set_id = s.id
            WHERE si.lang = ?
            ORDER BY si.name
            """;

    private static final String SELECT_BONUSES = """
            SELECT asb.armor_set_id, asb.bonus_kind, asb.required_pieces, asb.skill_id, asb.skill_rank_id,
                   s.game_id, s.kind, s.icon_id, s.icon_kind, si.name, si.description,
                   sr.level, sr.set_pieces_required, sri.name AS rank_name, sri.description AS rank_description
            FROM armor_set_bonus asb
            JOIN skill s ON s.id = asb.skill_id
            JOIN skill_i18n si ON si.skill_id = s.id AND si.lang = ?
            JOIN skill_rank sr ON sr.id = asb.skill_rank_id
            JOIN skill_rank_i18n sri ON sri.skill_rank_id = sr.id AND sri.lang = ?
            WHERE asb.armor_set_id = ?
            ORDER BY asb.bonus_kind, asb.required_pieces
            """;

    private final DatabaseConnectionProvider connectionProvider;
    private final ArmorPieceDao armorPieceDao;

    public SqliteArmorSetDao(DatabaseConnectionProvider connectionProvider, ArmorPieceDao armorPieceDao) {
        this.connectionProvider = connectionProvider;
        this.armorPieceDao = armorPieceDao;
    }

    @Override
    public Optional<ArmorSet> findById(int id, Language language) {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_SET_BY_ID)) {
            statement.setInt(1, id);
            statement.setString(2, language.getCode());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapArmorSet(connection, resultSet, language));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Error al consultar el conjunto de armadura con id " + id, e);
        }
    }

    @Override
    public List<ArmorSet> findAll(Language language) {
        List<ArmorSet> sets = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SETS)) {
            statement.setString(1, language.getCode());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    sets.add(mapArmorSet(connection, resultSet, language));
                }
            }
            return sets;
        } catch (SQLException e) {
            throw new DaoException("Error al consultar los conjuntos de armadura", e);
        }
    }

    private ArmorSet mapArmorSet(Connection connection, ResultSet resultSet, Language language) throws SQLException {
        int setId = resultSet.getInt("id");
        return new ArmorSet(
                setId,
                getNullableInteger(resultSet, "game_id"),
                resultSet.getString("name"),
                findBonuses(connection, setId, language),
                armorPieceDao.findByArmorSetId(setId, language)
        );
    }

    private List<ArmorSetBonus> findBonuses(Connection connection, int setId, Language language) throws SQLException {
        List<ArmorSetBonus> bonuses = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BONUSES)) {
            statement.setString(1, language.getCode());
            statement.setString(2, language.getCode());
            statement.setInt(3, setId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    SkillRank skillRank = new SkillRank(
                            resultSet.getInt("skill_rank_id"),
                            resultSet.getInt("skill_id"),
                            resultSet.getInt("level"),
                            getNullableInteger(resultSet, "set_pieces_required"),
                            resultSet.getString("rank_name"),
                            resultSet.getString("rank_description")
                    );
                    Skill skill = new Skill(
                            resultSet.getInt("skill_id"),
                            getNullableInteger(resultSet, "game_id"),
                            SkillKind.valueOf(resultSet.getString("kind").toUpperCase()),
                            getNullableInteger(resultSet, "icon_id"),
                            resultSet.getString("icon_kind"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            List.of(skillRank)
                    );
                    bonuses.add(new ArmorSetBonus(
                            setId,
                            BonusKind.valueOf(resultSet.getString("bonus_kind").toUpperCase()),
                            resultSet.getInt("required_pieces"),
                            skill,
                            skillRank
                    ));
                }
            }
        }
        return bonuses;
    }

    private Integer getNullableInteger(ResultSet resultSet, String column) throws SQLException {
        int value = resultSet.getInt(column);
        return resultSet.wasNull() ? null : value;
    }
}
