/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.proforma;

import connection.DBConnection;
import generalisation.GenericDAO.GenericDAO;
import generalisation.src.generalisation.utils.GenericUtil;
import java.sql.Connection;
import java.util.List;
import model.purchase.InvoiceLineItem;
import model.purchase.PaymentCondition;
import model.purchase.PurchaseOrder;

/**
 *
 * @author To Mamiarilaza
 */
public class PurchaseOrderService {
    
    // change purchase order status
    public static void changePurchaseOrderStatus(int idPurchaseOrder, int status, Connection connection) throws Exception {
        PurchaseOrder purchaseOrder = GenericDAO.findById(PurchaseOrder.class, idPurchaseOrder, connection);
        purchaseOrder.setStatus(status);
        
        GenericDAO.updateById(purchaseOrder, purchaseOrder.getIdPurchaseOrder(), connection);
    }

    public static void validatePurchaseOrder(int idPurchaseOrder) throws Exception {
        Connection connection = DBConnection.getConnection();
        changePurchaseOrderStatus(idPurchaseOrder, 2, connection);
        
        connection.commit();
        connection.close();
    }
    
    public static void refusePurchaseOrder(int idPurchaseOrder) throws Exception {
        Connection connection = DBConnection.getConnection();
        changePurchaseOrderStatus(idPurchaseOrder, 0, connection);
        
        connection.commit();
        connection.close();
    }
    
    // get all purchase valid
    public static List<PurchaseOrder> getAllPurchaseOrder(Connection connection) throws Exception {
        List<PurchaseOrder> purchaseOrders = (List<PurchaseOrder>) GenericDAO.getAll(PurchaseOrder.class, null, null);
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            // set all invoice line
            String query = "SELECT * FROM purchase_order_line_item WHERE id_purchase_order = " + purchaseOrder.getIdPurchaseOrder();
            List<InvoiceLineItem> lines = (List<InvoiceLineItem>) GenericDAO.directQuery(InvoiceLineItem.class, query, connection);
            purchaseOrder.setLineItems(lines);

            // set all payment condition
            String whereClause = "WHERE id_purchase_order = " + purchaseOrder.getIdPurchaseOrder();
            List<PaymentCondition> paymentConditions = (List<PaymentCondition>) GenericDAO.getAll(PaymentCondition.class, whereClause, connection);
            purchaseOrder.setPaymentConditions(paymentConditions);
        }

        return purchaseOrders;
    }

    public static PurchaseOrder getPurchaseOrder(int idPurchaseOrder) throws Exception {
        Connection connection = DBConnection.getConnection();
        PurchaseOrder purchaseOrder = GenericDAO.findById(PurchaseOrder.class, idPurchaseOrder, connection);
        
        // set all invoice line
        String query = "SELECT * FROM purchase_order_line_item WHERE id_purchase_order = " + purchaseOrder.getIdPurchaseOrder();
        List<InvoiceLineItem> lines = (List<InvoiceLineItem>) GenericDAO.directQuery(InvoiceLineItem.class, query, connection);
        purchaseOrder.setLineItems(lines);

        // set all payment condition
        String whereClause = "WHERE id_purchase_order = " + purchaseOrder.getIdPurchaseOrder();
        List<PaymentCondition> paymentConditions = (List<PaymentCondition>) GenericDAO.getAll(PaymentCondition.class, whereClause, connection);
        purchaseOrder.setPaymentConditions(paymentConditions);

        connection.close();
        
        return purchaseOrder;
    }

    public static void main(String[] args) throws Exception {
        refusePurchaseOrder(24);
    }
}
