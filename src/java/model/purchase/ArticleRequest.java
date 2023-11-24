/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.purchase;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.util.List;
import model.article.Article;
import model.purchase.ArticleQuantity;
import model.purchase.SupplierArticlePrice;
import model.supplier.Supplier;

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
    
    List<ArticleQuantity> articleQuantityDetails;
    List<Supplier> convenableSuppliers;
    List<SupplierArticlePrice> supplierArticlePrices;
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

    public List<ArticleQuantity> getArticleQuantityDetails() {
        return articleQuantityDetails;
    }

    public void setArticleQuantityDetails(List<ArticleQuantity> articleQuantityDetails) {
        this.articleQuantityDetails = articleQuantityDetails;
    }

    public List<Supplier> getConvenableSuppliers() {
        return convenableSuppliers;
    }

    public void setConvenableSuppliers(List<Supplier> convenableSuppliers) {
        this.convenableSuppliers = convenableSuppliers;
    }

    public List<SupplierArticlePrice> getSupplierArticlePrices() {
        return supplierArticlePrices;
    }

    public void setSupplierArticlePrices(List<SupplierArticlePrice> supplierArticlePrices) {
        this.supplierArticlePrices = supplierArticlePrices;
    }
    
    // Constructor

    public ArticleRequest() {
    }
    
    public ArticleRequest(Article article, double quantity) {
        this.article = article;
        this.quantity = quantity;
    }
}
