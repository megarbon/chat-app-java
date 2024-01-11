package mgb.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mgb.dao.ConnectionDB;
import mgb.model.Product;

/**
 * Servlet para procesar el proceso de compra.
 */
@WebServlet("/ProcessPurchaseServlet")
public class ProcessPurchaseServlet extends HttpServlet {

    private ConnectionDB dbConnection = new ConnectionDB();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Verificar si el usuario ha iniciado sesión
        int idCliente = (int) session.getAttribute("idCliente");
        if (idCliente <= 0) {
            // Si el idCliente no está presente en la sesión, redirige a la página de inicio de sesión
            response.sendRedirect("Login.jsp");
            return;
        }

        // Obtener información del formulario de compra
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        // Obtener el producto de la base de datos
        Product product = dbConnection.getProductById(idProducto);
        

        if (product != null && product.getStock() >= cantidad) {
            // El producto existe y hay suficiente stock

            // Calcular el total de la compra
            double totalCompra = product.getPrecio() * cantidad;

            // Realizar la compra (puedes implementar lógica adicional aquí)

            // Redirigir a una página de éxito
            String successMessage = "Compra exitosa. Total: $" + totalCompra;
            session.setAttribute("successMessage", successMessage);
            response.sendRedirect("PurchaseSuccess.jsp");
        } else {
            // Producto no encontrado o insuficiente stock

            // Redirigir a una página de error
            String errorMessage = "Error al procesar la compra. Verifica la disponibilidad del producto.";
            session.setAttribute("errorMessage", errorMessage);
            response.sendRedirect("PurchaseError.jsp");
        }
    }
}
