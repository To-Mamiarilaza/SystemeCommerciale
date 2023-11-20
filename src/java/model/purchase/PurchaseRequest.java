/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.purchase;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.article.Article;
import model.base.Service;
import model.base.Utilisateur;

/**
 *
 * @author To Mamiarilaza
 */
@DBTable(name = "purchase_request", sequenceName = "seq_purchase_request")
public class PurchaseRequest {
    // Field
    @DBField(name = "id_purchase_request", isPrimaryKey = true)
    int idPurchaseRequest;
    
    @DBField(name = "sending_date")
    LocalDate sendingDate;
    
    @DBField(name = "id_utilisateur", isForeignKey = true)
    Utilisateur utilisateur;
    
    @DBField(name = "id_service", isForeignKey = true)
    Service service;
    
    @DBField(name = "title")
    String title;
    
    @DBField(name = "description")
    String description;
    
    @DBField(name = "status")
    int status;
    
    List<ArticleQuantity> articleQuantityList = new ArrayList<>();
    
    // Getter and setter

    public int getIdPurchaseRequest() {
        return idPurchaseRequest;
    }

    public void setIdPurchaseRequest(int idPurchaseRequest) {
        this.idPurchaseRequest = idPurchaseRequest;
    }

    public LocalDate getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(LocalDate sendingDate) {
        this.sendingDate = sendingDate;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws Exception {
        if(title.trim().equals("") || title == null) {
            throw new Exception("Le titre ne doit pas etre null");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws Exception {
        if(description.trim().equals("") || description == null) {
            throw new Exception("La description ne doit pas etre null");
        }
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ArticleQuantity> getArticleQuantityList() {
        return articleQuantityList;
    }

    public void setArticleQuantityList(List<ArticleQuantity> articleQuantityList) {
        this.articleQuantityList = articleQuantityList;
    }
    
    
    // Constructor

    public PurchaseRequest() {
    }

    public PurchaseRequest(LocalDate sendingDate, Utilisateur utilisateur, Service service, String title, String description, int status) {
        this.sendingDate = sendingDate;
        this.utilisateur = utilisateur;
        this.service = service;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public PurchaseRequest(int idPurchaseRequest, LocalDate sendingDate, Utilisateur utilisateur, Service service, String title, String description, int status) {
        this.idPurchaseRequest = idPurchaseRequest;
        this.sendingDate = sendingDate;
        this.utilisateur = utilisateur;
        this.service = service;
        this.title = title;
        this.description = description;
        this.status = status;
    }
    
///Fonctions
     // ajouter un article dans une demande
    public ArticleQuantity addArticleQuantity(String article, String quantity) throws Exception {
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
        ArticleQuantity articleQuantity = new ArticleQuantity();
        //Verificationde l'existence de l'article dans la liste des articles ajoutes
        if(isArticleInList(articleFinding, quantityParsed) != -1) {
            int idArticleAdded = isArticleInList(articleFinding, quantityParsed);
            this.getArticleQuantityList().get(idArticleAdded).setQuantity(this.getArticleQuantityList().get(idArticleAdded).getQuantity() + quantityParsed);
            this.getArticleQuantityList().get(idArticleAdded).setIsExist(true);
            
            return this.getArticleQuantityList().get(idArticleAdded);
        } else {
            articleQuantity = new ArticleQuantity(articleFinding, quantityParsed, 0, 0.0, 1);
            this.getArticleQuantityList().add(articleQuantity);
            articleQuantity.setIsExist(false);
        }
       

        return articleQuantity;
    }

    //Verifier si l'article est deja ajoute dans la liste
    public int isArticleInList(Article article, Double newQuantity) throws Exception {
        for(int i = 0; i < this.getArticleQuantityList().size(); i++) {
            if(article.getIdArticle() == this.getArticleQuantityList().get(i).getArticle().getIdArticle()) {
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

        for (int i = 0; i < this.getArticleQuantityList().size(); i++) {
            if (this.getArticleQuantityList().get(i).getArticle().getCode().equals(code)) {
                if(this.getArticleQuantityList().get(i).getIdArticleQuantity() != 0) {
                    this.getArticleQuantityList().get(i).setStatus(0);
                    GenericDAO.updateById(this.getArticleQuantityList().get(i), this.getArticleQuantityList().get(i).getIdArticleQuantity(), null);
                 }
                this.getArticleQuantityList().remove(i);
            }
        }
    }

    // Pour avoir les informations a propos d'une demande
    public void getInformation() {
        for (ArticleQuantity articleQuantity : this.getArticleQuantityList()) {
            System.out.println("Code = " +articleQuantity.getArticle().getCode()+ ", article = "+articleQuantity.getArticle().getDesignation()+ ", quantity = " + articleQuantity.getQuantity()+", status = "+articleQuantity.getStatus());
        }
    }
    
    //Sauvgarder les demandes
    public void save(PurchaseRequest purchaseRequest) throws Exception {
        System.out.println("Objet ; "+purchaseRequest);
        GenericDAO.save(purchaseRequest, null);
        for(ArticleQuantity articleQuantity : this.getArticleQuantityList()) {
            articleQuantity.setPurchaseRequest(this);
            GenericDAO.save(articleQuantity, null);
        }
    } 
    
    //Avoir le string d'un status
    public String getStatusString() {
        if(this.getStatus() == 1) {
            return "Traiter";
        }
        else if(this.getStatus() == 2) {
            return "Valider";
        }
        
        return "Annuler";
    }
    
    //Avoir le badge d'un status
    public String getBadgeStatus() {
        if(this.getStatus() == 1) {
            return "badge-gradient-warning";
        }
        else if(this.getStatus() == 2) {
            return "badge-gradient-success";
        }
        
        return "badge-gradient-danger";
    }
    
    //Modifier une demande
    public void editRequest() throws Exception {
        GenericDAO.updateById(this, this.getIdPurchaseRequest(), null);
        for(int i = 0; i < this.getArticleQuantityList().size(); i++) {
            if(this.getArticleQuantityList().get(i).getIdArticleQuantity() != 0) {
                GenericDAO.updateById(this.getArticleQuantityList().get(i), this.getArticleQuantityList().get(i).getIdArticleQuantity(), null);
            }
            else {
                GenericDAO.save(this.getArticleQuantityList().get(i), null);
            }
        }
    }
}
