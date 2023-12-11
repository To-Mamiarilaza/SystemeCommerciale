/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.sale;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.article.Article;
import model.purchase.ArticleQuantity;
import model.purchase.PurchaseRequest;
/**
 *
 * @author chalman
 */
@DBTable(name = "proforma_Sending", sequenceName = "seq_proforma_Sending")
public class ProformaSending {
    @DBField(name = "id_proforma_sending", isPrimaryKey = true)
    int idProformaSending;
    @DBField(name = "email")
    String email;
    @DBField(name = "date_sending")
    LocalDate dateSending;
    @DBField(name = "status")
    int status; 
    List<ArticleQuantitySale> articles = new ArrayList<>();
    
///Getters et setters

    public int getIdProformaSending() {
        return idProformaSending;
    }

    public void setIdProformaSending(int idProformaSending) {
        this.idProformaSending = idProformaSending;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateSending() {
        return dateSending;
    }

    public void setDateSending(LocalDate dateSending) {
        this.dateSending = dateSending;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ArticleQuantitySale> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleQuantitySale> articles) {
        this.articles = articles;
    }
    
///Constructors

    public ProformaSending() {
    }

    public ProformaSending(int idProformaSending, String email, LocalDate dateSending, int status) {
        this.idProformaSending = idProformaSending;
        this.email = email;
        this.dateSending = dateSending;
        this.status = status;
    }

    public ProformaSending(String email, LocalDate dateSending, int status) {
        this.email = email;
        this.dateSending = dateSending;
        this.status = status;
    }

    
    
///Fonctions
    // get the total of tva
    public double getTvaTotal() {
        double sum = 0;
        for (ArticleQuantitySale article : articles) {
            sum += article.getTvaAmount();
        }
        return sum;
    }
    
    public double getHtTotal() {
        double sum = 0;
        for (ArticleQuantitySale article : articles) {
            sum += article.getHtAmount();
        }
        return sum;
    }
    
    public double getTtcTotal() {
        double sum = 0;
        for (ArticleQuantitySale article : articles) {
            sum += article.getTtcAmount();
        }
        return sum;
    }
    
     // ajouter un article dans une demande
    public ArticleQuantitySale addArticleQuantity(String article, String quantity) throws Exception {
        if (article.trim().equals("")) {
            throw new Exception("L'article ne doit pas être vide !");
        }
        if (quantity.trim().equals("")) {
            throw new Exception("La quantite ne doit pas être vide !");
        }

        Double quantityParsed;
        try {
            quantityParsed = Double.valueOf(quantity);
        } catch (Exception e) {
            throw new Exception("La valeur du quantite doit être un nombre !");
        }

        if(quantityParsed < 0) {
            throw new Exception("La valeur du quantite doit etre un nombre positif");
        }
        Article articleFinding = GenericDAO.findById(Article.class, Integer.valueOf(article), null);
        ArticleQuantitySale articleQuantity = new ArticleQuantitySale();
        //Verification de l'existence de l'article dans la liste des articles ajoutes
        if(isArticleInList(articleFinding, quantityParsed) != -1) {
            int idArticleAdded = isArticleInList(articleFinding, quantityParsed);
            this.getArticles().get(idArticleAdded).setQuantity(this.getArticles().get(idArticleAdded).getQuantity() + quantityParsed);
            this.getArticles().get(idArticleAdded).setIsExist(true);
            
            return this.getArticles().get(idArticleAdded);
        } else {
            articleQuantity = new ArticleQuantitySale(articleFinding, quantityParsed, 1);
            this.getArticles().add(articleQuantity);
            articleQuantity.setIsExist(false);
        }
       

        return articleQuantity;
    }

    //Verifier si l'article est deja ajoute dans la liste
    public int isArticleInList(Article article, Double newQuantity) throws Exception {
        for(int i = 0; i < this.getArticles().size(); i++) {
            if(article.getIdArticle() == this.getArticles().get(i).getArticle().getIdArticle()) {
                return i;
            }
        }
        
        return -1;
    }
    
    // supprimer une demande d'article
    public void deleteRequest(String code) throws Exception {
        if (code.trim().equals("")) {
            throw new Exception("Le code article ne doit pas être vide ou null !");
        }

        for (int i = 0; i < this.getArticles().size(); i++) {
            if (this.getArticles().get(i).getArticle().getCode().equals(code)) {
                if(this.getArticles().get(i).getIdArticleQuantitySale() != 0) {
                    this.getArticles().get(i).setStatus(0);
                    GenericDAO.updateById(this.getArticles().get(i), this.getArticles().get(i).getIdArticleQuantitySale(), null);
                 }
                this.getArticles().remove(i);
            }
        }
    }

    //Affichage du proforma demande
    public void displayProforma() {
        for(int i = 0; i < this.getArticles().size(); i++) {
            System.out.println("article = "+this.getArticles().get(i).getArticle().getDesignation()+", Quantity = "+this.getArticles().get(i).getQuantity());
        }
    }
    
     //Sauvgarder le proforma demande
    public void save() throws Exception {
        GenericDAO.save(this, null);
        for(ArticleQuantitySale articleQuantitySale : this.getArticles()) {
            articleQuantitySale.setProformaSending(this);
            GenericDAO.save(articleQuantitySale, null);
        }
    } 
}
