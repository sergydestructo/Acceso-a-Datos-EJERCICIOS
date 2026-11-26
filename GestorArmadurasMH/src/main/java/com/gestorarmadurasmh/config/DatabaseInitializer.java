package com.gestorarmadurasmh.config;

import com.gestorarmadurasmh.dao.exception.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseInitializer {

    private static final String SCHEMA_RESOURCE = "/db/schema.sql";
    private static final List<String> REQUIRED_TABLES = List.of(
            "skill",
            "skill_i18n",
            "skill_rank",
            "skill_rank_i18n",
            "item",
            "item_i18n",
            "armor_set",
            "armor_set_i18n",
            "armor_set_bonus",
            "armor_piece",
            "armor_piece_i18n",
            "armor_piece_slot",
            "armor_piece_skill",
            "armor_crafting_material",
            "charm",
            "charm_rank",
            "charm_rank_i18n",
            "charm_rank_skill",
            "charm_rank_crafting_material",
            "sync_metadata"
    );

    private final DatabaseConnectionProvider connectionProvider;

    public DatabaseInitializer(DatabaseConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public void initialize() {
        try (Connection connection = connectionProvider.getConnection()) {
            if (!hasRequiredTables(connection)) {
                createSchema(connection);
            }
        } catch (SQLException e) {
            throw new DaoException("Error al inicializar la base de datos", e);
        }
    }

    private boolean hasRequiredTables(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        for (String table : REQUIRED_TABLES) {
            if (!tableExists(metaData, table)) {
                return false;
            }
        }
        return true;
    }

    private boolean tableExists(DatabaseMetaData metaData, String table) throws SQLException {
        try (ResultSet resultSet = metaData.getTables(null, null, table, new String[]{"TABLE"})) {
            if (resultSet.next()) {
                return true;
            }
        }
        try (ResultSet resultSet = metaData.getTables(null, null, table.toUpperCase(), new String[]{"TABLE"})) {
            return resultSet.next();
        }
    }

    private void createSchema(Connection connection) {
        String schemaSql = loadSchema();
        try (Statement statement = connection.createStatement()) {
            statement.execute("PRAGMA foreign_keys = ON");
            for (String sqlStatement : splitStatements(schemaSql)) {
                if (!sqlStatement.isBlank()) {
                    statement.execute(sqlStatement);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error al crear el esquema de la base de datos", e);
        }
    }

    private String loadSchema() {
        try (InputStream inputStream = DatabaseInitializer.class.getResourceAsStream(SCHEMA_RESOURCE)) {
            if (inputStream == null) {
                throw new DaoException("No se encontró el recurso de esquema: " + SCHEMA_RESOURCE);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DaoException("Error al leer el recurso de esquema: " + SCHEMA_RESOURCE, e);
        }
    }

    private List<String> splitStatements(String schemaSql) {
        return List.of(schemaSql.split(";\\s*(?:\\r?\\n|$)"));
    }
}
