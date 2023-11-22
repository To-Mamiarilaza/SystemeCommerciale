/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.purchase;

import generalisation.GenericDAO.GenericDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.purchase.PurchaseOrder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import service.proforma.PurchaseOrderService;
import service.proforma.SupplierService;
import util.pdf.PDFRealisationUtil;

/**
 *
 * @author chalman
 */
@WebServlet(name = "PdfPurchaseOrderServlet", urlPatterns = {"/PdfPurchaseOrder"})
public class PdfPurchaseOrderServlet extends HttpServlet {

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
            out.println("<title>Servlet PdfPurchaseOrderServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PdfPurchaseOrderServlet at " + request.getContextPath() + "</h1>");
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
             int idPurchaseOrder = Integer.valueOf(request.getParameter("idPurchaseOrder"));
            PurchaseOrder purchaseOrder = PurchaseOrderService.getPurchaseOrder(idPurchaseOrder);
            SupplierService.loadSupplierOwnedCategory(purchaseOrder.getSupplier(), null);
            // Bien remplir ces données et tout doit aller automatiquement
            String title = "BON DE COMMANDE";
            String numero = String.valueOf(purchaseOrder.getIdPurchaseOrder());
            String dateBondeCommande = purchaseOrder.getDate().toString();
            String fournisseur = purchaseOrder.getSupplier().getSupplierName();
            String contactResponsable = purchaseOrder.getSupplier().getResponsableContact();
            String mail = purchaseOrder.getSupplier().getMail();
            String adresse = purchaseOrder.getSupplier().getSupplierAddress();
            String dateLivraison = purchaseOrder.getDeliveryDate().toString();
            String modePaiment = purchaseOrder.getPaymentMethod().getPaymentMethodName();

          
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage(PDRectangle.A4);
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    PDFRealisationUtil outil = new PDFRealisationUtil();

                    // Header du contrat
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                    outil.writeText(contentStream, 30, 810, title);
                    
                    //Description du contenu
                    int dynamicY = 790;     // pour que la hauteur s'adapte en fonction du nombres de ligne
                    int lineHeight = 20;

                    contentStream.setNonStrokingColor(192, 192, 192); // Gris clair
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    int line = outil.writeMultilineText(contentStream, 30, dynamicY, "Numero : "+numero, lineHeight, 70);
                    //dynamicY -= lineHeight * (line + 1);
                    dynamicY -= lineHeight;
                    
                    outil.writeText(contentStream, 30, dynamicY, dateBondeCommande);
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;
                    
                    
                    //Categories de produits vendues
                    contentStream.setNonStrokingColor(135, 206, 250); // Bleu clair
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                    outil.writeText(contentStream, 400, dynamicY, "CATEGORIES DE PRODUITS");
                    contentStream.setLineWidth(1f);
                    contentStream.setStrokingColor(135, 206, 250);
                    contentStream.drawLine(400, dynamicY - 8, 540, dynamicY - 8);
                    dynamicY -= lineHeight;
                    
                    contentStream.setNonStrokingColor(0, 0, 0); // Gris clair
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    int ye = dynamicY;
                    for(int i = 0; i < purchaseOrder.getSupplier().getOwnedCategoryList().size(); i++) {
                        outil.writeText(contentStream, 400, ye -= lineHeight, "- "+purchaseOrder.getSupplier().getOwnedCategoryList().get(i).getDesignation());
                    }
                    
                    //Information du fournisseur
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                    outil.writeText(contentStream, 30, dynamicY, "Nom du fournisseur");
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    outil.writeText(contentStream, 200, dynamicY, fournisseur);
                    dynamicY -= lineHeight;
                    
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                    outil.writeText(contentStream, 30, dynamicY, "Contact du responsable");
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    outil.writeText(contentStream, 200, dynamicY, contactResponsable);
                    dynamicY -= lineHeight;
                    
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                    outil.writeText(contentStream, 30, dynamicY, "Mail");
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    outil.writeText(contentStream, 200, dynamicY, mail);
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    dynamicY -= lineHeight;
                    
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                    outil.writeText(contentStream, 30, dynamicY, "Adresse");
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    outil.writeText(contentStream, 200, dynamicY, adresse);
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;
                    
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                    outil.writeText(contentStream, 30, dynamicY, "date de livraison");
                    outil.writeText(contentStream, 200, dynamicY, dateLivraison);
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    dynamicY -= lineHeight;
                    
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                    outil.writeText(contentStream, 30, dynamicY, "Mode de paiment");
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    outil.writeText(contentStream, 200, dynamicY, modePaiment);
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;

