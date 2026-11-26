package com.gestorarmadurasmh.dao.sqlite;

import com.gestorarmadurasmh.config.DatabaseConnectionProvider;
import com.gestorarmadurasmh.dao.exception.DaoException;
import com.gestorarmadurasmh.dao.interfaces.SkillDao;
import com.gestorarmadurasmh.domain.enums.Language;
import com.gestorarmadurasmh.domain.enums.SkillKind;
import com.gestorarmadurasmh.domain.model.Skill;
import com.gestorarmadurasmh.domain.model.SkillRank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteSkillDao implements SkillDao {

    private static final String SELECT_SKILL_BY_ID = """
            SELECT s.id, s.game_id, s.kind, s.icon_id, s.icon_kind, si.name, si.description
            FROM skill s
            JOIN skill_i18n si ON si.skill_id = s.id
            WHERE s.id = ? AND si.lang = ?
            """;

    private static final String SELECT_ALL_SKILLS = """
            SELECT s.id, s.game_id, s.kind, s.icon_id, s.icon_kind, si.name, si.description
            FROM skill s
            JOIN skill_i18n si ON si.skill_id = s.id
            WHERE si.lang = ?
            ORDER BY si.name
            """;

    private static final String SELECT_SKILL_RANKS = """
            SELECT sr.id, sr.skill_id, sr.level, sr.set_pieces_required, sri.name, sri.description
            FROM skill_rank sr
            JOIN skill_rank_i18n sri ON sri.skill_rank_id = sr.id
            WHERE sr.skill_id = ? AND sri.lang = ?
            ORDER BY sr.level, sr.id
            """;

    private final DatabaseConnectionProvider connectionProvider;

    public SqliteSkillDao(DatabaseConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public Optional<Skill> findById(int id, Language language) {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_SKILL_BY_ID)) {
            statement.setInt(1, id);
            statement.setString(2, language.getCode());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapSkill(connection, resultSet, language));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Error al consultar la habilidad con id " + id, e);
        }
    }

    @Override
    public List<Skill> findAll(Language language) {
        List<Skill> skills = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SKILLS)) {
            statement.setString(1, language.getCode());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    skills.add(mapSkill(connection, resultSet, language));
                }
            }
            return skills;
        } catch (SQLException e) {
            throw new DaoException("Error al consultar las habilidades", e);
        }
    }

    private Skill mapSkill(Connection connection, ResultSet resultSet, Language language) throws SQLException {
        int skillId = resultSet.getInt("id");
        return new Skill(
                skillId,
                getNullableInteger(resultSet, "game_id"),
                SkillKind.valueOf(resultSet.getString("kind").toUpperCase()),
                getNullableInteger(resultSet, "icon_id"),
                resultSet.getString("icon_kind"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                findRanksBySkillId(connection, skillId, language)
        );
    }

    private List<SkillRank> findRanksBySkillId(Connection connection, int skillId, Language language) throws SQLException {
        List<SkillRank> ranks = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_SKILL_RANKS)) {
            statement.setInt(1, skillId);
            statement.setString(2, language.getCode());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ranks.add(new SkillRank(
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
        return ranks;
    }

    private Integer getNullableInteger(ResultSet resultSet, String column) throws SQLException {
        int value = resultSet.getInt(column);
        return resultSet.wasNull() ? null : value;
    }
}
