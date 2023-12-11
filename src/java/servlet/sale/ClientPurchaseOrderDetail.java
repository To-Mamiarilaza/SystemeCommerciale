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
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.base.Utilisateur;
import model.purchaseClient.ArticleOrder;
import model.purchaseClient.PurchaseOrderClient;
import model.sale.ArticleQuantitySale;
import service.sale.SaleService;
import service.stock.StockService;

/**
 *
 * @author to
 */
@WebServlet(name = "ClientPurchaseOrderDetail", urlPatterns = {"/client-purchase-order-detail"})
public class ClientPurchaseOrderDetail extends HttpServlet {

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
            out.println("<title>Servlet ClientPurchaseOrderDetail</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClientPurchaseOrderDetail at " + request.getContextPath() + "</h1>");
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

            String idPurchaseOrderClient = request.getParameter("idPurchaseOrderClient");
            PurchaseOrderClient purchaseOrderClient = GenericDAO.findById(PurchaseOrderClient.class, Integer.valueOf(idPurchaseOrderClient), null);
            List<ArticleOrder> articleQuantityOrder = (List<ArticleOrder>) GenericDAO.directQuery(ArticleOrder.class, "SELECT * FROM article_quantity_order WHERE id_purchase_order="+idPurchaseOrderClient, null);
            purchaseOrderClient.setArticleOrder(articleQuantityOrder);
            
            Connection connection = DBConnection.getConnection();
            double price = 0;
            
            for (ArticleOrder article : articleQuantityOrder) {
                article.setAvailableQuantity(StockService.getRemainQuantity(article.getArticle(), connection));
                price = SaleService.getSalePrice(article.getArticle(), connection);
                article.setUnitPrice(price - (price * (article.getArticle().getTva() / 100)));
                article.setTva(article.getArticle().getTva());
                article.setTvaAmount((article.getUnitPrice() * article.getQuantity()) * (article.getArticle().getTva() / 100));
                article.setHtAmount(article.getUnitPrice() * article.getQuantity());
                article.setTtcAmount(price * article.getQuantity());
            }
            
            connection.close();

            
            request.setAttribute("purchaseOrderClient", purchaseOrderClient);
            
            
            // All required assets
            List<String> css = new ArrayList<>();
            css.add("assets/css/supplier/supplier.css");
            
            List<String> js = new ArrayList<>();
            js.add("assets/js/bootstrap.bundle.min.js");
            
            request.setAttribute("css", css);
            request.setAttribute("js", js);
            
            // Page definition
            request.setAttribute("title", "Bon de commande");
            request.setAttribute("contentPage", "./pages/sale/purchaseOrderDetail.jsp");
            
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
