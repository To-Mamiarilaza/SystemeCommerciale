/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.proforma;

import connection.DBConnection;
import generalisation.GenericDAO.GenericDAO;
import generalisation.utils.GenericUtil;
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
        List<PurchaseOrder> list = getAllPurchaseOrder(null);
        for (PurchaseOrder purchaseOrder : list) {
            System.out.println("---------->");
            GenericUtil.detailObjet(purchaseOrder);

            System.out.println("- payment method : ");
            GenericUtil.detailList(purchaseOrder.getPaymentConditions());

            System.out.println("- Line item");
            GenericUtil.detailList(purchaseOrder.getLineItems());

        }
    }
}
