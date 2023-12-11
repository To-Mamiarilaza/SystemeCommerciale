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
import java.time.LocalDate;
import java.util.List;
import model.purchaseClient.ArticleOrder;
import model.purchaseClient.PurchaseOrderClient;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import service.util.DisplayUtil;
import util.pdf.PDFRealisationUtil;

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
            List<ArticleOrder> articleOrders = (List<ArticleOrder>) GenericDAO.directQuery(ArticleOrder.class, "SELECT * FROM article_quantity_order WHERE id_purchase_order_client = "+purchaseOrderClient.getIdPurchaseOrderClient(), null);
            purchaseOrderClient.setArticleOrder(articleOrders);
            
            // Bien remplir ces données et tout doit aller automatiquement
            String dateFacturation = DisplayUtil.formatLocalDate(LocalDate.now());
            String underTitle1 = "FACTURE";
            String societe = "HUILE DE BONGOLAVA";
            String adressSupplier = "Tsiroanomandidy";
            String contactResponsable = "+261 32 124 32";
            String mailSupplier = "huileBongolava@gmail.com";
                
            String dateDelivery = DisplayUtil.formatLocalDate(purchaseOrderClient.getDeliveryDate());
            String modePaiement = purchaseOrderClient.getPaymentMethod().getPaymentMethodName();
            String adresseClient = purchaseOrderClient.getAdresse();
            String nameClient = purchaseOrderClient.getClientName();
            String contactClient = purchaseOrderClient.getContactDelivery();

                
            if(Integer.valueOf(status) == 5) { //Demander magasin
                purchaseOrderClient.setStatus(10);
                GenericDAO.updateById(purchaseOrderClient, purchaseOrderClient.getIdPurchaseOrderClient(), null);
            } else if(Integer.valueOf(status) == 15) {  //Facturer
                
                try (PDDocument document = new PDDocument()) {
                    PDPage page = new PDPage(PDRectangle.A4);
                    document.addPage(page);

                    try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                        PDFRealisationUtil outil = new PDFRealisationUtil();

                        // Header
                               contentStream.setNonStrokingColor(0, 0, 0); // Noir
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, 810, underTitle1);

                        //Description du contenu
                        int dynamicY = 790;     // pour que la hauteur s'adapte en fonction du nombres de ligne
                        int lineHeight = 20;

                        //Nom fournisseur
                        contentStream.setNonStrokingColor(150, 150, 150); // Gris sombre
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Nom du fournisseur ");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 150, dynamicY, societe);
                        
                        //Nom du client
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 350, dynamicY, "Nom du client ");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 450, dynamicY, nameClient);
                        dynamicY -= lineHeight;

                        //Email fournisseur
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Email");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 150, dynamicY, mailSupplier);
                        
                        //Adresse du client
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 350, dynamicY, "Adresse ");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 450, dynamicY, adresseClient);
                        dynamicY -= lineHeight;


                        //Contact livreur;
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Contact responsable");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 150, dynamicY, contactResponsable);
                        
                        //Contact du client
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 350, dynamicY, "Contact client");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 450, dynamicY, contactClient);
                        dynamicY -= lineHeight;

                        //Adresse fournisseur
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Adresse fournisseur");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 150, dynamicY, adressSupplier);;
                        dynamicY -= lineHeight;

                        //Date  de livraison
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Date de livraison");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 150, dynamicY, dateDelivery);
                        dynamicY -= lineHeight;
                        
                        //Date facturation
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Date facturation");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 150, dynamicY, dateFacturation);
                        dynamicY -= lineHeight;
                        
                        //Mode de paiment
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Mode de paiment");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 150, dynamicY, modePaiement);
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

                        //Liste des articles quantite
                          contentStream.setNonStrokingColor(0, 0, 0); // Noir
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Designation");
                        outil.writeText(contentStream, 110, dynamicY, "Prix unitaire");
                        outil.writeText(contentStream, 180, dynamicY, "Quantite");
                        outil.writeText(contentStream, 240, dynamicY, "Disponible");
                        outil.writeText(contentStream, 310, dynamicY, "TVA");
                        outil.writeText(contentStream, 350, dynamicY, "Montant TVA");
                        outil.writeText(contentStream, 420, dynamicY, "Montant HT");
                        outil.writeText(contentStream, 490, dynamicY, "Montant TTC");

                        dynamicY -= lineHeight;
                        dynamicY -= lineHeight;
                        contentStream.setNonStrokingColor(150, 150, 150); // Gris sombre
                        for(int i = 0; i < purchaseOrderClient.getArticleOrder().size(); i++) {
                            contentStream.setFont(PDType1Font.HELVETICA, 9);
                            outil.writeText(contentStream, 30, dynamicY, purchaseOrderClient.getArticleOrder().get(i).getArticle().getDesignation());
                            outil.writeText(contentStream, 110, dynamicY, String.valueOf("10 000 AR"));
                            outil.writeText(contentStream, 180, dynamicY, String.valueOf(purchaseOrderClient.getArticleOrder().get(i).getQuantity()));
                            outil.writeText(contentStream, 240, dynamicY, "20");
                            outil.writeText(contentStream, 310, dynamicY, DisplayUtil.formatMoney(purchaseOrderClient.getArticleOrder().get(i).getArticle().getTva(), "%"));
                            outil.writeText(contentStream, 350, dynamicY, "2000 AR");
                            outil.writeText(contentStream, 420, dynamicY, "60 000 AR");
                            outil.writeText(contentStream, 490, dynamicY, "72000AR");

                            contentStream.drawLine(20, dynamicY - 20, 570, dynamicY-20);
                            dynamicY -= lineHeight;
                            dynamicY -= lineHeight;
                        }

                        outil.writeText(contentStream, 310, dynamicY, "TOTAL");
                        outil.writeText(contentStream, 350, dynamicY, "2 000 AR");
                        outil.writeText(contentStream, 420, dynamicY, "60 000 AR");
                        outil.writeText(contentStream, 490, dynamicY, "72 000 AR");
                        contentStream.drawLine(20, dynamicY - 20, 570, dynamicY-20);
                        dynamicY -= lineHeight;
                        dynamicY -= lineHeight;
                        dynamicY -= lineHeight;
                        dynamicY -= lineHeight;
                        dynamicY -= lineHeight;

                        contentStream.setNonStrokingColor(0, 0, 0); // noir
                        //Prix total
                         contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Somme total a facturer :");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 240, dynamicY, "SOIXANTE DOUZE MILLE ARIARY");
                        dynamicY -= lineHeight;

                    }

                    if(!request.getParameter("idHelp").equals("0")) {   //Mettre a jour
                        //Mettre a jour
                        purchaseOrderClient.setStatus(20);
                        GenericDAO.updateById(purchaseOrderClient, purchaseOrderClient.getIdPurchaseOrderClient(), null);
                    }
                    
                    // Affichage a l'écran
                    document.save(response.getOutputStream());

                    } catch(Exception e) {
                        request.setAttribute("error", e.getMessage());
                        e.printStackTrace();
                    } 
                } else if(Integer.valueOf(status) == 20) {  //Livrer
                    underTitle1 = "BON DE LIVRAISON";
                    String dateBonLivraison = dateFacturation;
                    
                    try (PDDocument document = new PDDocument()) {
                    PDPage page = new PDPage(PDRectangle.A4);
                    document.addPage(page);

                    try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                        PDFRealisationUtil outil = new PDFRealisationUtil();

                        // Header
                               contentStream.setNonStrokingColor(0, 0, 0); // Noir
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, 810, underTitle1);

                        //Description du contenu
                        int dynamicY = 790;     // pour que la hauteur s'adapte en fonction du nombres de ligne
                        int lineHeight = 20;

                        //Nom fournisseur
                        contentStream.setNonStrokingColor(150, 150, 150); // Gris sombre
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Nom du fournisseur ");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 150, dynamicY, societe);
                        
                        //Nom du client
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 350, dynamicY, "Nom du client ");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 450, dynamicY, nameClient);
                        dynamicY -= lineHeight;

                        //Email fournisseur
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Email");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 150, dynamicY, mailSupplier);
                        
                        //Adresse du client
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 350, dynamicY, "Adresse ");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 450, dynamicY, adresseClient);
                        dynamicY -= lineHeight;


                        //Contact livreur;
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Contact responsable");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 150, dynamicY, contactResponsable);
                        
                        //Contact du client
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 350, dynamicY, "Contact client");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 450, dynamicY, contactClient);
                        dynamicY -= lineHeight;

                        //Adresse fournisseur
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Adresse fournisseur");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 150, dynamicY, adressSupplier);;
                        dynamicY -= lineHeight;

                        //Date  de livraison
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Date de livraison");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 150, dynamicY, dateDelivery);
                        dynamicY -= lineHeight;
                        
                        //Date facturation
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Date bon de livraison");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 150, dynamicY, dateBonLivraison);
                        dynamicY -= lineHeight;
                        
                        //Mode de paiment
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Mode de paiment");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 150, dynamicY, modePaiement);
                        dynamicY -= lineHeight;
                        dynamicY -= lineHeight;
                        
                        contentStream.setNonStrokingColor(135, 206, 250); // Bleu clair
                        
                        // Définir les coordonnées et les dimensions du rectangle
                        float x = 20;
                        float y = dynamicY - 20;
                        float width = 280;
                        float height = 40;

                        // Dessiner et colorer le rectangle
                        contentStream.fillRect(x, y, width, height);

                        //Liste des articles quantite
                         contentStream.setNonStrokingColor(0, 0, 0); // Noir
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Designation");
                        outil.writeText(contentStream, 150, dynamicY, "Quantite");
                        outil.writeText(contentStream, 250, dynamicY, "Unite");

                        dynamicY -= lineHeight;
                        dynamicY -= lineHeight;
                        contentStream.setNonStrokingColor(150, 150, 150); // Gris sombre
                        for(int i = 0; i < purchaseOrderClient.getArticleOrder().size(); i++) {
                            contentStream.setFont(PDType1Font.HELVETICA, 9);
                            outil.writeText(contentStream, 30, dynamicY, purchaseOrderClient.getArticleOrder().get(i).getArticle().getDesignation());
                            outil.writeText(contentStream, 150, dynamicY, String.valueOf(purchaseOrderClient.getArticleOrder().get(i).getQuantity()));
                            outil.writeText(contentStream, 250, dynamicY, String.valueOf(purchaseOrderClient.getArticleOrder().get(i).getArticle().getUnity().getName()));
                            
                            contentStream.drawLine(20, dynamicY - 20, 300, dynamicY-20);
                            dynamicY -= lineHeight;
                            dynamicY -= lineHeight;
                        }
                    }

                    //Mettre a jour
                    if(!request.getParameter("idHelp").equals("0")) {   //Mettre a jour
                        purchaseOrderClient.setStatus(25);
                        GenericDAO.updateById(purchaseOrderClient, purchaseOrderClient.getIdPurchaseOrderClient(), null);
                    }
        
                    // Affichage a l'écran
                    document.save(response.getOutputStream());

                    } catch(Exception e) {
                        request.setAttribute("error", e.getMessage());
                        e.printStackTrace();
                    } 
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
