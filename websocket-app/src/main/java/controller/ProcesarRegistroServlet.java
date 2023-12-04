/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.DatabaseManager;
import modelo.UserModel;

@WebServlet("/ProcesarRegistroServlet")
public class ProcesarRegistroServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recibe los parámetros del formulario
        String username = request.getParameter("name");
        //String first_name = request.getParameter("nombre");
        //String last_name = request.getParameter("apellidos");
        //String photo_url = request.getParameter("photo_url");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");

        // Genera un UUID para userId
        String userId = UUID.randomUUID().toString();

        // Verifica si la contraseña y la confirmación de contraseña coinciden
        if (!password.equals(passwordConfirm)) {
            // Redirige al usuario a una página de error (contraseña no coincide)
            response.sendRedirect("ErrorRegistro.jsp");
            return; // Sale del método para evitar la ejecución posterior
        }

        // Verifica si el usuario ya existe en la base de datos
        if (DatabaseManager.userExists(username)) {
            // Redirige al usuario a una página de error (usuario duplicado)
            response.sendRedirect("ErrorRegistro.jsp");
            return; // Sale del método para evitar la ejecución posterior
        }

        // Inserta el nuevo usuario en la base de datos
        //UserModel userRegistry = new UserModel(userId, username, first_name, last_name, photo_url, email, password);
        UserModel userRegistry = new UserModel(userId, username, email, password);
        DatabaseManager.insertUser(userRegistry);

        // Almacena los datos del usuario en la sesión
        HttpSession session = request.getSession();
        session.setAttribute("nombre", username);
        //session.setAttribute("apellidos", last_name); // Utilizado last_name para "apellidos"
        //session.setAttribute("photo_url", photo_url);
        session.setAttribute("contraseña", password);
        session.setAttribute("confirmarContraseña", passwordConfirm);

        // Redirige al usuario a la página de registro exitoso
        response.sendRedirect("index.jsp");
    }
}
