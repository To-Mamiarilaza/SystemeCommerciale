/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.departement.reception;

import generalisation.GenericDAO.GenericDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.article.Article;
import model.base.Utilisateur;
import model.dept.DeptReception;
import model.dept.DeptReceptionArticle;
import model.movement.out.OutgoingOrder;
import model.movement.out.OutgoingOrderDetail;
import model.reception.ArticleDetails;

/**
 *
 * @author to
 */
@WebServlet(name = "DeptReceptionOrderInsertionServlet", urlPatterns = {"/dept-reception-order-insertion"})
public class DeptReceptionOrderInsertionServlet extends HttpServlet {

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
            out.println("<title>Servlet ReceptionOrderInsertionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReceptionOrderInsertionServlet at " + request.getContextPath() + "</h1>");
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
            DeptReception accuseReception = new DeptReception();
            request.getSession().setAttribute("accuseReception", accuseReception);
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            if (utilisateur == null) {
                response.sendRedirect("./login");
            }
            String outOrder = request.getParameter("idOutOrder");
            request.setAttribute("utilisateur", utilisateur);

            if (request.getParameter("idOutOrder") != null) {
                int idOutOrder = Integer.valueOf(request.getParameter("idOutOrder"));
                OutgoingOrder out = (OutgoingOrder) GenericDAO.findById(OutgoingOrder.class, idOutOrder, null);
                request.setAttribute("outOrder", out);
                List<OutgoingOrderDetail> orderDetails = (List<OutgoingOrderDetail>) GenericDAO.directQuery(OutgoingOrderDetail.class, "select * from outgoing_order_detail where id_outgoing_order = " + out.getIdOutgoingOrder(), null);
                request.setAttribute("orderDetails", orderDetails);
                List<Article> articles = (List<Article>) GenericDAO.getAll(Article.class, " where status = 1", null);
                request.setAttribute("articles", articles);
                for (int i = 0; i < orderDetails.size(); i++) {
                    ArticleDetails ad = new ArticleDetails();
                    ad.setArticle(orderDetails.get(i).getArticle());
                    ad.setQuantity((int) orderDetails.get(i).getQuantity());
                    accuseReception.getListeArticles().add(ad);
                }
            }
            // All required assets
            List<String> css = new ArrayList<>();
            css.add("assets/css/supplier/supplier.css");

            List<String> js = new ArrayList<>();
            js.add("assets/js/bootstrap.bundle.min.js");

            request.setAttribute("css", css);
            request.setAttribute("js", js);

            // Page definition
            request.setAttribute("title", "Insertion accusé de récéption");
            request.setAttribute("contentPage", "./pages/reception/receptionOrderInsertion.jsp");

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
        try {
            String dateString = request.getParameter("date");
            LocalDate date = LocalDate.parse(dateString);
            String responsable = request.getParameter("responsable");
            String responsableContact = request.getParameter("responsableContact");
            int idOutorder = Integer.valueOf(request.getParameter("idOutOrder"));

            DeptReception accuseReception = (DeptReception) request.getSession().getAttribute("accuseReception");
            accuseReception.setDate(date);
            accuseReception.setOutgoingOrder((OutgoingOrder) GenericDAO.findById(OutgoingOrder.class, idOutorder, null));
            accuseReception.setResponsableContact(responsableContact);
            accuseReception.setResponsableName(responsable);
            accuseReception.setStatus(1);

            GenericDAO.save(accuseReception, null);
            
            List<DeptReception> lastAccuse = (List<DeptReception>) GenericDAO.directQuery(DeptReception.class, "select * from dept_reception order by id_dept_reception desc limit 1", null);
            
            for (int i = 0; i < accuseReception.getListeArticles().size(); i++) {
                DeptReceptionArticle deptArticles = new DeptReceptionArticle();
                deptArticles.setArticle(accuseReception.getListeArticles().get(i).getArticle());
                deptArticles.setDeptReception(lastAccuse.get(0));
                deptArticles.setQuantity(accuseReception.getListeArticles().get(i).getQuantity());
                GenericDAO.save(deptArticles, null);
            }
            GenericDAO.directUpdate("update outgoing_order set status = 15 where id_outgoing_order = "+lastAccuse.get(0).getOutgoingOrder().getIdOutgoingOrder(), null);
            response.sendRedirect("./dept-request-reception-list");
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
