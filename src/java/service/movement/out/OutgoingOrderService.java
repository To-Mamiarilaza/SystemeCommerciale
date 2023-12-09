/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.movement.out;

import connection.DBConnection;
import generalisation.GenericDAO.GenericDAO;
import generalisation.utils.GenericUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.article.Article;
import model.base.Service;
import model.movement.out.OutgoingOrder;
import model.movement.out.OutgoingOrderDetail;
import model.purchase.PurchaseOrder;

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
                PurchaseOrder purchaseOrder = (PurchaseOrder) (idPurchaseOrder == null ? null : GenericDAO.findById(PurchaseOrder.class, idPurchaseOrder, connection));
                String idService = resultSet.getString("id_service");
                Service service = (Service) (idService == null ? null : GenericDAO.findById(Service.class, idService, connection));
                Integer status = resultSet.getInt("status");

                OutgoingOrder newOrder = new OutgoingOrder(idOutgoingOrder, date, responsableName, responsableContact, motif, purchaseOrder, service, status);
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
                PurchaseOrder purchaseOrder = (PurchaseOrder) (idPurchaseOrder == null ? null : GenericDAO.findById(PurchaseOrder.class, idPurchaseOrder, connection));
                String idService = resultSet.getString("id_service");
                Service service = (Service) (idService == null ? null : GenericDAO.findById(Service.class, idService, connection));
                Integer status = resultSet.getInt("status");

                OutgoingOrder newOrder = new OutgoingOrder(idOutgoingOrder, date, responsableName, responsableContact, motif, purchaseOrder, service, status);
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
                PurchaseOrder purchaseOrder = (PurchaseOrder) (idPurchaseOrder == null ? null : GenericDAO.findById(PurchaseOrder.class, idPurchaseOrder, connection));
                String idService = resultSet.getString("id_service");
                Service service = (Service) (idService == null ? null : GenericDAO.findById(Service.class, idService, connection));
                Integer status = resultSet.getInt("status");

                order = new OutgoingOrder(id, date, responsableName, responsableContact, motif, purchaseOrder, service, status);
                
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
        String query = "UPDATE outgoing_order SET status = 10 WHERE id_outgoing_order = " + idOutgoingOrder;
        GenericDAO.directUpdate(query, null);
    }

    // save outgoing order
    public static void saveOutgoingOrder(OutgoingOrder outgoingOrder) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO outgoing_order VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, 1)";
            connection = DBConnection.getConnection();

            Integer idPurchaseOrder = outgoingOrder.getPurchaseOrder() == null ? null : outgoingOrder.getPurchaseOrder().getIdPurchaseOrder();
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
