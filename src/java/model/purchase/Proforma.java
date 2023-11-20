/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.purchase;

import java.time.LocalDate;
import java.util.List;
import model.supplier.Supplier;
import service.util.DisplayUtil;

/**
 *
 * @author To Mamiarilaza
 */
public class Proforma {
    // Field
    LocalDate date;
    Supplier supplier;
    double totalTVA;
    double totalHT;
    double totalTTC;
    List<InvoiceLineItem> invoiceLineItems;
    
    // Getter ans setter

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

    public void setTotalHT(double totalHT) {
        this.totalHT = totalHT;
    }

    public double getTotalTTC() {
        return totalTTC;
    }
    
    public String getTotalTTCString() {
        return DisplayUtil.formatMoney(getTotalTTC(), "AR");
    }
    
    public String getTotalTTCToLetter() {
        return DisplayUtil.toLetter((int) getTotalTTC()).toUpperCase();
    }

    public void setTotalTTC(double totalTTC) {
        this.totalTTC = totalTTC;
    }

    public List<InvoiceLineItem> getInvoiceLineItems() {
        return invoiceLineItems;
    }

    public void setInvoiceLineItems(List<InvoiceLineItem> invoiceLineItems) {
        this.invoiceLineItems = invoiceLineItems;
    }
    
    // Constructor

    public Proforma() {
    }

    public Proforma(LocalDate date, Supplier supplier, double totalTVA, double totalHT, double totalTTC, List<InvoiceLineItem> invoiceLineItems) {
        this.date = date;
        this.supplier = supplier;
        this.totalTVA = totalTVA;
        this.totalHT = totalHT;
        this.totalTTC = totalTTC;
        this.invoiceLineItems = invoiceLineItems;
    }
    
}
