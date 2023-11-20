/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.purchase;

import generalisation.annotations.DBField;
import model.article.Article;
import service.util.DisplayUtil;

/**
 *
 * @author To Mamiarilaza
 */

public class InvoiceLineItem {
    // ASSOCIE AVEC --> get_supplier_proforma(id_supplier)
    
    // Field
    @DBField(name = "id_article", isForeignKey = true)
    Article article;
    
    @DBField(name = "quantity")
    double quantity;
    
    @DBField(name = "unit_price")
    double unitPrice;
    
    @DBField(name = "tva")
    double tva;
    
    @DBField(name = "tva_amount")
    double TVAAmount;
    
    @DBField(name = "ht_amount")
    double HTAmount;
    
    @DBField(name = "ttc_amount")
    double TTCAmount;
    
    // Getter and Setter

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

    public double getUnitPrice() {
        return unitPrice;
    }
    
    public String getUnitPriceString() {
        return DisplayUtil.formatMoney(getUnitPrice(), "AR");
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

    public double getTVAAmount() {
        return TVAAmount;
    }
    
    public String getTVAAmountString() {
        return DisplayUtil.formatMoney(getTVAAmount(), "AR");
    }

    public void setTVAAmount(double TVAAmount) {
        this.TVAAmount = TVAAmount;
    }

    public double getHTAmount() {
        return HTAmount;
    }
    
    public String getHTAmountString() {
        return DisplayUtil.formatMoney(getHTAmount(), "AR");
    }

    public void setHTAmount(double HTAmount) {
        this.HTAmount = HTAmount;
    }

    public double getTTCAmount() {
        return TTCAmount;
    }
    
    public String getTTCAmountString() {
        return DisplayUtil.formatMoney(getTTCAmount(), "AR");
    }
    

    public void setTTCAmount(double TTCAmount) {
        this.TTCAmount = TTCAmount;
    }
    
    // Constructor
    public InvoiceLineItem() {
    }

    public InvoiceLineItem(Article article, double quantity, double unitPrice, double tva, double TVAAmount, double HTAmount, double TTCAmount) {
        this.article = article;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.tva = tva;
        this.TVAAmount = TVAAmount;
        this.HTAmount = HTAmount;
        this.TTCAmount = TTCAmount;
    }
    
}
