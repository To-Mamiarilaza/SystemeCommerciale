/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.purchase;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.purchase.ArticleQuantity;
import model.purchase.PurchaseRequest;

/**
 *
 * @author chalman
 */
@WebServlet(name = "AddNewRequestServlet", urlPatterns = {"/AddNewRequest"})
public class AddNewRequestServlet extends HttpServlet {

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
            out.println("<title>Servlet AddNewRequestServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewRequestServlet at " + request.getContextPath() + "</h1>");
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
        //doPost(request, response);
        PrintWriter out = response.getWriter();
        out.print("Mandeha");
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
            System.out.println("Article : "+quantity);
            System.out.println("Quantite : "+quantity);
            
            HttpSession session = request.getSession();
            PurchaseRequest pr = (PurchaseRequest) session.getAttribute("purchaseRequest");
            ArticleQuantity articleQuantity = pr.addArticleQuantity(article, quantity);
            
            if(articleQuantity.getIsExist() == false) {
                out.print("{\"code\":\""+articleQuantity.getArticle().getCode()+"\", \"article\":\""+articleQuantity.getArticle().getDesignation()+"\", \"quantity\":\""+articleQuantity.getQuantity()+"\", \"unity\":\""+articleQuantity.getArticle().getUnity().getName()+"\", \"exist\": false}");
            } else {
                out.print("{\"code\":\""+articleQuantity.getArticle().getCode()+"\", \"article\":\""+articleQuantity.getArticle().getDesignation()+"\", \"quantity\":\""+articleQuantity.getQuantity()+"\", \"unity\":\""+articleQuantity.getArticle().getUnity().getName()+"\", \"exist\": true}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("{\"error\": \"" + e.getMessage() + "\"}");
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
