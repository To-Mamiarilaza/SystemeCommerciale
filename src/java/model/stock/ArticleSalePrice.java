/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.stock;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.utils.GenericUtil;
import model.article.Article;

/**
 *
 * @author to
 */
@DBTable(name = "v_article_sale_price", sequenceName = "")
public class ArticleSalePrice {
    // Field
    @DBField(name = "id_article", isForeignKey = true)
    Article article;
    
    @DBField(name = "sale_price")
    double salePrice;
    
    // Getter and setter

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
    
    // Constructor

    public ArticleSalePrice() {
    }

    public ArticleSalePrice(Article article, double salePrice) {
        this.article = article;
        this.salePrice = salePrice;
    }
}
