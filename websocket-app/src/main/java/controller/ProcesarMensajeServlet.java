/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.UUID;
import modelo.DatabaseManager;
import modelo.MessageModel;

@WebServlet("/ProcesarMensajeServlet")
public class ProcesarMensajeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recibe los parámetros del formulario
        String userId = request.getParameter("userId");
        String content = request.getParameter("content");
        
        // Genera un UUID para messageId
        String messageId = UUID.randomUUID().toString();

        // Obtén la fecha actual como timestamp
        Date timestamp = new Date();

        // Crea un nuevo mensaje
        MessageModel message = new MessageModel(messageId, content, timestamp, userId);

        // Inserta el mensaje en la base de datos
        DatabaseManager.insertMessage(message);

        // Almacena el mensaje en la sesión (si es necesario)
        HttpSession session = request.getSession();
        session.setAttribute("lastMessage", message);

        // Redirige al usuario a la página de envío de mensajes exitoso
        response.sendRedirect("MensajeExitoso.jsp");
    }
}
