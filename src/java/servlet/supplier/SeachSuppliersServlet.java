/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.supplier;

import generalisation.GenericDAO.GenericDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.base.Utilisateur;
import model.supplier.Supplier;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "SeachSuppliersServlet", urlPatterns = {"/supplier-search"})
public class SeachSuppliersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            request.setAttribute("utilisateur", utilisateur);
            
            String keyWords = request.getParameter("keyWords");
            if (!"".equals(keyWords) || keyWords == null) {
                List<Supplier> suppliers = (List<Supplier>) GenericDAO.directQuery(Supplier.class, "select * from supplier where supplier_name like '%" + keyWords + "%' and status = 1", null);
                List<String> css = new ArrayList<>();
                css.add("assets/css/supplier/supplier.css");

                List<String> js = new ArrayList<>();

                request.setAttribute("css", css);
                request.setAttribute("js", js);

                request.setAttribute("listSupplier", suppliers);
                // Page definition
                request.setAttribute("title", "Listes des fournisseurs");
                request.setAttribute("contentPage", "./pages/supplier/supplierList.jsp");

                request.getRequestDispatcher("./template.jsp").forward(request, response);
            } else {
                List<Supplier> suppliers = (List<Supplier>) GenericDAO.directQuery(Supplier.class, "select * from supplier where status = 1", null);
                List<String> css = new ArrayList<>();
                css.add("assets/css/supplier/supplier.css");

                List<String> js = new ArrayList<>();

                request.setAttribute("css", css);
                request.setAttribute("js", js);

                request.setAttribute("listSupplier", suppliers);
                // Page definition
                request.setAttribute("title", "Listes des fournisseurs");
                request.setAttribute("contentPage", "./pages/supplier/supplierList.jsp");

                request.getRequestDispatcher("./template.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
