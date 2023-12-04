/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author dawmi
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DatabaseManager {

    /**
     * Clase que gestiona la interacción con la base de datos.
     */
    // Atributos de la clase
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/websocketapp";
    private static final String JDBC_USER = "java2";
    private static final String JDBC_PASSWORD = "Garyberto1";

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return Conexión a la base de datos.
     * @throws SQLException Si hay un error al establecer la conexión.
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    /**
     * Cierra la conexión, el PreparedStatement y el ResultSet.
     *
     * @param connection Conexión a la base de datos.
     * @param preparedStatement PreparedStatement a cerrar.
     * @param resultSet ResultSet a cerrar.
     */
    private static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.print(e.toString());
            e.printStackTrace();
        }
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param user Usuario a insertar en la base de datos.
     */
    public static void insertUser(UserModel user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            String query = 
                    "INSERT INTO users (user_id, username, first_name, last_name, photo_url, email, password) VALUES ('5', 'gary', 'aa', 'aass', 'sas.jpg', 'ssss@example.com', 'contraseña123');";
            preparedStatement = connection.prepareStatement(query);
            /* 
            preparedStatement.setString(1, user.getUserId());
            //preparedStatement.setString(2, user.getFirstName());
            //preparedStatement.setString(3, user.getLastName());
            //preparedStatement.setString(4, user.getPhotoUrl());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail()); */

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e.toString());
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    /**
     * Inserta un nuevo mensaje en la base de datos.
     *
     * @param message Mensaje a insertar en la base de datos.
     */
    public static void insertMessage(MessageModel message) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            String query = "INSERT INTO messages (content, timestamp, user_id) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, message.getContent());
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(message.getTimestamp().getTime()));
            preparedStatement.setString(3, message.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e.toString());
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    /**
     * Verifica si un usuario con el nombre de usuario y una contraseña dados ya
     * existen en la base de datos y son pareja.
     *
     * @param username Nombre de usuario a verificar.
     * @param password Contraseña a verificar.
     * @return true si el usuario y la contraseña son correctos, false de lo
     * contrario.
     */
    public static boolean validateCredentials(String username, String password) {
        boolean exists = false;

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    exists = resultSet.next(); // Si hay resultados, las credenciales son válidas
                }
            }
        } catch (SQLException e) {
            System.out.print(e.toString());
            e.printStackTrace(); // Manejo adecuado de las excepciones en una aplicación real
        }

        return exists;
    }

    /**
     * Verifica si un usuario con el nombre de usuario dado ya existe en la base
     * de datos.
     *
     * @param username Nombre de usuario a verificar.
     * @return true si el usuario existe, false de lo contrario.
     */
    public static boolean userExists(String username) {
        boolean exists = false;

        try (Connection connection = getConnection()) {
            String query = "SELECT COUNT(*) FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        exists = (count > 0);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.print(e.toString());
            e.printStackTrace(); // Manejo adecuado de las excepciones en una aplicación real
        }

        return exists;
    }

}
