/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.reception;

import model.article.Article;

/**
 *
 * @author Fy Botas
 */
public class ArticleDetails {

    Article article;
    int quantity;

    //constructors
    public ArticleDetails(Article article, int quantity) {
        this.article = article;
        this.quantity = quantity;
    }

    public ArticleDetails() {
    }
    
    // getters and setters

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
