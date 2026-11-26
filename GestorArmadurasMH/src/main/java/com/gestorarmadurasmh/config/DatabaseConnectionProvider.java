package com.gestorarmadurasmh.config;

import com.gestorarmadurasmh.dao.exception.DaoException;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionProvider {

    private final Path databasePath;
    private final String url;

    public DatabaseConnectionProvider(Path databasePath) {
        this.databasePath = databasePath;
        this.url = "jdbc:sqlite:" + databasePath.toAbsolutePath();
    }

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(url);
            try (Statement statement = connection.createStatement()) {
                statement.execute("PRAGMA foreign_keys = ON");
            }
            return connection;
        } catch (SQLException e) {
            throw new DaoException("Error al abrir la conexión con la base de datos: " + databasePath.toAbsolutePath(), e);
        }
    }

    public Path getDatabasePath() {
        return databasePath;
    }
}
