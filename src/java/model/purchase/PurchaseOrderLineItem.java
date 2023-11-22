/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.purchase;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.utils.GenericUtil;
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
    
    @DBField(name = "quantity")
    double quantity;
    
    @DBField(name = "unit_price")
    double unitPrice;
    
    @DBField(name = "tva")
    double tva;
    
    @DBField(name = "tva_amount")
    double tvaAmount;
    
    @DBField(name = "ht_amount")
    double htAmount;
    
    @DBField(name = "ttc_amount")
    double ttcAmount;
    
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
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

    public double getTvaAmount() {
        return tvaAmount;
    }

    public void setTvaAmount(double tvaAmount) {
        this.tvaAmount = tvaAmount;
    }

    public double getHtAmount() {
        return htAmount;
    }

    public void setHtAmount(double htAmount) {
        this.htAmount = htAmount;
    }

    public double getTtcAmount() {
        return ttcAmount;
    }

    public void setTtcAmount(double ttcAmount) {
        this.ttcAmount = ttcAmount;
    }
    
    // Constructeur

    public PurchaseOrderLineItem() {
    }
    
    public PurchaseOrderLineItem(int idPurchaseOrder, Article article, double quantity, double unitPrice, double tva, double tvaAmount, double htAmount, double ttcAmount) {
        this.idPurchaseOrder = idPurchaseOrder;
        this.article = article;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.tva = tva;
        this.tvaAmount = tvaAmount;
        this.htAmount = htAmount;
        this.ttcAmount = ttcAmount;
    }
    
    public PurchaseOrderLineItem(int idPurchaseOrderLineItem, int idPurchaseOrder, Article article, double quantity, double unitPrice, double tva, double tvaAmount, double htAmount, double ttcAmount) {
        this.idPurchaseOrderLineItem = idPurchaseOrderLineItem;
        this.idPurchaseOrder = idPurchaseOrder;
        this.article = article;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.tva = tva;
        this.tvaAmount = tvaAmount;
        this.htAmount = htAmount;
        this.ttcAmount = ttcAmount;
    }
    
    public PurchaseOrderLineItem(int idPurchaseOrder, InvoiceLineItem lineItem) {
        this.idPurchaseOrder = idPurchaseOrder;
        this.article = lineItem.getArticle();
        this.quantity = lineItem.getQuantity();
        this.unitPrice = lineItem.getUnitPrice();
        this.tva = lineItem.getTva();
        this.tvaAmount = lineItem.getTVAAmount();
        this.htAmount = lineItem.getHTAmount();
        this.ttcAmount = lineItem.getTTCAmount();
    }
    
}
