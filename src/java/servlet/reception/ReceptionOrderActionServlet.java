/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.reception;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.reception.ArticleDetails;
import model.reception.ReceptionOrder;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "ReceptionOrderActionServlet", urlPatterns = {"/reception-action"})
public class ReceptionOrderActionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReceptionOrder reception = (ReceptionOrder) request.getSession().getAttribute("reception");
        List<ArticleDetails> listeArticles = reception.getListeArticles();
        int action = Integer.valueOf(request.getParameter("action"));
        if (action == 1) {
            //modification
            int idArticle = Integer.valueOf(request.getParameter("idArticle"));
            ArticleDetails details = listeArticles.get(idArticle);
        } else if (action == 2) {
            //suppression
            int idArticle = Integer.valueOf(request.getParameter("idArticle"));
            listeArticles.remove(idArticle);
            response.sendRedirect("./reception-order-insertion");
        }
    }

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
