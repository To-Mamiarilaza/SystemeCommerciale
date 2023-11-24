/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.purchase;

import connection.DBConnection;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.article.Article;
import model.base.Utilisateur;
import model.purchase.ArticleRequest;
import model.purchase.Proforma;
import service.proforma.ArticlePriceService;
import service.proforma.ProformaService;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "ArticlePriceInsertionServlet", urlPatterns = {"/article-price-insertion"})
public class ArticlePriceInsertionServlet extends HttpServlet {

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
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            if (utilisateur == null) {
                response.sendRedirect("./login");
            }
            request.setAttribute("utilisateur", utilisateur);
            
            Connection connection = DBConnection.getConnection();
            
            // All required information
            List<ArticleRequest> articleRequests = ProformaService.getAllArticleRequest(connection);
            request.setAttribute("articleRequests", articleRequests);
            
            List<Proforma> proformas = ProformaService.getAllAvailableProforma(connection);
            request.setAttribute("proformas", proformas);
            
            List<Article> articles = ArticlePriceService.getAllArticle(connection);
            request.setAttribute("articles", articles);

            // All required assets
            List<String> css = new ArrayList<>();
            css.add("./assets/css/supplier/supplier.css");
            
            List<String> js = new ArrayList<>();
            js.add("./assets/js/purchase/article-choice.js");
            
            request.setAttribute("css", css);
            request.setAttribute("js", js);
            
            // Page definition
            request.setAttribute("title", "Detail d'une demande");
            request.setAttribute("contentPage", "./pages/request/articlePriceInsertion.jsp");
            
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
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            if (utilisateur == null) {
                response.sendRedirect("./login");
            }
            request.setAttribute("utilisateur", utilisateur);
            String idArticle = request.getParameter("idArticle");
            
            Connection connection = DBConnection.getConnection();
            
            // All required information
            List<ArticleRequest> articleRequests = new ArrayList<>();
            if (idArticle == null || idArticle.trim().equals("")) {
                articleRequests = ProformaService.getAllArticleRequest(connection);
            } else {
                articleRequests = ProformaService.getAllArticleRequest(idArticle, connection);
            }
            
            
            request.setAttribute("articleRequests", articleRequests);
            
            List<Proforma> proformas = ProformaService.getAllAvailableProforma(connection);
            request.setAttribute("proformas", proformas);
            
            List<Article> articles = ArticlePriceService.getAllArticle(connection);
            request.setAttribute("articles", articles);

            // All required assets
            List<String> css = new ArrayList<>();
            css.add("./assets/css/supplier/supplier.css");
            
            List<String> js = new ArrayList<>();
            js.add("./assets/js/purchase/article-choice.js");
            
            request.setAttribute("css", css);
            request.setAttribute("js", js);
            
            // Page definition
            request.setAttribute("title", "Detail d'une demande");
            request.setAttribute("contentPage", "./pages/request/articlePriceInsertion.jsp");
            
            request.getRequestDispatcher("./template.jsp").forward(request, response);
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
