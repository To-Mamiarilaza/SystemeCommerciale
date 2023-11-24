/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.purchase;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.base.Service;
import model.supplier.Supplier;
import service.util.DisplayUtil;

/**
 *
 * @author To Mamiarilaza
 */
@DBTable(name = "purchase_order", sequenceName = "seq_purchase_order", prefix = "BOC")
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

    @DBField(name = "total_ttc")
    double totalTTC;

    @DBField(name = "delivery_date")
    LocalDate deliveryDate;

    @DBField(name = "id_payment_method", isForeignKey = true)
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

    public String getTotalTVAString() {
        return DisplayUtil.formatMoney(getTotalTVA(), "AR");
    }
    
    public void setTotalTVA(double totalTVA) {
        this.totalTVA = totalTVA;
    }

    public double getTotalHT() {
        return totalHT;
    }
    
    public String getTotalHTString() {
        return DisplayUtil.formatMoney(getTotalHT(), "AR");
    }
    
    public double getTotalTTC() {
        return totalTTC;
    }
    
    public String getTotalTTCString() {
        return DisplayUtil.formatMoney(getTotalTTC(), "AR");
    }
    
    public String getTotalTTCLetter() {
        return DisplayUtil.toLetter((int) getTotalTTC()).toUpperCase();
    }

    public void setTotalTTC(double totalTTC) {
        this.totalTTC = totalTTC;
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
    
    public String getStatusLabel() {
        if (getStatus() == 1) {
            return "Attente";
        } else if (getStatus() == 2) {
            return "Valide";
        } else {
            return "Refuse";
        }
    }
    
    public String getStatusClass() {
        if (getStatus() == 1) {
            return "warning";
        } else if (getStatus() == 2) {
            return "success";
        } else {
            return "danger";
        }
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
        setPaymentConditions(new ArrayList<PaymentCondition>());
    }

    public PurchaseOrder(int idPurchaseOrder, LocalDate date, Supplier supplier, double totalTVA, double totalHT, double totalTTC, LocalDate deliveryDate, PaymentMethod paymentMethod, int status) {
        this.idPurchaseOrder = idPurchaseOrder;
        this.date = date;
        this.supplier = supplier;
        this.totalTVA = totalTVA;
        this.totalHT = totalHT;
        this.totalTTC = totalTTC;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
        this.status = status;

        setPaymentConditions(new ArrayList<PaymentCondition>());
    }

    public PurchaseOrder(LocalDate date, Supplier supplier, double totalTVA, double totalHT, double totalTTC, LocalDate deliveryDate, PaymentMethod paymentMethod, int status) {
        this.date = date;
        this.supplier = supplier;
        this.totalTVA = totalTVA;
        this.totalHT = totalHT;
        this.totalTTC = totalTTC;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
        setPaymentConditions(new ArrayList<PaymentCondition>());
    }

    public PurchaseOrder(int idPurchaseOrder, LocalDate date, Supplier supplier, double totalTVA, double totalHT, double totalTTC, LocalDate deliveryDate, PaymentMethod paymentMethod, int status, List<InvoiceLineItem> lineItems, List<PaymentCondition> paymentConditions) {
        this.idPurchaseOrder = idPurchaseOrder;
        this.date = date;
        this.supplier = supplier;
        this.totalTVA = totalTVA;
        this.totalHT = totalHT;
        this.totalTTC = totalTTC;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.lineItems = lineItems;
        this.paymentConditions = paymentConditions;

        setPaymentConditions(new ArrayList<PaymentCondition>());

    }

    // methods
    //get the value of all purchase
    public double montantTtc(List<PurchaseOrder> listPurchase){
        double montant = 0;
        for (int i = 0; i < listPurchase.size(); i++) {
            montant+=listPurchase.get(i).getTotalTTC();
        }
         return montant;      
    }
    
    // remove a payment condition
    public void removePaymentCondition(String part, String jour) throws Exception {
        if (part == null || part.trim().equals("")) {
            throw new Exception("Le part ne doit pas être vide !");
        }
        if (jour == null || jour.trim().equals("")) {
            throw new Exception("Le nombre de jour ne doit pas être vide !");
        }

        double partValue = Double.valueOf(part);
        int nbJour = Integer.valueOf(jour);

        LocalDate date = LocalDate.now().plusDays(nbJour);

        for (int i = 0; i < getPaymentConditions().size(); i++) {
            PaymentCondition paymentCondition = getPaymentConditions().get(i);
            if (paymentCondition.getPaymentDate().equals(date) && paymentCondition.getPercentage() == partValue) {
                getPaymentConditions().remove(paymentCondition);
                i--;
            }
        }

        displayAllPaymentCondition();
    }

    // add a payment condition to the list
    public void addPaymentCondition(String part, String jour) throws Exception {
        if (part == null || part.trim().equals("")) {
            throw new Exception("Le part ne doit pas être vide !");
        }
        if (jour == null || jour.trim().equals("")) {
            throw new Exception("Le nombre de jour ne doit pas être vide !");
        }

        double partValue = Double.valueOf(part);
        int nbJour = Integer.valueOf(jour);

        LocalDate date = LocalDate.now().plusDays(nbJour);

        PaymentCondition paymentCondition = new PaymentCondition(partValue, date);
        getPaymentConditions().add(paymentCondition);

        displayAllPaymentCondition();
    }

    public void displayAllPaymentCondition() {
        System.out.println("Les conditions de payements :");
        for (PaymentCondition paymentCondition : getPaymentConditions()) {
            System.out.println("- " + paymentCondition.getPercentage() + " : " + paymentCondition.getPaymentDate());
        }
    }

    public static void main(String[] args) throws Exception {
    }

}
