/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.sale;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fy Botas
 */
@DBTable(name = "v_article_sale_quantity", sequenceName = "")
public class ArticleSaleQuantity {

    @DBField(name = "id_article")
    int idArticle;

    @DBField(name = "designation")
    String designation;

    @DBField(name = "quantity")
    double quantity;

    /* avoir somme quantity */
    public double sommeQuantity() throws Exception {
        List<ArticleSaleQuantity> articleSale = (List<ArticleSaleQuantity>) GenericDAO.getAll(ArticleSaleQuantity.class, "", null);
        double somme = 0;
        for (int i = 0; i < articleSale.size(); i++) {
            somme += articleSale.get(i).getQuantity();
        }
        return somme;
    }

    // avoir la proportion des articles
    public List<Double> getArticleProportions() throws Exception {
        List<Double> proportionArticle = new ArrayList<>();
        List<ArticleSaleQuantity> articleSale = (List<ArticleSaleQuantity>) GenericDAO.getAll(ArticleSaleQuantity.class, "", null);
        for (int i = 0; i < articleSale.size(); i++) {
            proportionArticle.add((articleSale.get(i).getQuantity() * 100) / sommeQuantity());
        }
        return proportionArticle;
    }
    
    public String[] getProportionString() throws Exception{
        List<Double> proportions = this.getArticleProportions();
        String[] stringProportion = new String[proportions.size()];
        for (int i = 0; i < proportions.size(); i++) {
            stringProportion[i] = String.valueOf(proportions.get(i));
        }
        return stringProportion;
    }

    public String[] getArticleName() throws Exception{
        List<ArticleSaleQuantity> articleSale = (List<ArticleSaleQuantity>) GenericDAO.getAll(ArticleSaleQuantity.class, "", null);
        String[] articleName = new String[articleSale.size()];
        for (int i = 0; i < articleSale.size(); i++) {
            articleName[i] = articleSale.get(i).getDesignation();
        }
        return articleName;
    }
    
    public String[] formatArticleName() throws Exception{
        String[] articleName = getArticleName();
        String[] articleFormatted = new String[articleName.length];
        for (int i = 0; i < articleName.length; i++) {
            articleFormatted[i] = "'"+articleName[i]+"'";
        }
        return articleFormatted;
    }
    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public ArticleSaleQuantity(int idArticle, String designation, double quantity) {
        this.idArticle = idArticle;
        this.designation = designation;
        this.quantity = quantity;
    }

    public ArticleSaleQuantity() {
    }

    public static void main(String[] args) throws Exception {
        ArticleSaleQuantity a = new ArticleSaleQuantity();
        for (int i = 0; i < a.formatArticleName().length; i++) {
            System.out.println(a.formatArticleName()[i]);
        }
        
    }
}
