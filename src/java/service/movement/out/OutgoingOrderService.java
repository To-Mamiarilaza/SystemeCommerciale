/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.movement.out;

import connection.DBConnection;
import generalisation.GenericDAO.GenericDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.base.Service;
import model.movement.out.OutgoingOrder;
import model.movement.out.OutgoingOrderDetail;
import model.purchase.PurchaseOrder;
import model.purchaseClient.PurchaseOrderClient;
import service.stock.MovementService;
import service.stock.StockService;

/**
 *
 * @author to
 */
public class OutgoingOrderService {

    // getting all outgoing order service
    public static List<OutgoingOrder> getAllOutgoingOrder(Connection connection) throws Exception {
        List<OutgoingOrder> orders = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM outgoing_order";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idOutgoingOrder = resultSet.getInt("id_outgoing_order");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                String responsableName = resultSet.getString("responsable_name");
                String responsableContact = resultSet.getString("responsable_contact");
                String motif = resultSet.getString("motif");
                String idPurchaseOrder = resultSet.getString("id_purchase_order");
                PurchaseOrderClient purchaseOrderClient = (PurchaseOrderClient) (idPurchaseOrder == null ? null : GenericDAO.findById(PurchaseOrderClient.class, idPurchaseOrder, connection));
                String idService = resultSet.getString("id_service");
                Service service = (Service) (idService == null ? null : GenericDAO.findById(Service.class, idService, connection));
                Integer status = resultSet.getInt("status");

                OutgoingOrder newOrder = new OutgoingOrder(idOutgoingOrder, date, responsableName, responsableContact, motif, purchaseOrderClient, service, status);
                orders.add(newOrder);
            }

