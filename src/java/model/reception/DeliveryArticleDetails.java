/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.reception;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import model.article.Article;

/**
 *
 * @author Fy Botas
 */
@DBTable(name = "supplier_delivery_details", sequenceName = "seq_supplier_delivery_details")
public class DeliveryArticleDetails {

    @DBField(name = "id_supplier_delivery_details", isPrimaryKey = true)
    int id_supplier_delivery_details;

    @DBField(name = "id_supplier_delivery_order", isForeignKey = true)
    SupplierDeliveryOrder supplierDeliveryOrder;

    @DBField(name = "id_article", isForeignKey = true)
    Article article;

    @DBField(name = "quantity")
    double quantity;

    public DeliveryArticleDetails(int id_supplier_delivery_details, SupplierDeliveryOrder supplierDeliveryOrder, Article article, double quantity) {
        this.id_supplier_delivery_details = id_supplier_delivery_details;
        this.supplierDeliveryOrder = supplierDeliveryOrder;
        this.article = article;
        this.quantity = quantity;
    }

    public DeliveryArticleDetails() {
    }

    public int getId_supplier_delivery_details() {
        return id_supplier_delivery_details;
    }

    public void setId_supplier_delivery_details(int id_supplier_delivery_details) {
        this.id_supplier_delivery_details = id_supplier_delivery_details;
    }

    public SupplierDeliveryOrder getSupplierDeliveryOrder() {
        return supplierDeliveryOrder;
    }

    public void setSupplierDeliveryOrder(SupplierDeliveryOrder supplierDeliveryOrder) {
        this.supplierDeliveryOrder = supplierDeliveryOrder;
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

}
