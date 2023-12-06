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
@DBTable(name = "reception_article_details", sequenceName = "seq_reception_article_details")
public class ReceptionArticleDetails {

    @DBField(name = "id_reception_article_details", isPrimaryKey = true)
    int idReceptionArticleDetails;
    
    @DBField(name = "id_reception_order", isForeignKey = true)
    ReceptionOrder receptionOrder;
    
    @DBField(name = "id_article", isForeignKey = true)
    Article article;
    
    @DBField(name = "quantity")
    double quantity;

    public ReceptionArticleDetails(int idReceptionArticleDetails, ReceptionOrder receptionOrder, Article article, double quantity) {
        this.idReceptionArticleDetails = idReceptionArticleDetails;
        this.receptionOrder = receptionOrder;
        this.article = article;
        this.quantity = quantity;
    }

    public ReceptionArticleDetails() {
    }

    public int getIdReceptionArticleDetails() {
        return idReceptionArticleDetails;
    }

    public void setIdReceptionArticleDetails(int idReceptionArticleDetails) {
        this.idReceptionArticleDetails = idReceptionArticleDetails;
    }

    public ReceptionOrder getReceptionOrder() {
        return receptionOrder;
    }

    public void setReceptionOrder(ReceptionOrder receptionOrder) {
        this.receptionOrder = receptionOrder;
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
