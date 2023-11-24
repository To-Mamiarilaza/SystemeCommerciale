/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.purchase;

import generalisation.GenericDAO.GenericDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.article.Article;
import model.purchase.ArticleQuantity;
import model.purchase.PurchaseRequest;
import model.base.Utilisateur;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "PurchaseRequestDetailServlet", urlPatterns = {"/purchase-request-detail"})
public class PurchaseRequestDetailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
            //Recuperer la demande correspondant au id recu
            String idPurchaseRequest = request.getParameter("idPurchaseRequest");

            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            request.setAttribute("user", utilisateur);
            if (utilisateur == null) {
                response.sendRedirect("./login");
            }
            request.setAttribute("utilisateur", utilisateur);
            
            //Demande recuperer
            PurchaseRequest purchaseRequest = GenericDAO.findById(PurchaseRequest.class, Integer.valueOf(idPurchaseRequest), null);
            request.setAttribute("purchaseRequest", purchaseRequest);
            
            //Liste des articles quantites
            List<ArticleQuantity> articleQuantitys = (List<ArticleQuantity>) GenericDAO.directQuery(ArticleQuantity.class, "SELECT * FROM article_quantity WHERE id_purchase_request="+idPurchaseRequest+" AND status =1", null);
            request.setAttribute("articlesQuantity", articleQuantitys);
             
            //Liste des articles
            List<Article> articles = (List<Article>) GenericDAO.directQuery(Article.class, "SELECT * FROM article WHERE status = 1 ORDER BY id_article DESC", null);
            request.setAttribute("articles", articles);
            
            //Lancer la demande detailler en session
            HttpSession session = request.getSession();
            purchaseRequest.setArticleQuantityList(articleQuantitys);
            session.setAttribute("purchaseRequest", purchaseRequest);

            // All required assets
            List<String> css = new ArrayList<>();
            css.add("assets/css/supplier/supplier.css");
            
            List<String> js = new ArrayList<>();
            js.add("assets/js/purchase/purchase-insertion.js");
            
            request.setAttribute("css", css);
            request.setAttribute("js", js);
            
            // Page definition
            request.setAttribute("title", "Detail d'une demande");
            request.setAttribute("contentPage", "./pages/request/purchaseRequestDetail.jsp");
            
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
