/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet.purchase;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.utils.GenericUtil;
import model.article.Article;

/**
 *
 * @author To Mamiarilaza
 */
@DBTable(name = "v_article_request", sequenceName = "")
public class ArticleRequest {
    // Field
    @DBField(name = "id_article", isForeignKey = true)
    Article article;
    
    @DBField(name = "sum")
    double quantity;
    
    // Getter and setter

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
    
    // Constructor

    public ArticleRequest() {
    }
    
    public ArticleRequest(Article article, double quantity) {
        this.article = article;
        this.quantity = quantity;
    }
}
