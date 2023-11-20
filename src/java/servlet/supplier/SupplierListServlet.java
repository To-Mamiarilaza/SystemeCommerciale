/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.supplier;

import generalisation.GenericDAO.GenericDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.article.Article;
import model.article.Category;
import model.base.Utilisateur;
import model.supplier.Supplier;
import model.supplier.SupplierCategoryProduct;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "SupplierListServlet", urlPatterns = {"/supplier-list"})
public class SupplierListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            request.setAttribute("utilisateur", utilisateur);

            // All required assets
            List<String> css = new ArrayList<>();
            css.add("assets/css/supplier/supplier.css");

            List<String> js = new ArrayList<>();

            request.setAttribute("css", css);
            request.setAttribute("js", js);

            List<Supplier> suppliers = (List<Supplier>) GenericDAO.directQuery(Supplier.class, "SELECT * FROM supplier WHERE status = 1", null);

            request.setAttribute("listSupplier", suppliers);

            // Page definition
            request.setAttribute("title", "Listes des fournisseurs");
            request.setAttribute("contentPage", "./pages/supplier/supplierList.jsp");

            request.getRequestDispatcher("./template.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
