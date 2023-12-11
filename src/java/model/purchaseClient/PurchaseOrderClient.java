/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.purchaseClient;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.article.Article;
import model.purchase.PaymentMethod;
import model.sale.ArticleQuantitySale;
import service.util.DisplayUtil;

/**
 *
 * @author chalman
 */
@DBTable(name = "purchase_order_client", sequenceName = "seq_purchase_order_client")
public class PurchaseOrderClient {

    @DBField(name = "id_purchase_order_client", isPrimaryKey = true)
    int idPurchaseOrderClient;
    @DBField(name = "reference")
    String reference;
    @DBField(name = "date_insertion")
    LocalDate dateInsertion;
    @DBField(name = "client_name")
    String clientName;
    @DBField(name = "adresse")
    String adresse;
    @DBField(name = "contact_delivery")
    String contactDelivery;
    @DBField(name = "delivery_date")
    LocalDate deliveryDate;
    @DBField(name = "id_payment_method", isForeignKey = true)
    PaymentMethod paymentMethod;
    @DBField(name = "status")
    int status;
    List<ArticleOrder> articleOrder = new ArrayList<>();

    ///Getters et setters
    public int getIdPurchaseOrderClient() {
        return idPurchaseOrderClient;
    }
    
    public String getIdString() {
        return DisplayUtil.prefix("", 4, getIdPurchaseOrderClient());
    }

    public void setIdPurchaseOrderClient(int idPurchaseOrderClient) {
        this.idPurchaseOrderClient = idPurchaseOrderClient;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) throws Exception {
        if (reference.trim().equals("") && reference == null) {
            throw new Exception("Erreur : Reference est null");
        }
        this.reference = reference;
    }

    public LocalDate getDateInsertion() {
        return dateInsertion;
    }

    public void setDateInsertion(LocalDate dateInsertion) {
        this.dateInsertion = dateInsertion;
    }

