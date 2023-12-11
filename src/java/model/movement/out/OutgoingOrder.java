/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.movement.out;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.article.Article;
import model.base.Service;
import model.purchase.ArticleQuantity;
import model.purchase.PurchaseOrder;
import model.purchaseClient.ArticleOrder;
import model.purchaseClient.PurchaseOrderClient;
import service.util.DisplayUtil;

/**
 *
 * @author to
 */
@DBTable(name = "outgoing_order", sequenceName = "seq_outgoing_order")
public class OutgoingOrder {

    /// Field
    @DBField(name = "id_outgoing_order", isPrimaryKey = true)
    Integer idOutgoingOrder;

    @DBField(name = "date")
    LocalDate date;

    @DBField(name = "responsable_name")
    String responsableName;

    @DBField(name = "responsable_contact")
    String responsableContact;

    @DBField(name = "motif")
    String motif;

    @DBField(name = "id_purchase_order", isForeignKey = true)
    PurchaseOrderClient purchaseOrderClient;

    @DBField(name = "id_service", isForeignKey = true)
    Service service;

    @DBField(name = "status")
    Integer status;

    List<OutgoingOrderDetail> details;

    // Gettter and setter
    public Integer getIdOutgoingOrder() {
        return idOutgoingOrder;
    }

    public String getReference() {
        return DisplayUtil.prefix("BDS", 4, getIdOutgoingOrder());
    }

    public void setIdOutgoingOrder(Integer idOutgoingOrder) {
        this.idOutgoingOrder = idOutgoingOrder;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getResponsableName() {
        return responsableName;
    }

    public void setResponsableName(String responsableName) {
        this.responsableName = responsableName;
    }

    public String getResponsableContact() {
        return responsableContact;
    }

    public void setResponsableContact(String responsableContact) {
        this.responsableContact = responsableContact;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getOrderType() {
        return getService() == null ? "Vente" : "Besoin interne";
    }

    public PurchaseOrderClient getPurchaseOrderClient () {
        return purchaseOrderClient;
    }

    public String getPurchaseOrderDisplay() {
        return purchaseOrderClient == null ? "" : DisplayUtil.prefix("BDC", 4, purchaseOrderClient.getIdPurchaseOrderClient());
    }

    public void setPurchaseOrderClient(PurchaseOrderClient purchaseOrderClient) {
        this.purchaseOrderClient = purchaseOrderClient;
    }

    public Service getService() {
        return service;
    }

    public String getServiceDisplay() {
        return service == null ? "" : service.getService();
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Integer getStatus() {
        return status;
    }

    public String getStatusDisplay() {
        if (getStatus() == 1) {
            return "En Attente";
        } else if (getStatus() == 10) {
            return "Validé";
        }
        return "Refusé";
    }

    public String getStatusClass() {
        if (getStatus() == 1) {
            return "warning";
        } else if (getStatus() == 10) {
            return "success";
        }
        return "danger";
    }

    public String getStatusString(int status) {
        switch (status) {
            case 10:
                return "<label class=\"badge badge-gradient-warning label-width\"> Non traité </label>";
            case 15:
                return "<label class=\"badge badge-gradient-success label-width\"> Confirmer </label>";
            case 0:
                return "<label class=\"badge badge-gradient-danger label-width\"> Rejeter </label>";
            default:
                break;
        }
        return "";
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<OutgoingOrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OutgoingOrderDetail> details) {
        this.details = details;
    }

    // Constructor
    public OutgoingOrder() {
    }

    public OutgoingOrder(Integer idOutgoingOrder, LocalDate date, String responsableName, String responsableContact, String motif, PurchaseOrderClient purchaseOrderClient, Service service, Integer status) {
        this.idOutgoingOrder = idOutgoingOrder;
        this.date = date;
        this.responsableName = responsableName;
        this.responsableContact = responsableContact;
        this.motif = motif;
        this.purchaseOrderClient = purchaseOrderClient;
        this.service = service;
        this.status = status;
    }

    public OutgoingOrder(LocalDate date, String responsableName, String responsableContact, String motif, PurchaseOrderClient purchaseOrderClient, Service service, Integer status) {
        this.date = date;
        this.responsableName = responsableName;
        this.responsableContact = responsableContact;
        this.motif = motif;
        this.purchaseOrderClient = purchaseOrderClient;
        this.service = service;
        this.status = status;
    }

    public OutgoingOrder(ServiceRequest serviceRequest) {
        setService(serviceRequest.getService());
        List<OutgoingOrderDetail> details = new ArrayList<>();
        for (ArticleQuantity articleQuantity : serviceRequest.getArticleQuantities()) {
            details.add(new OutgoingOrderDetail(0, articleQuantity.getArticle(), articleQuantity.getQuantity()));
        }
        setDetails(details);
    }
    
    public OutgoingOrder(PurchaseOrderClient purchaseOrderClient) {
        setPurchaseOrderClient(purchaseOrderClient);
        List<OutgoingOrderDetail> details = new ArrayList<>();
        for (ArticleOrder article : purchaseOrderClient.getArticleOrder()) {
            details.add(new OutgoingOrderDetail(0, article.getArticle(), article.getQuantity()));
        }
        setDetails(details);
    }

    // Method
    public void removeDetail(String designation, String quantity) {
        double quantityValue = Double.valueOf(quantity);
        OutgoingOrderDetail targetDetail = null;
        for (OutgoingOrderDetail detail : details) {
            if (detail.getArticle().getDesignation().equals(designation) && detail.getQuantity() == quantityValue) {
                targetDetail = detail;
            }
        }
        if (targetDetail != null) {
            getDetails().remove(targetDetail);
        }
    }

    public void addNewDetail(String idArticle, String designation, String quantity) throws Exception {
        if (idArticle == null || idArticle.trim().equals("")) {
            throw new Exception("L' ID de l'article ne doit pas être null !");
        }

        if (quantity == null || quantity.trim().equals("")) {
            throw new Exception("La quantite ne doit pas être null !");
        }

        Article article = new Article();
        article.setIdArticle(Integer.valueOf(idArticle));
        article.setDesignation(designation);

        double quantityValue = Double.valueOf(quantity);

        OutgoingOrderDetail newDetail = new OutgoingOrderDetail(0, article, quantityValue);
        getDetails().add(newDetail);
        showDetails();
    }

    public void showDetails() {
        System.out.println("BON DE SORTIE :");
        if (getService() != null) {
            System.out.println("Service : " + getService().getService());
        }
        if (getPurchaseOrderClient() != null) {
            System.out.println("Purchase Order : " + getPurchaseOrderClient().getIdString());
        }
        for (OutgoingOrderDetail detail : details) {
            System.out.println("- " + detail.getArticle().getDesignation() + " : " + detail.getQuantity());
        }
    }
}
