package mgb.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mgb.dao.ConnectionDB;
import mgb.model.User;

/**
 * Servlet para procesar el registro de usuarios.
 */
public class ProcessRegisterServlet extends HttpServlet {

    private ConnectionDB dbConnection = new ConnectionDB();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener parámetros del formulario de registro
        String nombre = request.getParameter("nombre");
        String correoElectronico = request.getParameter("correoElectronico");
        String contrasena = request.getParameter("contrasena");
        String apellidos = request.getParameter("apellidos");
        
        // Crear un objeto User con la información del formulario
        User newUser = new User(0, nombre, correoElectronico, contrasena, apellidos);

        // Insertar el nuevo usuario en la base de datos
        int result = dbConnection.insertUser(newUser);

        // Verificar si el registro fue exitoso
        if (result > 0) {
            // Registro exitoso, redirigir a una página de éxito
            HttpSession session = request.getSession();
            session.setAttribute("nombre", nombre);
            response.sendRedirect("index.jsp");
        } else {
            // Error en el registro, redirigir a una página de error
            String errorMessage = "Error al registrar el usuario. Por favor, intenta nuevamente.";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }
    }
}
