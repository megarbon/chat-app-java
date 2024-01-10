package mgb.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mgb.dao.ConnectionDB;

/**
 * Servlet para procesar el inicio de sesión de usuarios.
 */
public class ProcessLoginServlet extends HttpServlet {

    private ConnectionDB dbConnection = new ConnectionDB();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correoElectronico = request.getParameter("correoElectronico"); // Cambiado para reflejar el cambio en el formulario de inicio de sesión
        String contrasena = request.getParameter("contrasena"); // Cambiado para reflejar el cambio en el formulario de inicio de sesión

        // Verifica si las credenciales son válidas en la base de datos
        if (dbConnection.isValidUserCredentials(correoElectronico, contrasena)) {
            // Autenticación exitosa
            HttpSession session = request.getSession();
            session.setAttribute("correoElectronico", correoElectronico); // Cambiado para reflejar el cambio en la sesión
            response.sendRedirect("LoginExitoso.jsp");
        } else {
            // Autenticación fallida
            String errorMessage = "Correo electrónico o contraseña incorrectos.";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
        }
    }
}
