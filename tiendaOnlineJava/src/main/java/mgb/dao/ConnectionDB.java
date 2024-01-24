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
    public void establishConnection() {
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
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param user El usuario a ser insertado.
     * @return El número de filas afectadas (debería ser 1 si la inserción fue
     * exitosa).
     * @throws SQLException Si hay un error al interactuar con la base de datos.
     */
    public int insertUser(User user) throws SQLException {
        Connection miConexion = null;
        PreparedStatement miStatement = null;

        try {
            miConexion = c; // Usar la conexión establecida en la clase

            String sqlSentence = "INSERT INTO cliente (nombre, correoElectronico, contrasena, apellidos, direccion, codigoPostal, numeroTarjeta, fotoPerfilURL) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            miStatement = miConexion.prepareStatement(sqlSentence);
            miStatement.setString(1, user.getNombre());
            miStatement.setString(2, user.getCorreoElectronico());
            miStatement.setString(3, user.getContrasena());
            miStatement.setString(4, user.getApellidos());
            miStatement.setString(5, user.getDireccion());
            miStatement.setString(6, user.getCodigoPostal());
            miStatement.setString(7, user.getNumeroTarjeta());
            miStatement.setString(8, user.getFotoPerfilURL());

            return miStatement.executeUpdate(); // Retorna un valor positivo si se inserta correctamente

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Lanzar la excepción para que sea manejada por el llamador
        } finally {
            if (miStatement != null) {
                miStatement.close();
            }
            // No cierres la conexión aquí, ya que es gestionada por la clase ConnectionDB
        }
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     *
     * @param userId El ID del usuario a ser eliminado.
     * @return El número de filas afectadas (debería ser 1 si la eliminación fue
     * exitosa).
     * @throws SQLException Si hay un error al interactuar con la base de datos.
     */
    public int deleteUser(int userId) throws SQLException {
        String sqlSentence = "DELETE FROM cliente WHERE idCliente = ?";

        try (PreparedStatement miStatement = c.prepareStatement(sqlSentence)) {
            miStatement.setInt(1, userId);
            return miStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Lanzar la excepción para que sea manejada por el llamador
        }
    }

    /**
     * Recupera un usuario de la base de datos por su ID.
     *
     * @param idCliente El ID del usuario a ser recuperado.
     * @return Un objeto User si se encuentra, o null si no hay resultados.
     * @throws SQLException Si hay un error al interactuar con la base de datos.
     */
    public User getUserById(int idCliente) throws SQLException {
        String sqlQuery = "SELECT * FROM cliente WHERE idCliente = ?";
        try (PreparedStatement preparedStatement = c.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, idCliente);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Aquí, crea un objeto User a partir de los datos en resultSet y devuélvelo
                    User user = new User();
                    user.setIdCliente(resultSet.getInt("idCliente"));
                    // Asigna los demás campos
                    return user;
                } else {
                    return null; // No se encontraron resultados
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // Lanzar la excepción para que sea manejada por el llamador
        }
    }

    /**
     * Recupera un producto de la base de datos por su ID.
     *
     * @param idProducto El ID del producto a ser recuperado.
     * @return Un objeto Product si se encuentra, o null si no hay resultados.
     * @throws SQLException Si hay un error al interactuar con la base de datos.
     */
    public Product getProductById(int idProducto) throws SQLException {
        String sqlQuery = "SELECT * FROM producto WHERE idProducto = ?";
        try (PreparedStatement preparedStatement = c.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, idProducto);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Aquí, crea un objeto Product a partir de los datos en resultSet y devuélvelo
                    Product product = new Product();
                    product.setIdProducto(resultSet.getInt("idProducto"));
                    // Asigna los demás campos
                    return product;
                } else {
                    return null; // No se encontraron resultados
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // Lanzar la excepción para que sea manejada por el llamador
        }
    }

    /**
     * Valida las credenciales de usuario en la base de datos.
     *
     * @param username El nombre de usuario (correo electrónico).
     * @param password La contraseña del usuario.
     * @return true si las credenciales son válidas, false de lo contrario.
     */
    public boolean isValidUserCredentials(String correoElectronico, String contrasena) {
        try {
            String query = "SELECT * FROM cliente WHERE correoElectronico=? AND contrasena=?";

            try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
                preparedStatement.setString(1, correoElectronico);
                preparedStatement.setString(2, contrasena);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            // Aquí podría lanzar una excepción específica o registrar el error de alguna manera.
            e.printStackTrace();
        }
        return false;
    }

}
