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

    private String url;
    private String username;
    private String password;
    private String driver;

    // Instancia única de la conexión (Singleton)
    private static Connection instance;

    public DatabaseConfig(@Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password,
            @Value("${spring.datasource.driver-class-name}") String driver) throws ClassNotFoundException {
        this.username = username;
        this.password = password;
        this.url = url;
        this.driver = driver;
        Class.forName(driver);
    }

    /**
     * Método para obtener la instancia única de la conexión.
     * 
     * @return instancia única de Connection.
     */
    public static synchronized Connection getInstance(DatabaseConfig config) throws SQLException {
        if (instance == null || instance.isClosed()) {
            instance = DriverManager.getConnection(config.url, config.username, config.password);
            System.out.println("Conexión exitosa a la base de datos: " + config.url + " con el driver: " + config.driver);
        }
        return instance;
    }

    private void prepareStatement(PreparedStatement statement, QueryParam... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            if (params[i].object_class.equals("string")) {
                statement.setString(i + 1, (String) params[i].param_string);
            } else if (params[i].object_class.equals("int")) {
                statement.setInt(i + 1, (int) params[i].param_int);
            } else if (params[i].object_class.equals("long")) {
                statement.setLong(i + 1, (long) params[i].param_long);
            } else if (params[i].object_class.equals("boolean")) {
                statement.setBoolean(i + 1, (boolean) params[i].param_boolean);
            } else if (params[i].object_class.equals("date")) {
                statement.setDate(i + 1, new java.sql.Date(params[i].param_date.getTime()));
            }
        }
    }

    /**
     * Ejecutar una consulta SELECT.
     * 
     * @param query  la consulta SQL.
     * @param params los parámetros de la consulta.
     * @return el ResultSet con los resultados.
     * @throws SQLException si ocurre un error al ejecutar la consulta.
     */
    public ResultSet executeQuery(String query, QueryParam... params) throws SQLException {
        PreparedStatement statement = getInstance(this).prepareStatement(query);
        prepareStatement(statement, params);        
        System.out.println("Ejecutando consulta: " + statement.toString());
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
    public long executeUpdate(String query, QueryParam... params) throws SQLException {

        PreparedStatement statement = getInstance(this).prepareStatement(query);
        prepareStatement(statement, params);
        System.out.println("Ejecutando consulta: " + statement.toString());
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
