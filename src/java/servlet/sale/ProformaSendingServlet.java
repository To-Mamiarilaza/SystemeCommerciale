/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.sale;

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
import model.base.Utilisateur;
import model.sale.ArticleQuantitySale;
import model.sale.ProformaSending;

/**
 *
 * @author to
 */
@WebServlet(name = "ProformaSendingServlet", urlPatterns = {"/proforma-sending"})
public class ProformaSendingServlet extends HttpServlet {

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
            
            //Liste des articles
            List<Article> articles = (List<Article>) GenericDAO.getAll(Article.class, null, null);
            request.setAttribute("articles", articles);
            
            //List des proforma
            List<ProformaSending> profomaSendings = (List<ProformaSending>) GenericDAO.getAll(ProformaSending.class, null, null);
            request.setAttribute("proformaSendings", profomaSendings);
            
            
            request.setAttribute("utilisateur", utilisateur);

            //Initialisation de la session proformaSending
            ProformaSending proformaSending = new ProformaSending();
            HttpSession session = request.getSession();
            session.setAttribute("proformaSending", proformaSending);
            
            // All required assets
            List<String> css = new ArrayList<>();
            css.add("assets/css/supplier/supplier.css");
            
            List<String> js = new ArrayList<>();
            js.add("assets/js/bootstrap.bundle.min.js");
            js.add("assets/js/sale/proformaSending.js");
            
            request.setAttribute("css", css);
            request.setAttribute("js", js);
            
            // Page definition
            request.setAttribute("title", "Insertion bon de livraison");
            request.setAttribute("contentPage", "./pages/sale/proformaSending.jsp");
            
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
              response.setContentType("text/plain;charset=UTF-8");
          
        PrintWriter out = response.getWriter();
        try {
            String article = request.getParameter("article");
            String quantity = request.getParameter("quantity");
            HttpSession session = request.getSession();
            ProformaSending proformaSending = (ProformaSending)session.getAttribute("proformaSending");
            ArticleQuantitySale articleQuantity = proformaSending.addArticleQuantity(article, quantity);
            proformaSending.displayProforma();
            if(articleQuantity.getIsExist() == false) {
                out.print("{\"code\":\""+articleQuantity.getArticle().getCode()+"\", \"article\":\""+articleQuantity.getArticle().getDesignation()+"\", \"quantity\":\""+articleQuantity.getQuantity()+"\", \"exist\": false}");
            } else {
                out.print("{\"code\":\""+articleQuantity.getArticle().getCode()+"\", \"article\":\""+articleQuantity.getArticle().getDesignation()+"\", \"quantity\":\""+articleQuantity.getQuantity()+"\", \"exist\": true}");
            }
        } catch(Exception e) {
            request.setAttribute("error", e.getMessage());
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
