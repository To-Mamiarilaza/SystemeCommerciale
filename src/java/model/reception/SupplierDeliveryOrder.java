/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.reception;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.purchase.ArticleQuantity;
import model.purchase.PurchaseOrder;

/**
 *
 * @author Fy Botas
 */
@DBTable(name = "supplier_delivery_order", sequenceName = "seq_supplier_delivery_order")
public class SupplierDeliveryOrder {

    @DBField(name = "id_supplier_delivery_order", isPrimaryKey = true)
    int idSupplierDeliveryOrder;

    @DBField(name = "reference")
    String reference;

    @DBField(name = "delivery_date")
    LocalDate delivery_date;

    @DBField(name = "id_purchase_order", isForeignKey = true)
    PurchaseOrder purchaseOrder;

    @DBField(name = "delivers_name")
    String deliversName;

    @DBField(name = "delivers_contact")
    String deliversContact;

    @DBField(name = "status")
    int status;

    List<ArticleDetails> listeArticles;

    //methods
    // avoir les annomalies concernant le bon de livraison
    public List<String> check_anommalie(List<ArticleDetails> articlesDetails, List<ArticleQuantity> articlesQuantity) {
        List<String> anommalies = new ArrayList<>();
        for (int i = 0; i < articlesDetails.size(); i++) {
            for (int j = 0; j < articlesQuantity.size(); j++) {
                if (articlesDetails.get(i).getArticle().getIdArticle() == articlesQuantity.get(j).getArticle().getIdArticle()) {
                    if (articlesDetails.get(i).getQuantity() < articlesQuantity.get(j).getQuantity()) {
                        String anommalie = "nous avons constatés des annomalies de " + articlesQuantity.get(j).getArticle().getDesignation() + " attendue : " + articlesQuantity.get(j).getQuantity() + " -> reçu : " + articlesDetails.get(i).getQuantity();
                        anommalies.add(anommalie);
                    }
                } else {
                    String anommalie = "l'article que vous avez choisit n'est pas inclus dans notre bon de commande";
                    anommalies.add(anommalie);
                }
            }
        }

        return anommalies;
    }

    //constructor
    public SupplierDeliveryOrder(int idSupplierDeliveryOrder, String reference, LocalDate delivery_date, PurchaseOrder purchaseOrder, String deliversName, String deliversContact, List<ArticleDetails> listeArticle) {
        this.idSupplierDeliveryOrder = idSupplierDeliveryOrder;
        this.reference = reference;
        this.delivery_date = delivery_date;
        this.purchaseOrder = purchaseOrder;
        this.deliversName = deliversName;
        this.deliversContact = deliversContact;
        this.listeArticles = listeArticle;
    }

    public SupplierDeliveryOrder(int idSupplierDeliveryOrder, String reference, LocalDate delivery_date, PurchaseOrder purchaseOrder, String deliversName, String deliversContact, int status) {
        this.idSupplierDeliveryOrder = idSupplierDeliveryOrder;
        this.reference = reference;
        this.delivery_date = delivery_date;
        this.purchaseOrder = purchaseOrder;
        this.deliversName = deliversName;
        this.deliversContact = deliversContact;
        this.status = status;
    }

    public SupplierDeliveryOrder() {
    }

    // getters and setters
    public int getStatus() {
        return 1;
    }

    public List<ArticleDetails> getListeArticles() {
        return listeArticles;
    }

    public void setListeArticles(List<ArticleDetails> listeArticles) {
        this.listeArticles = listeArticles;
    }

    public int getIdSupplierDeliveryOrder() {
        return idSupplierDeliveryOrder;
    }

    public void setIdSupplierDeliveryOrder(int idSupplierDeliveryOrder) {
        this.idSupplierDeliveryOrder = idSupplierDeliveryOrder;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(LocalDate delivery_date) {
        this.delivery_date = delivery_date;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getDeliversName() {
        return deliversName;
    }

    public void setDeliversName(String deliversName) {
        this.deliversName = deliversName;
    }

    public String getDeliversContact() {
        return deliversContact;
    }

    public void setDeliversContact(String deliversContact) {
        this.deliversContact = deliversContact;
    }

}
