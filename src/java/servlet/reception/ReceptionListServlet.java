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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.base.Utilisateur;
import model.reception.ReceptionOrder;

/**
 *
 * @author to
 */
@WebServlet(name = "ReceptionListServlet", urlPatterns = {"/reception-list"})
public class ReceptionListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            if (utilisateur == null) {
                response.sendRedirect("./login");
            }
            request.setAttribute("utilisateur", utilisateur);
            List<ReceptionOrder> receptions = new ArrayList<>();
            if (request.getParameter("date") == null || request.getParameter("status") == null) {
                receptions = (List<ReceptionOrder>) GenericDAO.getAll(ReceptionOrder.class, "", null);
            } else {
                int status = Integer.valueOf(request.getParameter("status"));
                String date = request.getParameter("date");
                receptions = (List<ReceptionOrder>) GenericDAO.directQuery(ReceptionOrder.class, "select * from reception_order where status = " + status + " and reception_date <= '" + date + "'", null);
            }
            request.setAttribute("receptions", receptions);
            // All required assets
            List<String> css = new ArrayList<>();
            css.add("assets/css/supplier/supplier.css");

            List<String> js = new ArrayList<>();
            js.add("assets/js/bootstrap.bundle.min.js");

            request.setAttribute("css", css);
            request.setAttribute("js", js);

            // Page definition
            request.setAttribute("title", "Liste des receptions");
            request.setAttribute("contentPage", "./pages/delivery/receptionList.jsp");

            request.getRequestDispatcher("./template.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
