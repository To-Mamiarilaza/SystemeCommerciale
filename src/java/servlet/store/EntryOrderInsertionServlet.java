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
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.article.Article;
import model.base.Utilisateur;
import model.entry.EntryOrder;
import model.entry.EntryOrderArticle;
import model.reception.ArticleDetails;
import model.reception.ReceptionArticleDetails;
import model.reception.ReceptionOrder;

/**
 *
 * @author to
 */
@WebServlet(name = "EntryOrderInsertionServlet", urlPatterns = {"/entry-order-insertion"})
public class EntryOrderInsertionServlet extends HttpServlet {

    List<ArticleDetails> articles = new ArrayList<>();

    public List<ArticleDetails> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDetails> articles) {
        this.articles = articles;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            EntryOrder entry = new EntryOrder();
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            if (utilisateur == null) {
                response.sendRedirect("./login");
            }
            int idReception = Integer.valueOf(request.getParameter("idReception"));
            List<ReceptionArticleDetails> receptionArticles = (List<ReceptionArticleDetails>) GenericDAO.directQuery(ReceptionArticleDetails.class, "select * from reception_article_details where id_reception_order = " + idReception, null);
            request.setAttribute("receptionArticles", receptionArticles);
            request.setAttribute("utilisateur", utilisateur);
            if (this.getArticles() != null) {
                this.getArticles().clear();
            }
            for (int i = 0; i < receptionArticles.size(); i++) {
                ArticleDetails ad = new ArticleDetails();
                ad.setArticle(receptionArticles.get(i).getArticle());
                ad.setQuantity((int) receptionArticles.get(i).getQuantity());
                entry.getListeArticle().add(ad);
            }
            request.getSession().setAttribute("entry", entry);
            // All required assets
            List<String> css = new ArrayList<>();
            css.add("assets/css/supplier/supplier.css");

            List<String> js = new ArrayList<>();
            js.add("assets/js/bootstrap.bundle.min.js");

            request.setAttribute("css", css);
            request.setAttribute("js", js);

            // Page definition
            request.setAttribute("title", "Insertion bon de livraison");
            request.setAttribute("contentPage", "./pages/store/entryOrderInsertion.jsp");

            request.getRequestDispatcher("./template.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String dateString = request.getParameter("date");
            LocalDate date = LocalDate.parse(dateString);
            String responsableName = request.getParameter("responsableName");
            String responsableContact = request.getParameter("responsableContact");
            int idReception = Integer.valueOf(request.getParameter("idReception"));

            EntryOrder entry = (EntryOrder) request.getSession().getAttribute("entry");
            entry.setDate(date);
            entry.setReceptionOrder((ReceptionOrder) GenericDAO.findById(ReceptionOrder.class, idReception, null));
            entry.setResponsableContact(responsableContact);
            entry.setResponsableName(responsableName);
            entry.setStatus(1);

            GenericDAO.save(entry, null);

            for (int i = 0; i < entry.getListeArticle().size(); i++) {
                EntryOrderArticle article = new EntryOrderArticle();
                article.setEntryOrder(entry);
                article.setArticle((Article) GenericDAO.findById(Article.class, entry.getListeArticle().get(i).getArticle().getIdArticle(), null));
                article.setQuantity(entry.getListeArticle().get(i).getQuantity());
                GenericDAO.save(article, null);
            }
            
            GenericDAO.directUpdate("update reception_order set status = 5 where id_reception_order = "+idReception, null);
            response.sendRedirect("./entry-request-list");

        } catch (Exception ex) {
            ex.printStackTrace();
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