            return orders;
        } catch (Exception e) {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            throw e;
        }
    }
    
    public static List<OutgoingOrder> getAllOutgoingOrder(String statusString, String dateString, Connection connection) throws Exception {
        List<OutgoingOrder> orders = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM outgoing_order WHERE date = '" + dateString + "' AND status = '" + statusString + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idOutgoingOrder = resultSet.getInt("id_outgoing_order");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                String responsableName = resultSet.getString("responsable_name");
                String responsableContact = resultSet.getString("responsable_contact");
                String motif = resultSet.getString("motif");
                String idPurchaseOrder = resultSet.getString("id_purchase_order");
                PurchaseOrderClient purchaseOrderClient = (PurchaseOrderClient) (idPurchaseOrder == null ? null : GenericDAO.findById(PurchaseOrderClient.class, idPurchaseOrder, connection));
                String idService = resultSet.getString("id_service");
                Service service = (Service) (idService == null ? null : GenericDAO.findById(Service.class, idService, connection));
                Integer status = resultSet.getInt("status");

                OutgoingOrder newOrder = new OutgoingOrder(idOutgoingOrder, date, responsableName, responsableContact, motif, purchaseOrderClient, service, status);
                orders.add(newOrder);
            }

            return orders;
        } catch (Exception e) {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            throw e;
        }
    }


    // Check id outgoing order
    public static int getOutgoingOrderId(String idOutgoingOrder) throws Exception {
        if (idOutgoingOrder == null || idOutgoingOrder.trim().equals("")) {
            throw new Exception("L' ID du bon de sortie ne doit pas être vide ou null !");
        }
        int id = 0;
        try {
            id = Integer.valueOf(idOutgoingOrder);
        } catch (NumberFormatException e) {
            throw e;
        }

        return id;
    }
    
    // get an outgoing order detail
    public static OutgoingOrder getOutgoingOrder(String idOutgoingOrder, Connection connection) throws Exception {
        int id = getOutgoingOrderId(idOutgoingOrder);
        
        OutgoingOrder order = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM outgoing_order WHERE id_outgoing_order = " + id;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                LocalDate date = resultSet.getDate("date").toLocalDate();
                String responsableName = resultSet.getString("responsable_name");
                String responsableContact = resultSet.getString("responsable_contact");
                String motif = resultSet.getString("motif");
                String idPurchaseOrder = resultSet.getString("id_purchase_order");
                PurchaseOrderClient purchaseOrderClient = (PurchaseOrderClient) (idPurchaseOrder == null ? null : GenericDAO.findById(PurchaseOrderClient.class, idPurchaseOrder, connection));
                String idService = resultSet.getString("id_service");
                Service service = (Service) (idService == null ? null : GenericDAO.findById(Service.class, idService, connection));
                Integer status = resultSet.getInt("status");

                order = new OutgoingOrder(id, date, responsableName, responsableContact, motif, purchaseOrderClient, service, status);
                
                // get all outgoing order detail
                order.setDetails(GenericDAO.getAll(OutgoingOrderDetail.class, "WHERE id_outgoing_order = " + id, connection));
            } else {
                throw new Exception("Il n'y a aucun outgoing portant cette id");
            }

            return order;
        } catch (Exception e) {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            throw e;
        }
    }

    // refuse an outgoing order
    public static void refuseOutgoingOrder(String idOutgoingOrder) throws Exception {
        String query = "UPDATE outgoing_order SET status = 0 WHERE id_outgoing_order = " + idOutgoingOrder;
        GenericDAO.directUpdate(query, null);
    }
    
    // accept an outgoing order
    public static void acceptOutgoingOrder(String idOutgoingOrder) throws Exception {
        Connection connection = DBConnection.getConnection();
        OutgoingOrder order = getOutgoingOrder(idOutgoingOrder, connection);
        
        // Update the purchase order client status to 15
        PurchaseOrderClient clientOrder = order.getPurchaseOrderClient();
        clientOrder.setStatus(15);
        GenericDAO.updateById(clientOrder, clientOrder.getIdPurchaseOrderClient(), connection);
        
        // Sortir du magasin
        for (OutgoingOrderDetail detail : order.getDetails()) {
            MovementService.sortir(LocalDate.now().toString(), String.valueOf(order.getIdOutgoingOrder()), String.valueOf(detail.getArticle().getIdArticle()), String.valueOf(detail.getQuantity()), connection);
        }
        
        String query = "UPDATE outgoing_order SET status = 10 WHERE id_outgoing_order = " + idOutgoingOrder;
        GenericDAO.directUpdate(query, connection);
        
        connection.commit();
        connection.close();
    }
    
    // get all id in a outgoing order to in sql format
    public static String getAllId(OutgoingOrder outgoingOrder) {
        String result = "";
        for (OutgoingOrderDetail detail : outgoingOrder.getDetails()) {
            result += detail.getArticle().getIdArticle() + ", ";
        }
        result = result.substring(0, result.length() - 2);
        return result;
    }
     
    // Disable service request
    public static void disableServiceRequest(OutgoingOrder outgoingOrder, Connection connection) throws Exception {
        String findIdQuery = "SELECT id_article_quantity FROM v_service_valid_request WHERE id_service = %d AND id_article IN (%s)";
        findIdQuery = String.format(findIdQuery, outgoingOrder.getService().getIdService(), getAllId(outgoingOrder));
        String query = "UPDATE article_quantity SET status = 3 WHERE id_article_quantity IN (" + findIdQuery + ")";
        GenericDAO.directUpdate(query, connection);
    }

    // save outgoing order
    public static void saveOutgoingOrder(OutgoingOrder outgoingOrder) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO outgoing_order VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, 1)";
            connection = DBConnection.getConnection();

            Integer idPurchaseOrder = outgoingOrder.getPurchaseOrderClient()== null ? null : outgoingOrder.getPurchaseOrderClient().getIdPurchaseOrderClient();
            Integer idService = outgoingOrder.getService() == null ? null : outgoingOrder.getService().getIdService();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, Date.valueOf(outgoingOrder.getDate()));
            statement.setString(2, outgoingOrder.getResponsableName());
            statement.setString(3, outgoingOrder.getResponsableContact());
            statement.setString(4, outgoingOrder.getMotif());
            statement.setObject(5, idPurchaseOrder);
            statement.setObject(6, idService);
            statement.execute();

            // Get the last inserted id
            ResultSet newKey = statement.getGeneratedKeys();
            newKey.next();
            int keyValue = newKey.getInt(1);

            // Save all detail
            for (OutgoingOrderDetail detail : outgoingOrder.getDetails()) {
                detail.setIdOutgoingOrder(keyValue);
                GenericDAO.save(detail, connection);
            }
            
            // Disable service request
            if (outgoingOrder.getService() != null) {
                disableServiceRequest(outgoingOrder, connection);
            }
            
            
            // Update the status of the purchase_order_client
//            if (outgoingOrder.getPurchaseOrderClient() != null) {
//                outgoingOrder.getPurchaseOrderClient().setStatus(15);
//                GenericDAO.save(outgoingOrder.getPurchaseOrderClient(), connection);
//            }

            connection.commit();
            connection.close();
        } catch (Exception e) {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.rollback();
                connection.commit();
            }
            throw e;
        }

    }

    // Main method for testing
    public static void main(String[] args) throws Exception {
//        PurchaseOrder order = new PurchaseOrder();
//        order.setIdPurchaseOrder(5);
//        
//        Service service = new Service();
//        service.setIdService(7);
//        
//        Article article = new Article();
//        article.setIdArticle(2);
//        
//        Article article1 = new Article();
//        article1.setIdArticle(3);
//        
//        List<OutgoingOrderDetail> details = new ArrayList<>();
//        details.add(new OutgoingOrderDetail(0, article, 12));
//        details.add(new OutgoingOrderDetail(0, article1, 8));
//        
//        OutgoingOrder outgoingOrder = new OutgoingOrder(LocalDate.now(), "MAMIARILAZA To Niasimandimby", "+261 34 14 517 43", "COUCOU", order, service, 1);
//        outgoingOrder.setDetails(details);
//        saveOutgoingOrder(outgoingOrder);
    
            acceptOutgoingOrder("14");

    }
}
