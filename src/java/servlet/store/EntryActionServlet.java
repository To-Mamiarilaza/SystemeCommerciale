/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.store;

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
@WebServlet(name = "EntryActionServlet", urlPatterns = {"/entry-action"})
public class EntryActionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int idEntry = Integer.valueOf(request.getParameter("idEntry"));
        if (action.equalsIgnoreCase("confirmer")) {
            try {
                GenericDAO.directUpdate("update entry_order set status = 5 where id_entry_order = " + idEntry, null);
                response.sendRedirect("./entry-order-list");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("refuser")) {
            try {
                GenericDAO.directUpdate("update entry_order set status = 0 where id_entry_order = " + idEntry, null);
                response.sendRedirect("./entry-order-list");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
