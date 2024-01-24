<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Clientes</title>
</head>
<body>

<%
    // Variables para la conexión a la base de datos
    String JDBC_URL = "jdbc:mariadb://localhost:3307/e-commerce-esbd";
    String JDBC_USER = "ecom_user";
    String JDBC_PASSWORD = "";

    try {
        // Establecer la conexión a la base de datos
        Class.forName("org.mariadb.jdbc.Driver");
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

        if (connection != null) {
            // Obtener datos de la tabla "cliente"
            String query = "SELECT * FROM cliente";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
%>
            <h2>Lista de Clientes</h2>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Correo Electrónico</th>
                    <!-- Agrega más columnas según tus necesidades -->
                </tr>
<%
            // Iterar sobre los resultados y mostrar en la tabla
            while (resultSet.next()) {
                int idCliente = resultSet.getInt("idCliente");
                String nombre = resultSet.getString("nombre");
                String correoElectronico = resultSet.getString("correoElectronico");
                // Puedes agregar más campos aquí

%>
                <tr>
                    <td><%= idCliente %></td>
                    <td><%= nombre %></td>
                    <td><%= correoElectronico %></td>
                    <!-- Agrega más celdas según tus necesidades -->
                </tr>
<%
            }
%>
            </table>
<%
            // Cerrar recursos
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } else {
%>
            <p>Error en la conexión a la base de datos.</p>
<%
        }
    } catch (Exception e) {
        e.printStackTrace();
%>
        <p>Error: <%= e.getMessage() %></p>
<%
    }
%>

</body>
</html>
