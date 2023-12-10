/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.reception;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import model.purchase.ArticleQuantity;
import model.purchase.PurchaseOrder;
import service.util.DisplayUtil;

/**
 *
 * @author Fy Botas
 */
@DBTable(name = "supplier_delivery_order", sequenceName = "seq_supplier_delivery_order")
public class SupplierDeliveryOrder {

    @DBField(name = "id_supplier_delivery_order", isPrimaryKey = true)
    int idSupplierDeliveryOrder;

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
        List<String> anomalies = new ArrayList<>();

        Map<Integer, Double> expectedQuantities = new HashMap<>();
        for (ArticleQuantity quantity : articlesQuantity) {
            expectedQuantities.put(quantity.getArticle().getIdArticle(), quantity.getQuantity());
        }

        for (ArticleDetails detail : articlesDetails) {
            int articleId = detail.getArticle().getIdArticle();

            if (expectedQuantities.containsKey(articleId)) {
                double expectedQuantity = expectedQuantities.get(articleId);
                int receivedQuantity = detail.getQuantity();

                if (receivedQuantity < expectedQuantity) {
                    String anomaly = "Nous avons constaté des anomalies pour "
                            + detail.getArticle().getDesignation()
                            + " - Attendue : " + expectedQuantity
                            + "=> Reçue : " + receivedQuantity;
                    anomalies.add(anomaly);
                }
            } else {
                String anomaly = "L'article " + detail.getArticle().getDesignation()
                        + " n'est pas inclus dans notre bon de commande";
                anomalies.add(anomaly);
            }
        }

        return anomalies;
    }

    //constructor
    public SupplierDeliveryOrder(int idSupplierDeliveryOrder, LocalDate delivery_date, PurchaseOrder purchaseOrder, String deliversName, String deliversContact, int status, List<ArticleDetails> listeArticles) {
        this.idSupplierDeliveryOrder = idSupplierDeliveryOrder;
        this.delivery_date = delivery_date;
        this.purchaseOrder = purchaseOrder;
        this.deliversName = deliversName;
        this.deliversContact = deliversContact;
        this.status = status;
        this.listeArticles = listeArticles;
    }

    public SupplierDeliveryOrder(int idSupplierDeliveryOrder, LocalDate delivery_date, PurchaseOrder purchaseOrder, String deliversName, String deliversContact, int status) {
        this.idSupplierDeliveryOrder = idSupplierDeliveryOrder;
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
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        return DisplayUtil.prefix("BDL", 4, getIdSupplierDeliveryOrder());
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
