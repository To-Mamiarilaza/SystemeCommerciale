/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.purchaseClient;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import model.article.Article;

/**
 *
 * @author chalman
 */
@DBTable(name = "article_quantity_order", sequenceName = "seq_article_quantity_order")
public class ArticleOrder {
    @DBField(name = "id_article_quantity_order", isPrimaryKey = true)
    int idArticleOrder;
    @DBField(name = "id_article", isForeignKey = true)
    Article article;
    @DBField(name = "quantity")
    Double quantity;
    @DBField(name = "id_purchase_order", isForeignKey = true)
    PurchaseOrderClient purchaseOrderClient;
    @DBField(name = "status")
    int status;
    boolean isExist;
    
///Getters et setters
    public int getIdArticleOrder() {
        return idArticleOrder;
    }
    public void setIdArticleOrder(int idArticleOrder) {    
        this.idArticleOrder = idArticleOrder;
    }

    public Article getArticle() {
        return article;
    }
    public void setArticle(Article article) {
        this.article = article;
    }

    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public PurchaseOrderClient getPurchaseOrderClient() {
        return purchaseOrderClient;
    }
    public void setPurchaseOrderClient(PurchaseOrderClient purchaseOrderClient) {
        this.purchaseOrderClient = purchaseOrderClient;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public boolean getIsExist() {
        return isExist;
    }
    public void setIsExist(boolean isExist) {
        this.isExist = isExist;
    }
    
///Constructors
    public ArticleOrder() {
    }
    
    public ArticleOrder(int idArticleOrder, Article article, Double quantity, PurchaseOrderClient purchaseOrderClient, int status) {
        this.idArticleOrder = idArticleOrder;
        this.article = article;
        this.quantity = quantity;
        this.status = status;
        this.purchaseOrderClient = purchaseOrderClient;
    }

    public ArticleOrder(Article article, Double quantity, PurchaseOrderClient purchaseOrderClient, int status) {
        this.article = article;
        this.quantity = quantity;
        this.status = status;
        this.purchaseOrderClient = purchaseOrderClient;
    }
        
    public ArticleOrder(Article article, Double quantity, int status) {
        this.article = article;
        this.quantity = quantity;
        this.status = status;
    }
///Fonctions

}
