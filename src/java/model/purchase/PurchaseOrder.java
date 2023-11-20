/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.purchase;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.utils.GenericUtil;
import java.time.LocalDate;
import java.util.List;
import model.supplier.Supplier;

/**
 *
 * @author To Mamiarilaza
 */
@DBTable(name = "purchase_order", sequenceName = "seq_purchase_order")
public class PurchaseOrder {
    // Field
    @DBField(name = "id_purchase_order", isPrimaryKey = true)
    int idPurchaseOrder;
    
    @DBField(name = "date")
    LocalDate date;
    
    @DBField(name = "id_supplier", isForeignKey = true)
    Supplier supplier;
    
    @DBField(name = "total_tva")
    double totalTVA;
    
    @DBField(name = "total_ht")
    double totalHT;
    
    @DBField(name = "delivery_date")
    LocalDate deliveryDate;
    
    @DBField(name = "id_payment_method")
    PaymentMethod paymentMethod;
    
    @DBField(name = "status")
    int status;
    
    List<InvoiceLineItem> lineItems;
    List<PaymentCondition> paymentConditions;
    
    // Getter and setter

    public int getIdPurchaseOrder() {
        return idPurchaseOrder;
    }

    public void setIdPurchaseOrder(int idPurchaseOrder) {
        this.idPurchaseOrder = idPurchaseOrder;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public double getTotalTVA() {
        return totalTVA;
    }

    public void setTotalTVA(double totalTVA) {
        this.totalTVA = totalTVA;
    }

    public double getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(double totalHT) {
        this.totalHT = totalHT;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<InvoiceLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<InvoiceLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public List<PaymentCondition> getPaymentConditions() {
        return paymentConditions;
    }

    public void setPaymentConditions(List<PaymentCondition> paymentConditions) {
        this.paymentConditions = paymentConditions;
    }
    
    // Constructor

    public PurchaseOrder() {
    }

    public PurchaseOrder(int idPurchaseOrder, LocalDate date, Supplier supplier, double totalTVA, double totalHT, LocalDate deliveryDate, PaymentMethod paymentMethod, int status) {
        this.idPurchaseOrder = idPurchaseOrder;
        this.date = date;
        this.supplier = supplier;
        this.totalTVA = totalTVA;
        this.totalHT = totalHT;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public PurchaseOrder(LocalDate date, Supplier supplier, double totalTVA, double totalHT, LocalDate deliveryDate, PaymentMethod paymentMethod, int status) {
        this.date = date;
        this.supplier = supplier;
        this.totalTVA = totalTVA;
        this.totalHT = totalHT;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }
    
    public PurchaseOrder(int idPurchaseOrder, LocalDate date, Supplier supplier, double totalTVA, double totalHT, LocalDate deliveryDate, PaymentMethod paymentMethod, int status, List<InvoiceLineItem> lineItems, List<PaymentCondition> paymentConditions) {
        this.idPurchaseOrder = idPurchaseOrder;
        this.date = date;
        this.supplier = supplier;
        this.totalTVA = totalTVA;
        this.totalHT = totalHT;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.lineItems = lineItems;
        this.paymentConditions = paymentConditions;
    }
    
}
