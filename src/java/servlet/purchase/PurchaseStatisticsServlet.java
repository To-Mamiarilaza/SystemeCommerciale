/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.purchase;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.base.Service;
import model.base.Utilisateur;
import model.purchase.ArticleRequest;
import model.purchase.PurchaseOrder;
import model.statistics.DepenseMensuel;
import model.statistics.ServiceDepense;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "PurchaseStatisticsServlet", urlPatterns = {"/purchase-statistics"})
public class PurchaseStatisticsServlet extends HttpServlet {

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
            out.println("<title>Servlet PurchaseStatisticsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PurchaseStatisticsServlet at " + request.getContextPath() + "</h1>");
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
            request.setAttribute("utilisateur", utilisateur);

            // All required informations
            // All required assets
            List<String> css = new ArrayList<>();
            css.add("assets/css/supplier/supplier.css");

            List<String> js = new ArrayList<>();
            js.add("assets/vendors/chart.js/Chart.min.js");
            
            request.setAttribute("css", css);
            request.setAttribute("js", js);

            List<PurchaseOrder> montantTtc = (List<PurchaseOrder>) GenericDAO.directQuery(PurchaseOrder.class, "select * from purchase_order where status = 2", null);
            double montant = (new PurchaseOrder()).montantTtc(montantTtc);

            List<ArticleRequest> articleEnAttente = (List<ArticleRequest>) GenericDAO.directQuery(ArticleRequest.class, "select * from v_article_request", null);
            int nombreRequest = articleEnAttente.size();
            //service depense
            List<ServiceDepense> serviceDepense = new ArrayList<>();
            //liste des service
            List<Service> listeService = (List<Service>) GenericDAO.directQuery(Service.class, "select * from service where status = 1", null);
            for (int i = 0; i < listeService.size(); i++) {
                ServiceDepense sp = listeService.get(i).getServiceDepense(montant);
                serviceDepense.add(sp);
            }
            List<DepenseMensuel> depenseMensuel = new DepenseMensuel().getDepenseMensuel(montant);
            
            request.setAttribute("depenseMensuel", depenseMensuel);
            request.setAttribute("sommeMontant", montant);
            request.setAttribute("nombreRequest", nombreRequest);
            request.setAttribute("serviceDepense", serviceDepense);
            request.setAttribute("listeService", listeService);
            // Page definition
            request.setAttribute("title", "Detail d'une demande");
            request.setAttribute("contentPage", "./pages/request/purchaseStatistique.jsp");

            request.getRequestDispatcher("./template.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
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
        processRequest(request, response);
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
