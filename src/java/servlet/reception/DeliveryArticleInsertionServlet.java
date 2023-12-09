/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.reception;

import com.google.gson.Gson;
import generalisation.GenericDAO.GenericDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.article.Article;
import model.reception.ArticleDetails;
import model.reception.SupplierDeliveryOrder;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "DeliveryArticleInsertionServlet", urlPatterns = {"/delivery-add-article"})
public class DeliveryArticleInsertionServlet extends HttpServlet {

    List<ArticleDetails> articleDetails = new ArrayList<>();

    public List<ArticleDetails> getArticleDetails() {
        return articleDetails;
    }

    public void setArticleDetails(List<ArticleDetails> articleDetails) {
        this.articleDetails = articleDetails;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("./delivery-order-insertion");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SupplierDeliveryOrder delivery = (SupplierDeliveryOrder) request.getSession().getAttribute("supplierDeliveryOrder");

            int idArticle = Integer.valueOf(request.getParameter("article"));
            int quantite = Integer.valueOf(request.getParameter("quantite"));

            
            this.getArticleDetails().add(new ArticleDetails((Article) GenericDAO.findById(Article.class, idArticle, null), quantite));

            delivery.setListeArticles(this.getArticleDetails());

            //pour supprimer les donnees dans le liste
            //delivery.getListeArticle().clear();
            response.setContentType("application/json");
            Gson gson = new Gson();
            String jsonData = gson.toJson(delivery.getListeArticles());
            response.getWriter().write(jsonData);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