                    //Condition de paiment
                    contentStream.setNonStrokingColor(135, 206, 250); // Bleu clair
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    outil.writeText(contentStream, 30, dynamicY, "Condition de payement");
                    dynamicY -= lineHeight;
                    
                    for(int i = 0; i < purchaseOrder.getPaymentConditions().size(); i++) {
                    contentStream.setNonStrokingColor(0, 0, 0); // Noir
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    outil.writeText(contentStream, 30, dynamicY, "-");
                    outil.writeText(contentStream, 70, dynamicY, purchaseOrder.getPaymentConditions().get(i).getPercentage()+"%");
                    outil.writeText(contentStream, 140, dynamicY, purchaseOrder.getPaymentConditions().get(i).getPaymentDate().toString());
                    dynamicY -= lineHeight;
                    }
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;
                    
                    contentStream.setNonStrokingColor(135, 206, 250); // Bleu clair

                    // Définir les coordonnées et les dimensions du rectangle
                    float x = 20;
                    float y = dynamicY - 20;
                    float width = 550;
                    float height = 40;

                    // Dessiner et colorer le rectangle
                    contentStream.fillRect(x, y, width, height);
                    
                            
                    contentStream.setNonStrokingColor(0, 0, 0); // noir
                    //Liste des articles
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                    outil.writeText(contentStream, 30, dynamicY, "Designation");
                    outil.writeText(contentStream, 140, dynamicY, "Prix unitaire");
                    outil.writeText(contentStream, 220, dynamicY, "Quantite");
                    outil.writeText(contentStream, 280, dynamicY, "TVA");
                    outil.writeText(contentStream, 350, dynamicY, "Montant TVA");
                    outil.writeText(contentStream, 440, dynamicY, "HT");
                    outil.writeText(contentStream, 520, dynamicY, "TTC");

                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;
                    
                    for(int i = 0; i < purchaseOrder.getLineItems().size(); i++) {
                    contentStream.setFont(PDType1Font.HELVETICA, 9);
                    outil.writeText(contentStream, 30, dynamicY, purchaseOrder.getLineItems().get(i).getArticle().getDesignation());
                    outil.writeText(contentStream, 140, dynamicY, purchaseOrder.getLineItems().get(i).getUnitPrice()+" AR");
                    outil.writeText(contentStream, 220, dynamicY, purchaseOrder.getLineItems().get(i).getQuantity()+"");
                    outil.writeText(contentStream, 280, dynamicY, purchaseOrder.getLineItems().get(i).getTva()+" %");
                    outil.writeText(contentStream, 335, dynamicY, purchaseOrder.getLineItems().get(i).getTVAAmount()+" AR");
                    outil.writeText(contentStream, 430, dynamicY, purchaseOrder.getLineItems().get(i).getHTAmount()+" AR");
                    outil.writeText(contentStream, 510, dynamicY, purchaseOrder.getLineItems().get(i).getTTCAmount()+" AR");
                    //contentStream.setNonStrokingColor(192, 192, 192); // Gris clair
                    contentStream.drawLine(20, dynamicY - 20, 570, dynamicY-20);
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;
                    }
                    
                    outil.writeText(contentStream, 280, dynamicY, "TOTAL");
                    contentStream.setNonStrokingColor(0, 80, 0); // Vert sombre
                    outil.writeText(contentStream, 335, dynamicY, purchaseOrder.getTotalTVAString()+"");
                    outil.writeText(contentStream, 425, dynamicY, purchaseOrder.getTotalHTString()+"");
                    outil.writeText(contentStream, 505, dynamicY, purchaseOrder.getTotalTTCString()+"");
                    contentStream.drawLine(20, dynamicY - 20, 570, dynamicY-20);
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;

                               
                    contentStream.setNonStrokingColor(0, 0, 0); // noir
                    //Prix total
                     contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                    outil.writeText(contentStream, 30, dynamicY, "Arrete le present proforma a la somme de :");
                    dynamicY -= lineHeight;
                    
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    outil.writeText(contentStream, 30, dynamicY, purchaseOrder.getTotalTTCLetter());
                    
                }

                // Affichage a l'écran
                document.save(response.getOutputStream());

                //conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("Error", e.getMessage());
            response.sendRedirect("./purchase-order-detail");
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
