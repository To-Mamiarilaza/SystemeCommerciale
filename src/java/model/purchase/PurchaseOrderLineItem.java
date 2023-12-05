/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.purchase;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.src.generalisation.utils.GenericUtil;
import model.article.Article;

/**
 *
 * @author To Mamiarilaza
 */
@DBTable(name = "purchase_order_line_item", sequenceName = "seq_purchase_order_line_item")
public class PurchaseOrderLineItem {
    // Field
    @DBField(name = "id_purchase_order_line_item", isPrimaryKey = true)
    int idPurchaseOrderLineItem;
    
    @DBField(name = "id_purchase_order")
    int idPurchaseOrder;
    
    @DBField(name = "id_article", isForeignKey = true)
    Article article;
    
    @DBField(name = "unit_price")
    double unitPrice;
    
    @DBField(name = "tva")
    double tva;
    
    @DBField(name = "tva_amount")
    double tva_amount;
    
    @DBField(name = "ht_amount")
    double ht_amount;
    
    @DBField(name = "ttc_amount")
    double ttc_amount;
    
    // Getter and setter

    public int getIdPurchaseOrderLineItem() {
        return idPurchaseOrderLineItem;
    }

    public void setIdPurchaseOrderLineItem(int idPurchaseOrderLineItem) {
        this.idPurchaseOrderLineItem = idPurchaseOrderLineItem;
    }

    public int getIdPurchaseOrder() {
        return idPurchaseOrder;
    }

    public void setIdPurchaseOrder(int idPurchaseOrder) {
        this.idPurchaseOrder = idPurchaseOrder;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public double getTva_amount() {
        return tva_amount;
    }

    public void setTva_amount(double tva_amount) {
        this.tva_amount = tva_amount;
    }

    public double getHt_amount() {
        return ht_amount;
    }

    public void setHt_amount(double ht_amount) {
        this.ht_amount = ht_amount;
    }

    public double getTtc_amount() {
        return ttc_amount;
    }

    public void setTtc_amount(double ttc_amount) {
        this.ttc_amount = ttc_amount;
    }
    
    // Constructor

    public PurchaseOrderLineItem() {
    }
    
    public PurchaseOrderLineItem(int idPurchaseOrder, Article article, double unitPrice, double tva, double tva_amount, double ht_amount, double ttc_amount) {
        this.idPurchaseOrder = idPurchaseOrder;
        this.article = article;
        this.unitPrice = unitPrice;
        this.tva = tva;
        this.tva_amount = tva_amount;
        this.ht_amount = ht_amount;
        this.ttc_amount = ttc_amount;
    }
    
    public PurchaseOrderLineItem(int idPurchaseOrderLineItem, int idPurchaseOrder, Article article, double unitPrice, double tva, double tva_amount, double ht_amount, double ttc_amount) {
        this.idPurchaseOrderLineItem = idPurchaseOrderLineItem;
        this.idPurchaseOrder = idPurchaseOrder;
        this.article = article;
        this.unitPrice = unitPrice;
        this.tva = tva;
        this.tva_amount = tva_amount;
        this.ht_amount = ht_amount;
        this.ttc_amount = ttc_amount;
    }
    
    public PurchaseOrderLineItem(int idPurchaseOrder, InvoiceLineItem invoiceLineItem) {
        this.idPurchaseOrder = idPurchaseOrder;
        this.article = invoiceLineItem.getArticle();
        this.unitPrice = invoiceLineItem.getUnitPrice();
        this.tva = invoiceLineItem.getTva();
        this.tva_amount = invoiceLineItem.getTVAAmount();
        this.ht_amount = invoiceLineItem.getHTAmount();
        this.ttc_amount = invoiceLineItem.getTTCAmount();
    }
    
    public static void main(String[] args) throws Exception {
        GenericUtil.detailList(GenericDAO.getAll(PurchaseOrderLineItem.class, null, null));
    }
}
