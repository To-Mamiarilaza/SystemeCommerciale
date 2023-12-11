/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.sale;

import connection.DBConnection;
import generalisation.GenericDAO.GenericDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.article.Article;
import model.base.Utilisateur;
import model.purchase.ArticleQuantity;
import model.purchase.PaymentMethod;
import model.purchaseClient.ArticleOrder;
import model.purchaseClient.PurchaseOrderClient;
import service.stock.StockService;

/**
 *
 * @author to
 */
@WebServlet(name = "ClientPurchaseOrderInsertionServlet", urlPatterns = {"/client-purchase-order-insertion"})
public class ClientPurchaseOrderInsertionServlet extends HttpServlet {

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
            request.setAttribute("utilisateur", utilisateur);
            
            List<PaymentMethod> paymentMethods = (List<PaymentMethod>) GenericDAO.getAll(PaymentMethod.class, null, null);
            request.setAttribute("paymentMethods", paymentMethods);
            List<Article> articles = (List<Article>) GenericDAO.getAll(Article.class, null, null);
            HttpSession session = request.getSession();
            request.setAttribute("articles", articles);
            PurchaseOrderClient purchaseOrderClient = new PurchaseOrderClient();
            session.setAttribute("purchaseOrderClient", purchaseOrderClient);
            
            // All required assets
            List<String> css = new ArrayList<>();
            css.add("assets/css/supplier/supplier.css");
            
            List<String> js = new ArrayList<>();
            js.add("assets/js/bootstrap.bundle.min.js");
            js.add("assets/js/sale/purchaseOrderClient.js");
            
            request.setAttribute("css", css);
            request.setAttribute("js", js);
            
            // Page definition
            request.setAttribute("title", "Insertion bon de livraison");
            request.setAttribute("contentPage", "./pages/sale/purchaseOrderInsertion.jsp");
            
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
            System.out.println("Tonga");
            String reference = request.getParameter("reference");
            String date = request.getParameter("date");
            String nomClient = request.getParameter("clientName");
            String adress = request.getParameter("adress");
            String contactDelivery = request.getParameter("contactDelivery");
            String deliveryDate = request.getParameter("deliveryDate");
            String methodPayment = request.getParameter("methodPayment");
            
            HttpSession session = request.getSession();
            PurchaseOrderClient purchaseOrderClient = (PurchaseOrderClient)session.getAttribute("purchaseOrderClient");
            purchaseOrderClient.setReference(reference);
            purchaseOrderClient.setDateInsertion(date);
            purchaseOrderClient.setClientName(nomClient);
            purchaseOrderClient.setAdresse(adress);
            purchaseOrderClient.setContactDelivery(contactDelivery);
            purchaseOrderClient.setDeliveryDate(deliveryDate);
            purchaseOrderClient.setPaymentMethod(methodPayment);
            purchaseOrderClient.setStatus(5);
            
            // Vérification des stock
            Connection connection = DBConnection.getConnection();
            for (ArticleOrder articleOrder : purchaseOrderClient.getArticleOrder()) {
                ArticleQuantity articleQuantity = new ArticleQuantity();
                articleQuantity.setArticle(articleOrder.getArticle());
                articleQuantity.setQuantity(articleOrder.getQuantity());
                System.out.println("VERIFICATION QUANTITE : ");
                System.out.println("- " + articleOrder.getArticle());
                System.out.println("- " + articleOrder.getQuantity());
                
                StockService.checkStock(articleQuantity, connection);
            }
            
            //Sauvegarder
            purchaseOrderClient.save(purchaseOrderClient, connection);
            
            connection.commit();
            connection.close();
            
            response.sendRedirect("./client-purchase-order-list");
        } catch(Exception e) {
            request.setAttribute("error", e.getMessage());
            e.printStackTrace();
            doGet(request, response);
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
