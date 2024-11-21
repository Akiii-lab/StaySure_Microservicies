package StaySure.DataBase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    // Instancia única de la conexión (Singleton)
    private static Connection instance;

    public DatabaseConfig() {
        // springboot inyecta los valores
    }

    /**
     * Método para obtener la instancia única de la conexión.
     * 
     * @return instancia única de Connection.
     */
    public static synchronized Connection getInstance(DatabaseConfig config) throws SQLException {
        if (instance == null || instance.isClosed()) {
            instance = DriverManager.getConnection(config.url, config.username, config.password);
            System.out.println("Conexión exitosa a PostgreSQL.");
        }
        return instance;
    }

    /**
     * Ejecutar una consulta SELECT.
     * 
     * @param query  la consulta SQL.
     * @param params los parámetros de la consulta.
     * @return el ResultSet con los resultados.
     * @throws SQLException si ocurre un error al ejecutar la consulta.
     */
    public ResultSet executeQuery(String query, Object... params) throws SQLException {
        PreparedStatement statement = getInstance(this).prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        return statement.executeQuery();
    }

    /**
     * Ejecutar una operación INSERT, UPDATE o DELETE.
     * 
     * @param query  la consulta SQL.
     * @param params los parámetros de la consulta.
     * @return el número de filas afectadas o la id si es una operación INSERT.
     * @throws SQLException si ocurre un error al ejecutar la consulta.
     */
    public long executeUpdate(String query, Object... params) throws SQLException {
        PreparedStatement statement = getInstance(this).prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0 && query.toLowerCase().startsWith("insert")) {
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        }

        return rowsAffected;
    }

    /**
     * Cerrar la conexión a la base de datos.
     */
    public void close() {
        try {
            if (instance != null && !instance.isClosed()) {
                instance.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
