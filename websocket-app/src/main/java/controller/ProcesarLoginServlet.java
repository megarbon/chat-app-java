/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import modelo.DatabaseManager;

@WebServlet("/ProcesarLoginServlet")
public class ProcesarLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Verifica las credenciales en la base de datos
        if (DatabaseManager.validateCredentials(username, password)) {
            // Autenticación exitosa
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("LoginExitoso.jsp");
        } else {
            // Autenticación fallida
            String errorMessage = "Nombre de usuario o contraseña incorrectos.";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}
