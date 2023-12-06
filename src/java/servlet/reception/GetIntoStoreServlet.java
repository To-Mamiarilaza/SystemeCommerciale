/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.reception;

import generalisation.GenericDAO.GenericDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "GetIntoStoreServlet", urlPatterns = {"/get-into-store"})
public class GetIntoStoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idDelivery = Integer.valueOf(request.getParameter("idDelivery"));
            int idReception = Integer.valueOf(request.getParameter("idReception"));

            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("valider")) {
                GenericDAO.directUpdate("update supplier_delivery_order set status = 2 where id_supplier_delivery_order = " + idDelivery, null);
                GenericDAO.directUpdate("update reception_order set status = 2 where id_reception_order = " + idReception, null);
            } else if (action.equalsIgnoreCase("rejeter")) {
                GenericDAO.directUpdate("update supplier_delivery_order set status = 0 where id_supplier_delivery_order = " + idDelivery, null);
                GenericDAO.directUpdate("update reception_order set status = 0 where id_reception_order = " + idReception, null);
            }

            response.sendRedirect("./reception-list");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
