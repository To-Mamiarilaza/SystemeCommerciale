/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.purchase;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.src.generalisation.utils.GenericUtil;
import java.time.LocalDate;
import model.article.Article;
import model.supplier.Supplier;
import service.util.DisplayUtil;

/**
 *
 * @author To Mamiarilaza
 */
@DBTable(name = "supplier_article_price", sequenceName = "seq_supplier_article_price")
public class SupplierArticlePrice {
    // Field
    @DBField(name = "id_supplier_article_price", isPrimaryKey = true)
    int idSupplierArticlePrice;
    
    @DBField(name = "date")
    LocalDate date;
    
    @DBField(name = "id_article", isForeignKey = true)
    Article article;
    
    @DBField(name = "id_supplier", isForeignKey = true)
    Supplier supplier;
    
    @DBField(name = "unit_price")
    double unitPrice;
    
    @DBField(name = "chosen")
    boolean chosen;
    
    @DBField(name = "status")
    int status;
    
    // Getter and setter

    public int getIdSupplierArticlePrice() {
        return idSupplierArticlePrice;
    }

    public void setIdSupplierArticlePrice(int idSupplierArticlePrice) {
        this.idSupplierArticlePrice = idSupplierArticlePrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
    
    public String getUnitPriceString() {
        return DisplayUtil.formatMoney(getUnitPrice(), "AR");
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean getChosen() {
        return chosen;
    }
    
    public String getChosenCheckedStatus() {
        if (getChosen()) {
            return "checked";
        } else {
            return "";
        }
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    // Constructor

    public SupplierArticlePrice() {
    }

    public SupplierArticlePrice(LocalDate date, Article article, Supplier supplier, double unitPrice, boolean chosen, int status) {
        this.date = date;
        this.article = article;
        this.supplier = supplier;
        this.unitPrice = unitPrice;
        this.chosen = chosen;
        this.status = status;
    }

    public SupplierArticlePrice(int idSupplierArticlePrice, LocalDate date, Article article, Supplier supplier, double unitPrice, boolean chosen, int status) {
        this.idSupplierArticlePrice = idSupplierArticlePrice;
        this.date = date;
        this.article = article;
        this.supplier = supplier;
        this.unitPrice = unitPrice;
        this.chosen = chosen;
        this.status = status;
    }
}
