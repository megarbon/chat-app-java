package mgb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mgb.model.Product;
import mgb.model.User;

/**
 * Clase que gestiona la conexión y operaciones con la base de datos.
 */
public class ConnectionDB {

    private Connection c;

    /**
     * Constructor que establece la conexión con la base de datos.
     */
    public ConnectionDB() {
        establishConnection();
    }

    /**
     * Establece la conexión con la base de datos.
     */
    private void establishConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            this.c = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3307/e-commerce-esbd",
                    "ecom_user", ""
            );
        } catch (SQLException | ClassNotFoundException ex) {
            this.c = null;
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("conn: " + c);
    }

    /**
     * Ejecuta una sentencia SQL de actualización en la base de datos.
     *
     * @param sqlSentence La sentencia SQL a ejecutar.
     * @param parameters Los parámetros para la sentencia SQL.
     * @return El número de filas afectadas.
     */
    private int executeUpdate(String sqlSentence, Object... parameters) {
        try (PreparedStatement st = c.prepareStatement(sqlSentence)) {
            for (int i = 0; i < parameters.length; i++) {
                st.setObject(i + 1, parameters[i]);
            }
            return st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Ejecuta una consulta SQL y retorna un objeto User.
     *
     * @param sqlQuery La consulta SQL a ejecutar.
     * @return Un objeto User si se encuentra, o null si no hay resultados.
     * @throws SQLException Si ocurre un error durante la ejecución de la
     * consulta.
     */
    private User executeQueryForUser(String sqlQuery, int idCliente) throws SQLException {
        try (PreparedStatement preparedStatement = c.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, idCliente);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? createUserFromResultSet(resultSet) : null;
            }
        }
    }

    /**
     * Crea un objeto User a partir de los resultados de una consulta.
     *
     * @param resultSet Los resultados de la consulta.
     * @return Un objeto User construido a partir de los resultados.
     * @throws SQLException Si ocurre un error al obtener datos del ResultSet.
     */
    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("idCliente"),
                resultSet.getString("nombre"),
                resultSet.getString("correoElectronico"),
                resultSet.getString("contrasena"),
                resultSet.getString("apellidos"),
                resultSet.getString("direccion"),
                resultSet.getString("codigoPostal"),
                resultSet.getString("numeroTarjeta"),
                resultSet.getString("fotoPerfilURL")
        );
    }

    /**
     * Ejecuta una consulta SQL y retorna un objeto Product.
     *
     * @param sqlQuery La consulta SQL a ejecutar.
     * @return Un objeto Product si se encuentra, o null si no hay resultados.
     * @throws SQLException Si ocurre un error durante la ejecución de la
     * consulta.
     */
    private Product executeQueryForProduct(String sqlQuery, int idProducto) throws SQLException {
        try (PreparedStatement preparedStatement = c.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, idProducto);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? createProductFromResultSet(resultSet) : null;
            }
        }
    }

    /**
     * Crea un objeto Product a partir de los resultados de una consulta.
     *
     * @param resultSet Los resultados de la consulta.
     * @return Un objeto Product construido a partir de los resultados.
     * @throws SQLException Si ocurre un error al obtener datos del ResultSet.
     */
    private Product createProductFromResultSet(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getInt("idProducto"),
                resultSet.getString("nombre"),
                resultSet.getString("descripcion"),
                resultSet.getDouble("precio"),
                resultSet.getInt("stock"),
                resultSet.getString("marca"),
                resultSet.getString("categoria")
        );
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param user El usuario a ser insertado.
     * @return El número de filas afectadas (debería ser 1 si la inserción fue
     * exitosa).
     */
    public int insertUser(User user) {
        String sqlSentence = "INSERT INTO cliente (nombre, correoElectronico, contrasena, apellidos, direccion, codigoPostal, numeroTarjeta, fotoPerfilURL) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return executeUpdate(sqlSentence, user.getNombre(), user.getCorreoElectronico(), user.getContrasena(),
                user.getApellidos(), user.getDireccion(), user.getCodigoPostal(), user.getNumeroTarjeta(),
                user.getFotoPerfilURL());
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     *
     * @param userId El ID del usuario a ser eliminado.
     * @return El número de filas afectadas (debería ser 1 si la eliminación fue
     * exitosa).
     */
    public int deleteUser(int userId) {
        String sqlSentence = "DELETE FROM cliente WHERE idCliente = ?";
        return executeUpdate(sqlSentence, userId);
    }

    /**
     * Recupera un usuario de la base de datos por su ID.
     *
     * @param idCliente El ID del usuario a ser recuperado.
     * @return Un objeto User si se encuentra, o null si no hay resultados.
     */
    public User getUserById(int idCliente) {
        String sqlQuery = "SELECT * FROM cliente WHERE idCliente = ?";
        try {
            return executeQueryForUser(sqlQuery, idCliente);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Recupera un producto de la base de datos por su ID.
     *
     * @param idProducto El ID del producto a ser recuperado.
     * @return Un objeto Product si se encuentra, o null si no hay resultados.
     */
    public Product getProductById(int idProducto) {
        String sqlQuery = "SELECT * FROM producto WHERE idProducto = ?";
        try {
            return executeQueryForProduct(sqlQuery, idProducto);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Valida las credenciales de usuario en la base de datos.
     *
     * @param username El nombre de usuario (correo electrónico).
     * @param password La contraseña del usuario.
     * @return true si las credenciales son válidas, false de lo contrario.
     */
    public boolean isValidUserCredentials(String username, String password) {
        try {
            if (c != null && !c.isClosed()) {
                String query = "SELECT * FROM cliente WHERE correoElectronico=? AND contrasena=?";
                try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        return resultSet.next();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
