/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.purchase;

import com.google.gson.Gson;
import generalisation.GenericDAO.GenericDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
import model.base.Service;
import model.base.Utilisateur;
import model.purchase.ArticleQuantity;
import model.purchase.PurchaseRequest;
import model.base.Utilisateur;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "PurchaseRequestInsertionServlet", urlPatterns = {"/purchase-request-insertion"})
public class PurchaseRequestInsertion extends HttpServlet {

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
            //Initialiser l'objet session du demande achat
            HttpSession session = request.getSession();
            PurchaseRequest pr = new PurchaseRequest();
            session.setAttribute("purchaseRequest", pr);
            
            //Initialiser la liste des articles
            List<Article> articles = (List<Article>) GenericDAO.getAll(Article.class, null, null);
            request.setAttribute("articles", articles);

            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            if (utilisateur == null) {
                response.sendRedirect("./login");
            }
            request.setAttribute("utilisateur", utilisateur);
            
            // All required assets
            List<String> css = new ArrayList<>();
            css.add("assets/css/supplier/supplier.css");
            css.add("aassets/css/ajax/styleAjax.css");
            
            List<String> js = new ArrayList<>();
            js.add("assets/js/purchase/purchase-insertion.js");
            
            request.setAttribute("css", css);
            request.setAttribute("js", js);
            
            // Page definition
            request.setAttribute("title", "Insertion demande achat");
            request.setAttribute("contentPage", "./pages/request/purchaseRequestInsertion.jsp");
            
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
        PrintWriter out = response.getWriter();
        try {
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            if (utilisateur == null) {
                response.sendRedirect("./login");
            }
            request.setAttribute("utilisateur", utilisateur);
            
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            
            HttpSession session = request.getSession();
            PurchaseRequest pr = (PurchaseRequest) session.getAttribute("purchaseRequest");
            
            pr.setTitle(title);
            pr.setDescription(description);
            pr.setSendingDate(LocalDate.now());
            pr.setService(utilisateur.getService());
            pr.setUtilisateur(utilisateur);
            pr.setStatus(1);
            pr.save(pr);
            session.removeAttribute("purchaseRequest");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            e.printStackTrace();
        }
        response.sendRedirect("./purchase-request-list");
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
