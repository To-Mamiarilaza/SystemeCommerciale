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
import model.purchaseClient.PurchaseOrderClient;

/**
 *
 * @author chalman
 */
@WebServlet(name = "ClientTraitPurchaseOrderServlet", urlPatterns = {"/client-trait-purchase-order"})
public class ClientTraitPurchaseOrderServlet extends HttpServlet {

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
            out.println("<title>Servlet ClientTraitPurchaseOrderServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClientTraitPurchaseOrderServlet at " + request.getContextPath() + "</h1>");
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
            String idPurchaseOrderClient = request.getParameter("idPurchaseOrderClient");
            String status = request.getParameter("status");
            PurchaseOrderClient purchaseOrderClient = GenericDAO.findById(PurchaseOrderClient.class, Integer.valueOf(idPurchaseOrderClient), null);
            
            if(Integer.valueOf(status) == 5) { //Demander magasin
                purchaseOrderClient.setStatus(10);
                GenericDAO.updateById(purchaseOrderClient, purchaseOrderClient.getIdPurchaseOrderClient(), null);
            } else if(Integer.valueOf(status) == 15) {  //Facturer
                purchaseOrderClient.setStatus(20);
                GenericDAO.updateById(purchaseOrderClient, purchaseOrderClient.getIdPurchaseOrderClient(), null);
            } else if(Integer.valueOf(status) == 20) {  //Livrer
                purchaseOrderClient.setStatus(25);
                GenericDAO.updateById(purchaseOrderClient, purchaseOrderClient.getIdPurchaseOrderClient(), null);
            }
            
            response.sendRedirect("./client-purchase-order-detail?idPurchaseOrderClient="+purchaseOrderClient.getIdPurchaseOrderClient());
        } catch(Exception e) {
            request.setAttribute("error", e.getMessage());
            e.printStackTrace();
            response.sendRedirect("./client-purchase-order-detail");
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
