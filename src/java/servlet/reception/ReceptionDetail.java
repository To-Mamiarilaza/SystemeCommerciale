/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.reception;

import generalisation.GenericDAO.GenericDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.anomalie.Anomalie;
import model.anomalie.DetailAnomalie;
import model.base.Utilisateur;
import model.reception.DeliveryArticleDetails;
import model.reception.ReceptionArticleDetails;
import model.reception.ReceptionOrder;

/**
 *
 * @author to
 */
@WebServlet(name = "ReceptionDetail", urlPatterns = {"/reception-detail"})
public class ReceptionDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            if (utilisateur == null) {
                response.sendRedirect("./login");
            }
            request.setAttribute("utilisateur", utilisateur);
            int idReception = Integer.valueOf(request.getParameter("idReception"));
            ReceptionOrder reception = (ReceptionOrder) GenericDAO.findById(ReceptionOrder.class, idReception, null);
            List<ReceptionArticleDetails> receptionArticle = (List<ReceptionArticleDetails>) GenericDAO.directQuery(ReceptionArticleDetails.class, "select * from reception_article_details where id_reception_order = " + reception.getIdReceptionOrder(), null);
            List<DeliveryArticleDetails> deliveryArticle = (List<DeliveryArticleDetails>) GenericDAO.directQuery(DeliveryArticleDetails.class, "select * from supplier_delivery_details where id_supplier_delivery_order = " + reception.getDeliveryOrder().getIdSupplierDeliveryOrder(), null);
            // anomalie de livraison
            List<Anomalie> anomalyDelivery = (List<Anomalie>) GenericDAO.directQuery(Anomalie.class, "select * from anomalie where id_type_anomalie = 1 and id = " + reception.getDeliveryOrder().getIdSupplierDeliveryOrder(), null);
            List<DetailAnomalie> anomalyDeliveryDetails = new ArrayList<>();
            if (!anomalyDelivery.isEmpty()) {
                anomalyDeliveryDetails = (List<DetailAnomalie>) GenericDAO.directQuery(DetailAnomalie.class, "select * from detail_anomalie where id_anomalie = " + anomalyDelivery.get(0).getIdAnomalie(), null);
            }
            // anomalie de reception
            List<Anomalie> anomalyReception = (List<Anomalie>) GenericDAO.directQuery(Anomalie.class, "select * from anomalie where id_type_anomalie = 2 and id = " + reception.getDeliveryOrder().getIdSupplierDeliveryOrder(), null);
            List<DetailAnomalie> anomalyReceptionDetails = new ArrayList<>();
            if (!anomalyReception.isEmpty()) {
                anomalyReceptionDetails = (List<DetailAnomalie>) GenericDAO.directQuery(DetailAnomalie.class, "select * from detail_anomalie where id_anomalie = " + anomalyDelivery.get(0).getIdAnomalie(), null);
            }

            request.setAttribute("reception", reception);
            request.setAttribute("receptionArticle", receptionArticle);
            request.setAttribute("deliveryArticle", deliveryArticle);
            request.setAttribute("anomalyDelivery", anomalyDelivery);
            request.setAttribute("anomalyDeliveryDetails", anomalyDeliveryDetails);
            request.setAttribute("anomalyReception", anomalyReception);
            request.setAttribute("anomalyReceptionDetails", anomalyReceptionDetails);
            // All required assets
            List<String> css = new ArrayList<>();
            css.add("assets/css/supplier/supplier.css");

            List<String> js = new ArrayList<>();
            js.add("assets/js/bootstrap.bundle.min.js");

            request.setAttribute("css", css);
            request.setAttribute("js", js);

            // Page definition
            request.setAttribute("title", "Insertion bon de livraison");
            request.setAttribute("contentPage", "./pages/delivery/receptionDetail.jsp");

            request.getRequestDispatcher("./template.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
