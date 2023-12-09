/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.store;

import generalisation.GenericDAO.GenericDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.anomalie.Anomalie;
import model.anomalie.DetailAnomalie;
import model.base.Utilisateur;
import model.entry.EntryOrder;
import model.entry.EntryOrderArticle;

/**
 *
 * @author to
 */
@WebServlet(name = "EntryOrderDetailServlet", urlPatterns = {"/entry-order-detail"})
public class EntryOrderDetailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReceptionListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReceptionListServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            if (utilisateur == null) {
                response.sendRedirect("./login");
            }
            int idEntry = Integer.valueOf(request.getParameter("idEntry"));
            EntryOrder entry = (EntryOrder) GenericDAO.findById(EntryOrder.class, idEntry, null);
            List<EntryOrderArticle> articleEntry = (List<EntryOrderArticle>) GenericDAO.directQuery(EntryOrderArticle.class, "select * from entry_order_articles where id_entry_order = " + entry.getIdEntryOrder(), null);
            List<Anomalie> anomalyDelivery = (List<Anomalie>) GenericDAO.directQuery(Anomalie.class, "select * from anomalie where id_type_anomalie = 1 and id = " + entry.getReceptionOrder().getDeliveryOrder().getIdSupplierDeliveryOrder(), null);
            List<DetailAnomalie> anomalyDeliveryDetails = new ArrayList<>();
            if (!anomalyDelivery.isEmpty()) {
                anomalyDeliveryDetails = (List<DetailAnomalie>) GenericDAO.directQuery(DetailAnomalie.class, "select * from detail_anomalie where id_anomalie = " + anomalyDelivery.get(0).getIdAnomalie(), null);
            }
            request.setAttribute("anomalyDelivery", anomalyDelivery);
            request.setAttribute("anomalyDeliveryDetails", anomalyDeliveryDetails);
            request.setAttribute("entry", entry);
            request.setAttribute("entryArticles", articleEntry);
            request.setAttribute("utilisateur", utilisateur);

            // All required assets
            List<String> css = new ArrayList<>();
            css.add("assets/css/supplier/supplier.css");

            List<String> js = new ArrayList<>();
            js.add("assets/js/bootstrap.bundle.min.js");

            request.setAttribute("css", css);
            request.setAttribute("js", js);

            // Page definition
            request.setAttribute("title", "Insertion bon de livraison");
            request.setAttribute("contentPage", "./pages/store/entryOrderDetail.jsp");

            request.getRequestDispatcher("./template.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
