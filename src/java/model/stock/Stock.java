package model.stock;

import generalisation.annotations.DBField;
import model.article.Article;
import service.util.DisplayUtil;


public class Stock {
    // This class is representing the function get_stock_availability(start_date, end_date) on the database

    // Field
    @DBField(name = "id_article", isForeignKey = true)
    Article article;

    @DBField(name = "initial_quantity")
    double initialQuantity;
    
    @DBField(name = "incoming")
    double incoming;
    
    @DBField(name = "outgoing")
    double outgoing;

    @DBField(name = "reste")
    double remain;

    @DBField(name = "unit_price")
    double unitPrice;

    @DBField(name = "amount")
    double amount;

    // Getter and setter

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public double getInitialQuantity() {
        return initialQuantity;
    }

    public String getInitialQuantityDisplay() {
        return DisplayUtil.formatMoney(getInitialQuantity(), "");
    }

    public double getIncoming() {
        return incoming;
    }

    public void setIncoming(double incoming) {
        this.incoming = incoming;
    }

    public double getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(double outgoing) {
        this.outgoing = outgoing;
    }
    
    public void setInitialQuantity(double initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public double getRemain() {
        return remain;
    }

    public String getRemainDisplay() {
        return DisplayUtil.formatMoney(getRemain(), "");
    }

    public void setRemain(double remain) {
        this.remain = remain;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getUnitPriceDisplay() { return  DisplayUtil.formatMoney(getUnitPrice(), "AR");}

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }


    public double getAmount() {
        return amount;
    }

    public String getAmountDisplay() {
        return DisplayUtil.formatMoney(getAmount(), "AR");
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Constructor

    public Stock() {
    }

    public Stock(Article article, double initialQuantity, double incoming, double outgoing, double remain, double unitPrice, double amount) {
        this.article = article;
        this.initialQuantity = initialQuantity;
        this.incoming = incoming;
        this.outgoing = outgoing;
        this.remain = remain;
        this.unitPrice = unitPrice;
        this.amount = amount;
    }

}
