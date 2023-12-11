/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.store;

import connection.DBConnection;
import generalisation.GenericDAO.GenericDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import model.entry.EntryOrder;
import model.entry.EntryOrderArticle;
import model.purchase.PurchaseOrder;
import service.stock.MovementService;

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
        try {
            Connection connection = DBConnection.getConnection();
            
            EntryOrder entry = (EntryOrder) GenericDAO.findById(EntryOrder.class, idEntry, connection);
            List<EntryOrderArticle> articleEntry = (List<EntryOrderArticle>) GenericDAO.directQuery(EntryOrderArticle.class, "select * from entry_order_articles where id_entry_order = " + entry.getIdEntryOrder(), connection);

            PurchaseOrder purchaseOrder = entry.getReceptionOrder().getDeliveryOrder().getPurchaseOrder();
            purchaseOrder.setAllLineItems(connection);
            
            for (EntryOrderArticle entryOrderArticle : articleEntry) {
                MovementService.entrer(LocalDate.now().toString(), String.valueOf(entry.getIdEntryOrder()), String.valueOf(entryOrderArticle.getArticle().getIdArticle()), String.valueOf(entryOrderArticle.getQuantity()), String.valueOf(purchaseOrder.getUnitPrice(entryOrderArticle.getArticle())), connection);
            }
            
            if (action.equalsIgnoreCase("confirmer")) {
                try {
                    GenericDAO.directUpdate("update entry_order set status = 5 where id_entry_order = " + idEntry, connection);

                    response.sendRedirect("./entry-order-list");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (action.equalsIgnoreCase("refuser")) {
                try {
                    GenericDAO.directUpdate("update entry_order set status = 0 where id_entry_order = " + idEntry, connection);
                    response.sendRedirect("./entry-order-list");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            
            connection.commit();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
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
