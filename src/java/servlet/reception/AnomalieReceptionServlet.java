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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.anomalie.Anomalie;
import model.anomalie.DetailAnomalie;
import model.anomalie.TypeAnomalie;
import model.reception.ReceptionOrder;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "AnomalieReceptionServlet", urlPatterns = {"/add-reception-anomalie"})
public class AnomalieReceptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<ReceptionOrder> lastReception = (List<ReceptionOrder>) GenericDAO.directQuery(ReceptionOrder.class, "select * from reception_order order by id_reception_order desc limit 1", null);

            String explication = request.getParameter("explication");

            String detailAnomalieJson = request.getParameter("detailAnomalie");
            List<String> details = parseJsonArray(detailAnomalieJson);

            Anomalie a = new Anomalie();
            List<TypeAnomalie> anomalyType = (List<TypeAnomalie>) GenericDAO.directQuery(TypeAnomalie.class, "select * from type_anomalie where type_anomalie = 'BDR'", null);
            a.setTypeAnomalie(anomalyType.get(0));
            a.setExplication(explication);
            if (lastReception.size() > 0) {
                a.setId((lastReception.get(0).getIdReceptionOrder()+ 1));
            } else {
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
        }
    }

    private List<String> parseJsonArray(String jsonArray) {
        Gson gson = new Gson();
        java.lang.reflect.Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        return gson.fromJson(jsonArray, listType);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
