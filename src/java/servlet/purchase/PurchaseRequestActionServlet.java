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
import jakarta.servlet.http.HttpSession;
import model.purchase.PurchaseRequest;

/**
 *
 * @author chalman
 */
@WebServlet(name = "PurchaseRequestActionServlet", urlPatterns = {"/PurchaseRequestAction"})
public class PurchaseRequestActionServlet extends HttpServlet {

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
            out.println("<title>Servlet PurchaseRequestActionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PurchaseRequestActionServlet at " + request.getContextPath() + "</h1>");
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
            String idHelp = request.getParameter("idHelp");
            HttpSession session = request.getSession();
            PurchaseRequest purchaseRequest = (PurchaseRequest)session.getAttribute("purchaseRequest");
            System.out.println("help : "+idHelp);
            System.out.println("objet :"+purchaseRequest);
            
            if(Integer.valueOf(idHelp) == 0) {  //refuse
                purchaseRequest.setStatus(0);
                GenericDAO.updateById(purchaseRequest, purchaseRequest.getIdPurchaseRequest(), null);
            }
            else {  //Valide
                purchaseRequest.setStatus(2);
                GenericDAO.updateById(purchaseRequest, purchaseRequest.getIdPurchaseRequest(), null);
            }
        } catch(Exception e) {
            request.setAttribute("error", e.getMessage());
            e.printStackTrace();
        }
        response.sendRedirect("./purchase-request-list");
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
