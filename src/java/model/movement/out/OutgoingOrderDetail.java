/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.movement.out;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import model.article.Article;

/**
 *
 * @author to
 */
@DBTable(name = "outgoing_order_detail", sequenceName = "seq_outgoing_order_detail")
public class OutgoingOrderDetail {
    /// Field
    @DBField(name = "id_outgoing_order_detail", isPrimaryKey = true)
    int idOutgoingOrderDetail;
    
    @DBField(name = "id_outgoing_order")
    int idOutgoingOrder;
    
    @DBField(name = "id_article", isForeignKey = true)
    Article article;
    
    @DBField(name = "quantity")
    double quantity;
    
    @DBField(name = "status")
    int status;
    
    /// Getter and setter

    public int getIdOutgoingOrderDetail() {
        return idOutgoingOrderDetail;
    }

    public void setIdOutgoingOrderDetail(int idOutgoingOrderDetail) {
        this.idOutgoingOrderDetail = idOutgoingOrderDetail;
    }

    public int getIdOutgoingOrder() {
        return idOutgoingOrder;
    }

    public void setIdOutgoingOrder(int idOutgoingOrder) {
        this.idOutgoingOrder = idOutgoingOrder;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
    
    /// Constructor

    public OutgoingOrderDetail(int idOutgoingOrderDetail, int idOutgoingOrder, Article article, double quantity, int status) {
        this.idOutgoingOrderDetail = idOutgoingOrderDetail;
        this.idOutgoingOrder = idOutgoingOrder;
        this.article = article;
        this.quantity = quantity;
        this.status = status;
    }

    public OutgoingOrderDetail() {
    }

    public OutgoingOrderDetail(int idOutgoingOrder, Article article, double quantity) {
        this.idOutgoingOrder = idOutgoingOrder;
        this.article = article;
        this.quantity = quantity;
        this.status = 1;
    }
    
}