    public void setDateInsertion(String dateInsertion) throws Exception {
        if (dateInsertion.trim().equals("") && dateInsertion == null) {
            throw new Exception("Erreur : date insertion est null");
        }
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateInsertion);
        } catch (Exception e) {
            throw new Exception("Erreur : Impossible de formater la dat inserer Cause : " + e.getMessage());
        }
        this.setDateInsertion(date);
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) throws Exception {
        if (clientName.trim().equals("") && clientName == null) {
            throw new Exception("Erreur : client est null");
        }
        this.clientName = clientName;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) throws Exception {
        if (adresse.trim().equals("") && adresse == null) {
            throw new Exception("Erreur : adresse est null");
        }
        this.adresse = adresse;
    }

    public String getContactDelivery() {
        return contactDelivery;
    }

    public void setContactDelivery(String contact) throws Exception {
        if (contact.trim().equals("") && contact == null) {
            throw new Exception("Erreur : contact est null");
        }
        this.contactDelivery = contact;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) throws Exception {;
        if (deliveryDate.trim().equals("") && deliveryDate == null) {
            throw new Exception("Erreur : date livraison est null");
        }
        LocalDate date = null;
        try {
            date = LocalDate.parse(deliveryDate);
        } catch (Exception e) {
            throw new Exception("Erreur : Impossible de formater la date livraison Cause : " + e.getMessage());
        }
        this.setDeliveryDate(date);
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) throws Exception {
        if (paymentMethod.trim().equals("") && paymentMethod == null) {
            throw new Exception("Erreur : methode de payment est null");
        }
        Integer idPaymentMethod = null;
        try {
            idPaymentMethod = Integer.valueOf(paymentMethod);
        } catch (Exception e) {
            throw new Exception("Erreur : impossible de formatter la valeur paymentMethod Cause : " + e.getMessage());
        }
        PaymentMethod pm = GenericDAO.findById(PaymentMethod.class, idPaymentMethod, null);
        this.setPaymentMethod(pm);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTtcTotalLetter() {
        return DisplayUtil.toLetter((int) getTtcTotal());
    }
    
    public List<ArticleOrder> getArticleOrder() {
        return articleOrder;
    }

    public void setArticleOrder(List<ArticleOrder> articleOrder) {
        this.articleOrder = articleOrder;
    }

///Constructors
    public PurchaseOrderClient() {
    }

    public PurchaseOrderClient(int idPurchaseOrderClient, String reference, LocalDate dateInsertion, String clientName, String adresse, String contact, LocalDate liverDate, PaymentMethod paymentMethod, int status) {
        this.idPurchaseOrderClient = idPurchaseOrderClient;
        this.reference = reference;
        this.dateInsertion = dateInsertion;
        this.clientName = clientName;
        this.adresse = adresse;
        this.contactDelivery = contact;
        this.deliveryDate = liverDate;
        this.paymentMethod = paymentMethod;
        this.articleOrder = articleOrder;
        this.status = status;
    }

    public PurchaseOrderClient(String reference, LocalDate dateInsertion, String clientName, String adresse, String contact, LocalDate liverDate, PaymentMethod paymentMethod, int status) {
        this.reference = reference;
        this.dateInsertion = dateInsertion;
        this.clientName = clientName;
        this.adresse = adresse;
        this.contactDelivery = contact;
        this.deliveryDate = liverDate;
        this.paymentMethod = paymentMethod;
        this.articleOrder = articleOrder;
        this.status = status;
    }

    ///Fonctions
    // get the total of tva
    public double getTvaTotal() {
        double sum = 0;
        for (ArticleOrder article : articleOrder) {
            sum += article.getTvaAmount();
        }
        return sum;
    }

    public double getHtTotal() {
        double sum = 0;
        for (ArticleOrder article : articleOrder) {
            sum += article.getHtAmount();
        }
        return sum;
    }

    public double getTtcTotal() {
        double sum = 0;
        for (ArticleOrder article : articleOrder) {
            sum += article.getTtcAmount();
        }
        return sum;
    }

    // ajouter un article dans une demande
    public ArticleOrder addArticleQuantity(String article, String quantity) throws Exception {
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

        if (quantityParsed < 0) {
            throw new Exception("La valeur du quantite doit etre un nombre positif");
        }
        Article articleFinding = GenericDAO.findById(Article.class, Integer.valueOf(article), null);
        ArticleOrder articleOrder = new ArticleOrder();
        //Verification de l'existence de l'article dans la liste des articles ajoutes
        if (isArticleInList(articleFinding, quantityParsed) != -1) {
            int idArticleAdded = isArticleInList(articleFinding, quantityParsed);
            this.getArticleOrder().get(idArticleAdded).setQuantity(this.getArticleOrder().get(idArticleAdded).getQuantity() + quantityParsed);
            this.getArticleOrder().get(idArticleAdded).setIsExist(true);

            return this.getArticleOrder().get(idArticleAdded);
        } else {
            articleOrder = new ArticleOrder(articleFinding, quantityParsed, 1);
            this.getArticleOrder().add(articleOrder);
            articleOrder.setIsExist(false);
        }

        return articleOrder;
    }

    //Verifier si l'article est deja ajoute dans la liste
    public int isArticleInList(Article article, Double newQuantity) throws Exception {
        for (int i = 0; i < this.getArticleOrder().size(); i++) {
            if (article.getIdArticle() == this.getArticleOrder().get(i).getArticle().getIdArticle()) {
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

        for (int i = 0; i < this.getArticleOrder().size(); i++) {
            if (this.getArticleOrder().get(i).getArticle().getCode().equals(code)) {
                if (this.getArticleOrder().get(i).getIdArticleOrder() != 0) {
                    this.getArticleOrder().get(i).setStatus(0);
                    GenericDAO.updateById(this.getArticleOrder().get(i), this.getArticleOrder().get(i).getIdArticleOrder(), null);
                }
                this.getArticleOrder().remove(i);
            }
        }
    }
    
    // Charger les details
    public void loadArticleQuantityOrder(Connection connection) throws Exception {
        List<ArticleOrder> articleOrders = (List<ArticleOrder>) GenericDAO.getAll(ArticleOrder.class, "WHERE id_purchase_order = " + this.getIdPurchaseOrderClient(), connection);
        setArticleOrder(articleOrders);
    }
    
    //Affichage du bon de commande
    public void displayProforma() {
        for (int i = 0; i < this.getArticleOrder().size(); i++) {
            System.out.println("article = " + this.getArticleOrder().get(i).getArticle().getDesignation() + ", Quantity = " + this.getArticleOrder().get(i).getQuantity());
        }
    }

    //Sauvgarder le bon de commande
    public void save(PurchaseOrderClient purchaseOrderClient, Connection connection) throws Exception {
        GenericDAO.save(purchaseOrderClient, null);
        for (ArticleOrder articleOrder : purchaseOrderClient.getArticleOrder()) {
            articleOrder.setPurchaseOrderClient(purchaseOrderClient);
            articleOrder.setStatus(1);
            GenericDAO.save(articleOrder, connection);
        }
    }

    //Badge status
    public String getBadgeStatus() {
        if (this.getStatus() < 5) {
            return "danger";
        } else if (this.getStatus() >= 25) {
            return "success";
        }

        return "warning";
    }

    //Status en lettre
    public String getStatusLetter() {
        if (this.getStatus() == 5) {
            return "En attente";
        } else if (this.getStatus() == 10) {
            return "Au magasin";
        } else if (this.getStatus() == 15) {
            return "Facturation";
        } else if (this.getStatus() == 20) {
            return "Livraison";
        } else if (this.getStatus() == 25) {
            return "Livrer";
        }

        return "Effacer";
    }
}
