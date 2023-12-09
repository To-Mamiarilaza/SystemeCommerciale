/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.movement.out;

import generalisation.GenericDAO.GenericDAO;
import java.sql.Connection;
import java.util.List;
import model.base.Service;
import model.movement.out.ServiceRequest;
import model.purchase.ArticleQuantity;

/**
 *
 * @author to
 */
public class ServiceRequestService {
    // All method for managing service request
    
    public static List<ServiceRequest> getAllServiceRequest(Connection connection) throws Exception {
        String query = "SELECT DISTINCT id_service FROM v_service_valid_request";
        List<ServiceRequest> requests = (List<ServiceRequest>) GenericDAO.directQuery(ServiceRequest.class, query, connection);
        for (ServiceRequest request : requests) {
            query = "SELECT * FROM v_service_valid_request WHERE id_service = " + request.getService().getIdService();
            List<ArticleQuantity> articleQuantites = (List<ArticleQuantity>) GenericDAO.directQuery(ArticleQuantity.class, query, connection);
            request.setArticleQuantities(articleQuantites);
        }
        return requests;
    }
    
    public static ServiceRequest getServiceRequest(String idService, Connection connection) throws Exception {
        String query = "SELECT DISTINCT id_service FROM v_service_valid_request WHERE id_service = " + idService;
        List<ServiceRequest> requests = (List<ServiceRequest>) GenericDAO.directQuery(ServiceRequest.class, query, connection);
        for (ServiceRequest request : requests) {
            query = "SELECT * FROM v_service_valid_request WHERE id_service = " + request.getService().getIdService();
            List<ArticleQuantity> articleQuantites = (List<ArticleQuantity>) GenericDAO.directQuery(ArticleQuantity.class, query, connection);
            request.setArticleQuantities(articleQuantites);
        }
        if (requests.size() == 0) {
            throw new Exception("Il n'y a aucun commande validé pour ce service !");
        }
        return requests.get(0);
    }
}
