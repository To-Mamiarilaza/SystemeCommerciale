/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.sale;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import model.sale.ProformaSending;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import service.email.EmailSender;
import service.util.DisplayUtil;
import util.pdf.PDFRealisationUtil;

/**
 *
 * @author chalman
 */
@WebServlet(name = "SaveProformaSendingServlet", urlPatterns = {"/SaveProformaSending"})
public class SaveProformaSendingServlet extends HttpServlet {

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
            out.println("<title>Servlet SaveProformaSendingServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaveProformaSendingServlet at " + request.getContextPath() + "</h1>");
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
            Integer status = Integer.valueOf(request.getParameter("status"));
            HttpSession session = request.getSession();
            ProformaSending proformaSending = (ProformaSending)session.getAttribute("proformaSending");
            
            if(status == 1) {   //Envoyer

                // Bien remplir ces données et tout doit aller automatiquement
                proformaSending.setDateSending(LocalDate.now());
                String dateSending = proformaSending.getDateSending().toString();
                String underTitle1 = "PROFORMA";
                String societe = "HUILE DE BONGOLAVA";
                String adressSupplier = "Tsiroanomandidy";
                String contactResponsable = "+261 32 124 32";
                String mailSupplier = "huileBongolava@gmail.com";
                String underTitle2 = "Client";
                String mailClient = proformaSending.getEmail();

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

                        //societe description
                        contentStream.setNonStrokingColor(150, 150, 150); // Gris sombre
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Societe : ");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 100, dynamicY, societe);
                        
                        //Mail client description
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 350, dynamicY, "Email client: ");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 450, dynamicY, mailClient);
                        dynamicY -= lineHeight;

                        //Contact responsable
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Contact du responsable : ");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 150, dynamicY, contactResponsable);;
                        dynamicY -= lineHeight;


                        //Fournisseur description
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Adresse : ");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 100, dynamicY, adressSupplier);
                        dynamicY -= lineHeight;


                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        outil.writeText(contentStream, 30, dynamicY, "Mail : ");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 100, dynamicY, mailSupplier);;
                        dynamicY -= lineHeight;
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
                        for(int i = 0; i < proformaSending.getArticles().size(); i++) {
                            contentStream.setFont(PDType1Font.HELVETICA, 9);
                            outil.writeText(contentStream, 30, dynamicY, proformaSending.getArticles().get(i).getArticle().getDesignation());
                            outil.writeText(contentStream, 110, dynamicY, String.valueOf("10 000 AR"));
                            outil.writeText(contentStream, 180, dynamicY, String.valueOf(proformaSending.getArticles().get(i).getQuantity()));
                            outil.writeText(contentStream, 240, dynamicY, "20");
                            outil.writeText(contentStream, 310, dynamicY, DisplayUtil.formatMoney(proformaSending.getArticles().get(i).getArticle().getTva(), "%"));
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
                        outil.writeText(contentStream, 30, dynamicY, "Arrete le present proforma a la somme de :");
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        outil.writeText(contentStream, 240, dynamicY, "SOIXANTE DOUZE MILLE ARIARY");
                        dynamicY -= lineHeight;

                    }

                    //Sauvegarder le proforma request
                    proformaSending.save();

                    // Pour le persistance de données
                    String persistanceDirectory = getServletContext().getRealPath("").replace("build\\web\\", "web\\");

                    // Exportation en pdf
                    document.save(getServletContext().getRealPath("/proformaSendingPdf/PROF00" + proformaSending.getIdProformaSending() + ".pdf"));
                    document.save(persistanceDirectory + "\\proformaSendingPdf\\PROF00" + proformaSending.getIdProformaSending() + ".pdf");
                    String path = persistanceDirectory + "\\proformaSendingPdf\\PROF00" + proformaSending.getIdProformaSending() + ".pdf";

                    //Envoye par email
                    String[] recipients = {proformaSending.getEmail()};
                    EmailSender.sendEmail(recipients, "Demande de proforma", "Bonjour!Voici le proforma contenant les articles detailles que vous avez demande", path);

                    //Suppression des sessions
                    session.removeAttribute("proformaSending");


                    // Affichage a l'écran
                    document.save(response.getOutputStream());

                    //response.sendRedirect("./proforma-sending");
                } 

            } else {    //Refuser
                session.removeAttribute("proformaSending");
                //response.sendRedirect("./proforma-sending");
            }
        } catch(Exception e) {
            request.setAttribute("error", e.getMessage());
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
