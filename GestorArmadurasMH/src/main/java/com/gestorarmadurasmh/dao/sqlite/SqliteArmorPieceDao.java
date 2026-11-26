package com.gestorarmadurasmh.dao.sqlite;

import com.gestorarmadurasmh.config.DatabaseConnectionProvider;
import com.gestorarmadurasmh.dao.exception.DaoException;
import com.gestorarmadurasmh.dao.interfaces.ArmorPieceDao;
import com.gestorarmadurasmh.domain.enums.ArmorKind;
import com.gestorarmadurasmh.domain.enums.Language;
import com.gestorarmadurasmh.domain.enums.Rank;
import com.gestorarmadurasmh.domain.model.ArmorPiece;
import com.gestorarmadurasmh.domain.model.CraftingMaterial;
import com.gestorarmadurasmh.domain.model.Item;
import com.gestorarmadurasmh.domain.model.ResistanceType;
import com.gestorarmadurasmh.domain.model.SkillRank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

public class SqliteArmorPieceDao implements ArmorPieceDao {

    private static final String SELECT_PIECE_BY_ID = """
            SELECT ap.id, ap.armor_set_id, ap.kind, ap.rank, ap.rarity, ap.defense_base, ap.defense_max,
                   ap.resist_fire, ap.resist_water, ap.resist_ice, ap.resist_thunder, ap.resist_dragon,
                   ap.crafting_zenny, api.name, api.description
            FROM armor_piece ap
            JOIN armor_piece_i18n api ON api.armor_piece_id = ap.id
            WHERE ap.id = ? AND api.lang = ?
            """;

    private static final String SELECT_ALL_PIECES = """
            SELECT ap.id, ap.armor_set_id, ap.kind, ap.rank, ap.rarity, ap.defense_base, ap.defense_max,
                   ap.resist_fire, ap.resist_water, ap.resist_ice, ap.resist_thunder, ap.resist_dragon,
                   ap.crafting_zenny, api.name, api.description
            FROM armor_piece ap
            JOIN armor_piece_i18n api ON api.armor_piece_id = ap.id
            WHERE api.lang = ?
            ORDER BY api.name
            """;

    private static final String SELECT_PIECES_BY_SET = """
            SELECT ap.id, ap.armor_set_id, ap.kind, ap.rank, ap.rarity, ap.defense_base, ap.defense_max,
                   ap.resist_fire, ap.resist_water, ap.resist_ice, ap.resist_thunder, ap.resist_dragon,
                   ap.crafting_zenny, api.name, api.description
            FROM armor_piece ap
            JOIN armor_piece_i18n api ON api.armor_piece_id = ap.id
            WHERE ap.armor_set_id = ? AND api.lang = ?
            ORDER BY ap.kind, api.name
            """;

    private static final String SELECT_SLOTS = """
            SELECT slot_level
            FROM armor_piece_slot
            WHERE armor_piece_id = ?
            ORDER BY slot_index
            """;

    private static final String SELECT_PIECE_SKILLS = """
            SELECT sr.id, sr.skill_id, sr.level, sr.set_pieces_required, sri.name, sri.description
            FROM armor_piece_skill aps
            JOIN skill_rank sr ON sr.id = aps.skill_rank_id
            JOIN skill_rank_i18n sri ON sri.skill_rank_id = sr.id
            WHERE aps.armor_piece_id = ? AND sri.lang = ?
            ORDER BY sr.skill_id, sr.level
            """;

    private static final String SELECT_CRAFTING_MATERIALS = """
            SELECT acm.quantity, i.id, i.game_id, i.rarity, i.value, i.carry_limit, i.icon_id, i.icon_kind,
                   i.icon_color_id, i.icon_color, ii.name, ii.description
            FROM armor_crafting_material acm
            JOIN item i ON i.id = acm.item_id
            JOIN item_i18n ii ON ii.item_id = i.id
            WHERE acm.armor_piece_id = ? AND ii.lang = ?
            ORDER BY ii.name
            """;

    private final DatabaseConnectionProvider connectionProvider;

    public SqliteArmorPieceDao(DatabaseConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public Optional<ArmorPiece> findById(int id, Language language) {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PIECE_BY_ID)) {
            statement.setInt(1, id);
            statement.setString(2, language.getCode());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapArmorPiece(connection, resultSet, language));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Error al consultar la pieza de armadura con id " + id, e);
        }
    }

    @Override
    public List<ArmorPiece> findAll(Language language) {
        return findPieces(SELECT_ALL_PIECES, statement -> statement.setString(1, language.getCode()), language, "Error al consultar las piezas de armadura");
    }

    @Override
    public List<ArmorPiece> findByArmorSetId(int armorSetId, Language language) {
        return findPieces(SELECT_PIECES_BY_SET, statement -> {
            statement.setInt(1, armorSetId);
            statement.setString(2, language.getCode());
        }, language, "Error al consultar las piezas del conjunto con id " + armorSetId);
    }

    private List<ArmorPiece> findPieces(String sql, StatementConfigurer configurer, Language language, String errorMessage) {
        List<ArmorPiece> pieces = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            configurer.configure(statement);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    pieces.add(mapArmorPiece(connection, resultSet, language));
                }
            }
            return pieces;
        } catch (SQLException e) {
            throw new DaoException(errorMessage, e);
        }
    }

    private ArmorPiece mapArmorPiece(Connection connection, ResultSet resultSet, Language language) throws SQLException {
        int pieceId = resultSet.getInt("id");
        EnumMap<ResistanceType, Integer> resistances = new EnumMap<>(ResistanceType.class);
        resistances.put(ResistanceType.FIRE, resultSet.getInt("resist_fire"));
        resistances.put(ResistanceType.WATER, resultSet.getInt("resist_water"));
        resistances.put(ResistanceType.ICE, resultSet.getInt("resist_ice"));
        resistances.put(ResistanceType.THUNDER, resultSet.getInt("resist_thunder"));
        resistances.put(ResistanceType.DRAGON, resultSet.getInt("resist_dragon"));

        return new ArmorPiece(
                pieceId,
                getNullableInteger(resultSet, "armor_set_id"),
                ArmorKind.valueOf(resultSet.getString("kind").toUpperCase()),
                Rank.valueOf(resultSet.getString("rank").toUpperCase()),
                resultSet.getInt("rarity"),
                resultSet.getInt("defense_base"),
                resultSet.getInt("defense_max"),
                resistances,
                findSlots(connection, pieceId),
                findSkills(connection, pieceId, language),
                getNullableInteger(resultSet, "crafting_zenny"),
                findCraftingMaterials(connection, pieceId, language),
                resultSet.getString("name"),
                resultSet.getString("description")
        );
    }

    private List<Integer> findSlots(Connection connection, int pieceId) throws SQLException {
        List<Integer> slots = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_SLOTS)) {
            statement.setInt(1, pieceId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    slots.add(resultSet.getInt("slot_level"));
                }
            }
        }
        return slots;
    }

    private List<SkillRank> findSkills(Connection connection, int pieceId, Language language) throws SQLException {
        List<SkillRank> skills = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PIECE_SKILLS)) {
            statement.setInt(1, pieceId);
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

    private List<CraftingMaterial> findCraftingMaterials(Connection connection, int pieceId, Language language) throws SQLException {
        List<CraftingMaterial> materials = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CRAFTING_MATERIALS)) {
            statement.setInt(1, pieceId);
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

    @FunctionalInterface
    private interface StatementConfigurer {
        void configure(PreparedStatement statement) throws SQLException;
    }
}
