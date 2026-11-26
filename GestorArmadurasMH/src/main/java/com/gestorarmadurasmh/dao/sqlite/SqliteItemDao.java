package com.gestorarmadurasmh.dao.sqlite;

import com.gestorarmadurasmh.config.DatabaseConnectionProvider;
import com.gestorarmadurasmh.dao.exception.DaoException;
import com.gestorarmadurasmh.dao.interfaces.ItemDao;
import com.gestorarmadurasmh.domain.enums.Language;
import com.gestorarmadurasmh.domain.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteItemDao implements ItemDao {

    private static final String SELECT_ITEM_BY_ID = """
            SELECT i.id, i.game_id, i.rarity, i.value, i.carry_limit, i.icon_id, i.icon_kind, i.icon_color_id,
                   i.icon_color, ii.name, ii.description
            FROM item i
            JOIN item_i18n ii ON ii.item_id = i.id
            WHERE i.id = ? AND ii.lang = ?
            """;

    private static final String SELECT_ALL_ITEMS = """
            SELECT i.id, i.game_id, i.rarity, i.value, i.carry_limit, i.icon_id, i.icon_kind, i.icon_color_id,
                   i.icon_color, ii.name, ii.description
            FROM item i
            JOIN item_i18n ii ON ii.item_id = i.id
            WHERE ii.lang = ?
            ORDER BY ii.name
            """;

    private final DatabaseConnectionProvider connectionProvider;

    public SqliteItemDao(DatabaseConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public Optional<Item> findById(int id, Language language) {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ITEM_BY_ID)) {
            statement.setInt(1, id);
            statement.setString(2, language.getCode());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapItem(resultSet));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Error al consultar el objeto con id " + id, e);
        }
    }

    @Override
    public List<Item> findAll(Language language) {
        List<Item> items = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ITEMS)) {
            statement.setString(1, language.getCode());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(mapItem(resultSet));
                }
            }
            return items;
        } catch (SQLException e) {
            throw new DaoException("Error al consultar los objetos", e);
        }
    }

    private Item mapItem(ResultSet resultSet) throws SQLException {
        return new Item(
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
    }

    private Integer getNullableInteger(ResultSet resultSet, String column) throws SQLException {
        int value = resultSet.getInt(column);
        return resultSet.wasNull() ? null : value;
    }
}
