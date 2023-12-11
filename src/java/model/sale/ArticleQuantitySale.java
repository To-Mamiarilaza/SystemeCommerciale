/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.sale;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import model.article.Article;

/**
 *
 * @author chalman
 */
@DBTable(name = "article_quantity_sale", sequenceName = "seq_article_quantity_sale")
public class ArticleQuantitySale {
    @DBField(name = "id_article_quantity_sale", isPrimaryKey = true)
    int idArticleQuantitySale;
    @DBField(name = "id_article", isForeignKey = true)
    Article article;
    @DBField(name = "quantity")
    double quantity;
    double availableQuantity;
    double unitPrice;
    double tva;
    double tvaAmount;
    double htAmount;
    double ttcAmount;
    @DBField(name = "id_proforma_sending", isForeignKey = true)
    ProformaSending proformaSending; 
    @DBField(name = "status")
    int status;
    boolean isExist;
    
///Getters et setters

    public int getIdArticleQuantitySale() {
        return idArticleQuantitySale;
    }

    public void setIdArticleQuantitySale(int IdArticleQuantitySale) {
        this.idArticleQuantitySale = IdArticleQuantitySale;
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

    public ProformaSending getProformaSending() {
        return proformaSending;
    }

    public void setProformaSending(ProformaSending proformaSending) {
        this.proformaSending = proformaSending;
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

    public double getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(double availableQuantity) {
        this.availableQuantity = availableQuantity;
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
    
    
    
    
    
    
///Constructors

    public ArticleQuantitySale() {
    }

    public ArticleQuantitySale(int idArticleQuantity, Article article, double quantity, int status) {
        this.idArticleQuantitySale = idArticleQuantity;
        this.article = article;
        this.quantity = quantity;
        this.status = status;
    }

    public ArticleQuantitySale(Article article, double quantity, int status) {
        this.article = article;
        this.quantity = quantity;
        this.status = status;
    }
    
///fonctions
    
}
