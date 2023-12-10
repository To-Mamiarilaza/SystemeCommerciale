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
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.article.Article;
import model.base.Utilisateur;
import model.purchase.ArticleQuantity;
import model.purchase.PurchaseOrder;
import model.reception.ArticleDetails;
import model.reception.SupplierDeliveryOrder;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "DeliveryOrderInsertionServlet", urlPatterns = {"/delivery-order-insertion"})
public class DeliveryOrderInsertionServlet extends HttpServlet {

    List<String> annomalies;

    public List<String> getAnnomalies() {
        return annomalies;
    }

    public void setAnnomalies(List<String> annomalies) {
        this.annomalies = annomalies;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DeliveryArticleInsertionServlet dev = new DeliveryArticleInsertionServlet();
            dev.getArticleDetails().clear();

            if (request.getSession().getAttribute("supplierDeliveryOrder") == null) {
                SupplierDeliveryOrder supplierDeliveries = new SupplierDeliveryOrder();
                HttpSession session = request.getSession();
                session.setAttribute("supplierDeliveryOrder", supplierDeliveries);
            }

            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            if (utilisateur == null) {
                response.sendRedirect("./login");
            }
            request.setAttribute("utilisateur", utilisateur);
            List<Article> articles = (List<Article>) GenericDAO.getAll(Article.class, " where status = 1", null);
            List<PurchaseOrder> bonCommande = (List<PurchaseOrder>) GenericDAO.getAll(PurchaseOrder.class, "where id_purchase_order not in (select id_purchase_order from supplier_delivery_order) and status = 2", null);
            request.setAttribute("articles", articles);
            request.setAttribute("commandes", bonCommande);
            // All required assets
            List<String> css = new ArrayList<>();
            css.add("assets/css/supplier/supplier.css");

            List<String> js = new ArrayList<>();
            js.add("assets/js/bootstrap.bundle.min.js");
            js.add("assets/js/delivery/delivery.js");

            request.setAttribute("css", css);
            request.setAttribute("js", js);

            // Page definition
            request.setAttribute("title", "Insertion bon de livraison");
            request.setAttribute("contentPage", "./pages/delivery/deliveryOrderInsertion.jsp");
            request.setAttribute("annomalies", this.getAnnomalies());

            if (request.getParameter("error") != null || !"".equals(request.getParameter("error"))) {
                request.setAttribute("error", request.getParameter("error"));
            }
            request.getRequestDispatcher("./template.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            LocalDate date = LocalDate.parse(request.getParameter("date"));
            int idBoc = Integer.parseInt(request.getParameter("boc"));
            String livreur = request.getParameter("livreur");
            String livreurContact = request.getParameter("livreurContact");

            SupplierDeliveryOrder delivery = (SupplierDeliveryOrder) request.getSession().getAttribute("supplierDeliveryOrder");
            delivery.setPurchaseOrder((PurchaseOrder) GenericDAO.findById(PurchaseOrder.class, idBoc, null));
            delivery.setDelivery_date(date);
            delivery.setDeliversContact(livreurContact);
            delivery.setDeliversName(livreur);

            if (delivery.getListeArticles() != null) {
                List<ArticleDetails> deliveryArticle = delivery.getListeArticles();
                List<ArticleQuantity> articlesQuantity = (List<ArticleQuantity>) GenericDAO.directQuery(ArticleQuantity.class, "select * from article_quantity where id_purchase_order = " + idBoc, null);
                List<String> annomalies = delivery.check_anommalie(deliveryArticle, articlesQuantity);
                if (this.getAnnomalies() == null && annomalies.size() > 0) {
                    this.setAnnomalies(annomalies);
                    response.sendRedirect("./delivery-order-insertion");
                } else if (annomalies.size() <= 0) {
                    response.sendRedirect("./reception-order-insertion");
                }
            } else {
                String error = " vous devez ajouter les articles livrés avant de crée un bon de livraison ";
                response.sendRedirect("./delivery-order-insertion?error=" + error);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("./delivery-order-insertion?error=" + ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
