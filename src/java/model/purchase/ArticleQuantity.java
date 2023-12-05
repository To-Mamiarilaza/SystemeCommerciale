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
@DBTable(name = "article_quantity", sequenceName = "seq_article_quantity")
public class ArticleQuantity {
    // Field
    @DBField(name = "id_article_quantity", isPrimaryKey = true)
    int idArticleQuantity;
    
    @DBField(name = "id_purchase_request", isForeignKey = true)
    PurchaseRequest purchaseRequest;
    
    @DBField(name = "id_article", isForeignKey = true)
    Article article;
    
    @DBField(name = "quantity")
    double quantity;
    
    @DBField(name = "id_purchase_order")
    Integer idPurchaseOrder;
    
    @DBField(name = "amount")
    double amount;
    
    @DBField(name = "status")
    int status;
    
    boolean isExist;
    
    // Getter and Setter

    public int getIdArticleQuantity() {
        return idArticleQuantity;
    }

    public void setIdArticleQuantity(int idArticleQuantity) {
        this.idArticleQuantity = idArticleQuantity;
    }

    public PurchaseRequest getPurchaseRequest() {
        return purchaseRequest;
    }

    public void setPurchaseRequest(PurchaseRequest purchaseRequest) {
        this.purchaseRequest = purchaseRequest;
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

    public Integer getIdPurchaseOrder() {
        return idPurchaseOrder;
    }

    public void setIdPurchaseOrder(Integer idPurchaseOrder) {
        this.idPurchaseOrder = idPurchaseOrder;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
    
    // Constructor

    public ArticleQuantity() {
    }

    public ArticleQuantity(PurchaseRequest purchaseRequest, Article article, double quantity, Integer idPurchaseOrder, double amount, int status) {
        this.purchaseRequest = purchaseRequest;
        this.article = article;
        this.quantity = quantity;
        this.idPurchaseOrder = idPurchaseOrder;
        this.amount = amount;
        this.status = status;
    }

    public ArticleQuantity(int idArticleQuantity, PurchaseRequest purchaseRequest, Article article, double quantity, Integer idPurchaseOrder, double amount, int status) {
        this.idArticleQuantity = idArticleQuantity;
        this.purchaseRequest = purchaseRequest;
        this.article = article;
        this.quantity = quantity;
        this.idPurchaseOrder = idPurchaseOrder;
        this.amount = amount;
        this.status = status;
    }
    
    public ArticleQuantity(Article article, double quantity, Integer idPurchaseOrder, double amount, int status) {
        this.article = article;
        this.quantity = quantity;
        this.idPurchaseOrder = idPurchaseOrder;
        this.amount = amount;
        this.status = status;
    }
}
