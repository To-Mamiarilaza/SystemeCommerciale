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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.base.Utilisateur;
import model.reception.ArticleDetails;
import model.reception.DeliveryArticleDetails;
import model.reception.ReceptionOrder;
import model.reception.SupplierDeliveryOrder;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "ReceptionOrderInsertionServlet", urlPatterns = {"/reception-order-insertion"})
public class ReceptionOrderInsertionServlet extends HttpServlet {

    List<String> anomalies = new ArrayList<>();

    public List<String> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(List<String> anomalies) {
        this.anomalies = anomalies;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            if (utilisateur == null) {
                response.sendRedirect("./login");
            }
            SupplierDeliveryOrder delivery = (SupplierDeliveryOrder) request.getSession().getAttribute("supplierDeliveryOrder");
            request.setAttribute("utilisateur", utilisateur);
            ReceptionOrder reception = new ReceptionOrder();
            reception.setListeArticles(delivery.getListeArticles());
            request.getSession().setAttribute("reception", reception);
            request.getSession().setAttribute("anomalies", this.getAnomalies());

            // All required assets
            List<String> css = new ArrayList<>();
            css.add("assets/css/supplier/supplier.css");

            List<String> js = new ArrayList<>();
            js.add("assets/js/bootstrap.bundle.min.js");
            js.add("assets/js/delivery/delivery.js");

            request.setAttribute("css", css);
            request.setAttribute("js", js);
            request.setAttribute("delivery", delivery);
            request.setAttribute("reception", reception);

            // Page definition
            request.setAttribute("title", "Insertion bon de livraison");
            request.setAttribute("contentPage", "./pages/delivery/receptionOrderInsertion.jsp");

            request.getRequestDispatcher("./template.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SupplierDeliveryOrder delivery = (SupplierDeliveryOrder) request.getSession().getAttribute("supplierDeliveryOrder");

            LocalDate date = LocalDate.parse(request.getParameter("date"));
            String nomResponsable = request.getParameter("responsable");
            String contactResponsable = request.getParameter("responsableContact");

            ReceptionOrder reception = (ReceptionOrder) request.getSession().getAttribute("reception");

            reception.setReceptionDate(date);
            reception.setResponsableContact(contactResponsable);
            reception.setResponsableName(nomResponsable);
            reception.setStatus(1);
            delivery.setStatus(1);

            GenericDAO.save(delivery, null);

            List<SupplierDeliveryOrder> lastDelivery = (List<SupplierDeliveryOrder>) GenericDAO.directQuery(SupplierDeliveryOrder.class, "select * from supplier_delivery_order order by id_supplier_delivery_order desc", null);

            reception.setDeliveryOrder(lastDelivery.get(0));
            System.out.println("delivery size = " + delivery.getListeArticles().size());
            for (int i = 0; i < delivery.getListeArticles().size(); i++) {
                GenericDAO.directUpdate("insert into supplier_delivery_details values (default, " + lastDelivery.get(0).getIdSupplierDeliveryOrder() + ", " + delivery.getListeArticles().get(i).getArticle().getIdArticle() + ", " + delivery.getListeArticles().get(i).getQuantity() + ")", null);
            }

            List<String> annomalies = reception.checkAnomalie(delivery.getListeArticles(), reception.getListeArticles());
            if (!annomalies.isEmpty()) {
                this.setAnomalies(annomalies);
                System.out.println("annomalies : " + annomalies.get(0));
                System.out.println(" -- - - - - - - Size Anomaly : " + this.getAnomalies().size());
                response.sendRedirect("./reception-order-insertion");
            } else {
                GenericDAO.save(reception, null);

                List<ReceptionOrder> lastReception = (List<ReceptionOrder>) GenericDAO.directQuery(ReceptionOrder.class, "select * from reception_order order by id_reception_order desc limit 1", null);
                for (int i = 0; i < reception.getListeArticles().size(); i++) {
                    GenericDAO.directUpdate("insert into reception_article_details values (default, " + lastReception.get(0).getIdReceptionOrder() + ", " + reception.getListeArticles().get(i).getArticle().getIdArticle() + ", " + reception.getListeArticles().get(i).getQuantity() + ")", null);
                }

                request.getSession().removeAttribute("supplierDeliveryOrder");
                response.sendRedirect("./reception-list");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
