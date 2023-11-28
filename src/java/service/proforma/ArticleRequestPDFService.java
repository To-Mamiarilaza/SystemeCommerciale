/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.proforma;

import java.io.IOException;
import java.util.List;
import model.purchase.ArticleRequest;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import util.pdf.PDFRealisationUtil;

/**
 *
 * @author to
 */
public class ArticleRequestPDFService {

    public static PDDocument getArticleRequestPDF(List<ArticleRequest> articleRequests) throws IOException {
        PDDocument document = new PDDocument();

        // Setting the page
        PDPage page = new PDPage(PDRectangle.A6);
        document.addPage(page);

        // Writing in the document
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            PDFRealisationUtil outil = new PDFRealisationUtil();

            int dynamicY = 380;
            int lineHeight = 12;
            int leftMargin = 20;

            // Title
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 11);
            PDFRealisationUtil.writeText(contentStream, leftMargin + 50, dynamicY, "DEMANDE DE PROFORMA");
            dynamicY -= lineHeight;
            dynamicY -= lineHeight;
            dynamicY -= lineHeight;

            // Senders informations
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            PDFRealisationUtil.writeText(contentStream, leftMargin, dynamicY, "HUILE DE BONGOLAVA");
            dynamicY -= lineHeight;
            PDFRealisationUtil.writeText(contentStream, leftMargin, dynamicY, "Tsiroanomandidy 119, Madagascar");
            dynamicY -= lineHeight;
            PDFRealisationUtil.writeText(contentStream, leftMargin, dynamicY, "huile.bongolava@gmail.com");
            dynamicY -= lineHeight;
            PDFRealisationUtil.writeText(contentStream, leftMargin, dynamicY, "+261 34 14 517 43");

            dynamicY -= lineHeight;
            dynamicY -= lineHeight;

            // A subject text
            contentStream.setFont(PDType1Font.HELVETICA, 8);
            String paragraph = "Nous apprécions grandement votre collaboration continue avec nous. Dans le cadre de notre processus de commande, nous sollicitons un proforma pour la commande suivante :";
            PDFRealisationUtil.writeMultilineText(contentStream, leftMargin, dynamicY, paragraph, lineHeight, 50);
            dynamicY -= lineHeight;
            dynamicY -= lineHeight;
            dynamicY -= lineHeight;
            dynamicY -= lineHeight;

            // Article request detail
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
            for (ArticleRequest articleRequest : articleRequests) {
                PDFRealisationUtil.writeText(contentStream, leftMargin, dynamicY, "-");
                PDFRealisationUtil.writeText(contentStream, leftMargin + 10, dynamicY, articleRequest.getArticle().getDesignation());
                PDFRealisationUtil.writeText(contentStream, leftMargin + 100, dynamicY, String.valueOf(articleRequest.getQuantity()));
                PDFRealisationUtil.writeText(contentStream, leftMargin + 150, dynamicY, String.valueOf(articleRequest.getArticle().getUnity().getName()));
                dynamicY -= lineHeight;
            }
            dynamicY -= lineHeight;

            contentStream.setFont(PDType1Font.HELVETICA, 8);
            paragraph = "Pourriez-vous s'il vous plaît nous fournir le proforma correspondant dès que possible? Si vous avez besoin de plus d'informations ou de clarifications, n'hésitez pas à me contacter directement.";
            PDFRealisationUtil.writeMultilineText(contentStream, leftMargin, dynamicY, paragraph, lineHeight, 60);
            dynamicY -= lineHeight;
            dynamicY -= lineHeight;
            dynamicY -= lineHeight;
            dynamicY -= lineHeight;

            contentStream.setFont(PDType1Font.HELVETICA, 8);
            paragraph = "Nous vous remercions par avance pour votre coopération et restons à votre disposition pour toute question.";
            PDFRealisationUtil.writeMultilineText(contentStream, leftMargin, dynamicY, paragraph, lineHeight, 60);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return document;
    }
}
