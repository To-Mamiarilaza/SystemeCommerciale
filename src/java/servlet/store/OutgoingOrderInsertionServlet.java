package servlet.store;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import connection.DBConnection;
import generalisation.GenericDAO.GenericDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.article.Article;
import model.base.Utilisateur;
import model.movement.out.OutgoingOrder;
import model.movement.out.ServiceRequest;
import model.purchaseClient.PurchaseOrderClient;
import service.movement.out.OutgoingOrderService;
import service.movement.out.ServiceRequestService;

/**
 *
 * @author to
 */
@WebServlet(urlPatterns = {"/outgoing-order-insertion"})
public class OutgoingOrderInsertionServlet extends HttpServlet {

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
            out.println("<title>Servlet OutgoingRequestListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OutgoingRequestListServlet at " + request.getContextPath() + "</h1>");
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
            request.setAttribute("utilisateur", utilisateur);
            
            // All required informations
            String idService = request.getParameter("idService");
            String idPurchase = request.getParameter("idPurchase");
            
            Connection connection = DBConnection.getConnection();
            
            OutgoingOrder order = null;
            if(idService != null) {
                ServiceRequest serviceRequest = ServiceRequestService.getServiceRequest(idService, connection);
                order = new OutgoingOrder(serviceRequest);
            } else {
                PurchaseOrderClient purchase = GenericDAO.findById(PurchaseOrderClient.class, idPurchase, connection);
                purchase.loadArticleQuantityOrder(connection);
                order = new OutgoingOrder(purchase);
            }
            
            order.showDetails();
            request.getSession().setAttribute("outgoingOrder", order);
            request.setAttribute("outgoingOrder", order);
            List<Article> articleList = (List<Article>) GenericDAO.getAll(Article.class, "WHERE status = 1", connection);
            request.setAttribute("articleList", articleList);
            
            // All required assets
            List<String> css = new ArrayList<>();
            css.add("assets/css/supplier/supplier.css");
            
            List<String> js = new ArrayList<>();
            js.add("assets/js/store/outgoing-order.js");
            js.add("assets/js/bootstrap.bundle.min.js");
            
            request.setAttribute("css", css);
            request.setAttribute("js", js);
            
            // Page definition
            request.setAttribute("title", "Bon de sortie");
            request.setAttribute("contentPage", "./pages/store/outgoingOrderInsertion.jsp");
            
            request.getRequestDispatcher("./template.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            LocalDate date = LocalDate.parse(request.getParameter("date"));
            String responsableName = request.getParameter("responsableName");
            String responsableContact = request.getParameter("responsableContact");
            String motif = request.getParameter("motif");
            
            OutgoingOrder outgoingOrder = (OutgoingOrder) request.getSession().getAttribute("outgoingOrder");
            outgoingOrder.setDate(date);
            outgoingOrder.setResponsableName(responsableName);
            outgoingOrder.setResponsableContact(responsableContact);
            outgoingOrder.setMotif(motif);
            
            OutgoingOrderService.saveOutgoingOrder(outgoingOrder);
            
            response.sendRedirect("./outgoing-order-list");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
