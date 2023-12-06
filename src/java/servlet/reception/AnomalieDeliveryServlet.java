/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.reception;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import model.anomalie.TypeAnomalie;
import model.reception.SupplierDeliveryOrder;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "AnomalieDeliveryServlet", urlPatterns = {"/add-anomalie-delivery"})
public class AnomalieDeliveryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<SupplierDeliveryOrder> lastDelivery = (List<SupplierDeliveryOrder>) GenericDAO.directQuery(SupplierDeliveryOrder.class, "select * from supplier_delivery_order order by id_supplier_delivery_order desc", null);

            String explication = request.getParameter("explication");

            String detailAnomalieJson = request.getParameter("detailAnomalie");
            List<String> details = parseJsonArray(detailAnomalieJson);

            Anomalie a = new Anomalie();
            List<TypeAnomalie> anomalyType = (List<TypeAnomalie>) GenericDAO.directQuery(TypeAnomalie.class, "select * from type_anomalie where type_anomalie = 'BDL'", null);
            a.setTypeAnomalie(anomalyType.get(0));
            a.setExplication(explication);
            if (lastDelivery.size() > 0) {
                a.setId((lastDelivery.get(0).getIdSupplierDeliveryOrder() + 1));
            }
            else {
                a.setId(1);
            }
            GenericDAO.save(a, null);

            List<Anomalie> lastAnomalie = (List<Anomalie>) GenericDAO.directQuery(Anomalie.class, "select * from anomalie order by id_anomalie desc limit 1", null);

            System.out.println("insertion des details");
            
            for (int i = 0; i < details.size(); i++) {
                DetailAnomalie detail = new DetailAnomalie();
                detail.setAnomalie((Anomalie) GenericDAO.findById(Anomalie.class, lastAnomalie.get(0).getIdAnomalie(), null));
                detail.setDetail(details.get(i));
                GenericDAO.save(detail, null);
            }
            System.out.println("finition des insertions des details");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("./delivery-order-insertion?error=" + ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private List<String> parseJsonArray(String jsonArray) {
        Gson gson = new Gson();
        java.lang.reflect.Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        return gson.fromJson(jsonArray, listType);
    }
}
