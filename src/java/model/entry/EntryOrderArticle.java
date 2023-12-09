/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entry;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import model.article.Article;

/**
 *
 * @author Fy Botas
 */
@DBTable(name = "entry_order_articles", sequenceName = "seq_entry_order_article")
public class EntryOrderArticle {

    @DBField(name = "id_entry_order_article", isPrimaryKey = true)
    int idEntryOrderArticle;

    @DBField(name = "id_entry_order", isForeignKey = true)
    EntryOrder entryOrder;

    @DBField(name = "id_article", isForeignKey = true)
    Article article;

    @DBField(name = "quantity")
    double quantity;

    public int getIdEntryOrderArticle() {
        return idEntryOrderArticle;
    }

    public void setIdEntryOrderArticle(int idEntryOrderArticle) {
        this.idEntryOrderArticle = idEntryOrderArticle;
    }

    public EntryOrder getEntryOrder() {
        return entryOrder;
    }

    public void setEntryOrder(EntryOrder entryOrder) {
        this.entryOrder = entryOrder;
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

    public EntryOrderArticle(int idEntryOrderArticle, EntryOrder entryOrder, Article article, double quantity) {
        this.idEntryOrderArticle = idEntryOrderArticle;
        this.entryOrder = entryOrder;
        this.article = article;
        this.quantity = quantity;
    }

    public EntryOrderArticle() {
    }

}
