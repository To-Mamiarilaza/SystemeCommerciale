/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.purchase;

import connection.DBConnection;
import generalisation.GenericDAO.GenericDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.base.Utilisateur;
import model.purchase.PaymentMethod;
import model.purchase.Proforma;
import model.purchase.PurchaseOrder;
import model.supplier.Supplier;
import service.proforma.ProformaService;
import service.proforma.SupplierService;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "PurchaseOrderInsertion", urlPatterns = {"/purchase-order-insertion"})
public class PurchaseOrderInsertion extends HttpServlet {

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
            out.println("<title>Servlet PurchaseOrderInsertion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PurchaseOrderInsertion at " + request.getContextPath() + "</h1>");
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
            
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            request.getSession().setAttribute("purchaseOrder", purchaseOrder);
            
            // All required information
            Connection connection = DBConnection.getConnection();
            
            String idSupplier = request.getParameter("idSupplier");
            Proforma proforma = ProformaService.getProforma(idSupplier, connection);
            SupplierService.loadSupplierOwnedCategory(proforma.getSupplier(), connection);
            request.setAttribute("proforma", proforma);
            
            List<PaymentMethod> paymentMethods = ProformaService.getAllPaymentMehod(connection);
            request.setAttribute("paymentMethods", paymentMethods);
            
            connection.close();
            
            // All required assets
            List<String> css = new ArrayList<>();
            css.add("assets/css/supplier/supplier.css");
            
            List<String> js = new ArrayList<>();
            js.add("./assets/js/purchase/payment-method.js");
            js.add("./assets/js/purchase/purchase-order-save.js");
            
            request.setAttribute("css", css);
            request.setAttribute("js", js);
            
            // Page definition
            request.setAttribute("title", "Nouvelle bon de commande");
            request.setAttribute("contentPage", "./pages/request/purchaseOrderInsertion.jsp");
            
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
            int nbJourDelivery = Integer.valueOf(request.getParameter("nbJourDelivery"));
            int idSupplier = Integer.valueOf(request.getParameter("idSupplier"));
            int idPaymentMethod = Integer.valueOf(request.getParameter("idPaymentMethod"));
            
            Connection connection = DBConnection.getConnection();
            
            Supplier supplier = GenericDAO.findById(Supplier.class, idSupplier, connection);
            PaymentMethod paymentMethod = GenericDAO.findById(PaymentMethod.class, idPaymentMethod, connection);
            LocalDate deliveryDate = LocalDate.now().plusDays(nbJourDelivery);
            
            Proforma proforma = ProformaService.getProforma(supplier, connection);
            
            PurchaseOrder purchaseOrder = (PurchaseOrder) request.getSession().getAttribute("purchaseOrder");
            purchaseOrder.setDate(LocalDate.now());
            purchaseOrder.setSupplier(supplier);
            purchaseOrder.setPaymentMethod(paymentMethod);
            purchaseOrder.setDeliveryDate(deliveryDate);
            purchaseOrder.setTotalTVA(proforma.getTotalTVA());
            purchaseOrder.setTotalHT(proforma.getTotalHT());
            purchaseOrder.setTotalTTC(proforma.getTotalTTC());
            purchaseOrder.setStatus(1);     // En attente de validation
            purchaseOrder.setLineItems(proforma.getInvoiceLineItems());
            
            ProformaService.savePurchaseOrder(purchaseOrder, connection);
            
            connection.commit();
            connection.close();
            
            out.print("{\"success\": \"success\"}");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"error\": \"" + e.getMessage() + "\"}");
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
