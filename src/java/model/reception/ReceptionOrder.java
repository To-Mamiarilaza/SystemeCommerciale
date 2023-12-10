package model.reception;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.purchase.ArticleQuantity;
import model.purchase.PurchaseOrder;
import service.util.DisplayUtil;

/**
 *
 * @author Fy Botas
 */
@DBTable(name = "reception_order", sequenceName = "seq_reception_order")
public class ReceptionOrder {

    @DBField(name = "id_reception_order", isPrimaryKey = true)
    int idReceptionOrder;

    @DBField(name = "reception_date")
    LocalDate receptionDate;

    @DBField(name = "responsable_name")
    String responsableName;

    @DBField(name = "responsable_contact")
    String responsableContact;

    @DBField(name = "status")
    int status;

    @DBField(name = "id_supplier_delivery_order", isForeignKey = true)
    SupplierDeliveryOrder deliveryOrder;

    List<ArticleDetails> listeArticles;

    //methods
    // avoir les annomalies concernant le bon de reception
    public List<String> checkAnomalie(List<ArticleDetails> articlesDetailsDelivery, List<ArticleDetails> articlesDetailsReception) {
        List<String> anomalies = new ArrayList<>();

        for (ArticleDetails deliveryDetail : articlesDetailsDelivery) {
            for (ArticleDetails receptionDetail : articlesDetailsReception) {
                System.out.println("Id delivery details : " + deliveryDetail.getArticle().getIdArticle() + " Id reception article details : " + receptionDetail.getArticle().getIdArticle());
                if (deliveryDetail.getArticle().getIdArticle() != receptionDetail.getArticle().getIdArticle()) {
                    String anomaly = "L'article " + deliveryDetail.getArticle().getDesignation()
                            + " doit être dans les articles commandés";
                    anomalies.add(anomaly);
                }
            }
        }

        return anomalies;
    }

    public String getStatusString(int status) {
        switch (status) {
            case 1:
                return "<label class=\"badge badge-gradient-warning label-width\"> En attente </label>";
            case 2:
                return "<label class=\"badge badge-gradient-success label-width\"> Confirmer </label>";
            case 5:
                return "<label class=\"badge badge-gradient-success label-width\"> Confirmer </label>";
            case 0:
                return "<label class=\"badge badge-gradient-danger label-width\"> Rejeter </label>";
            default:
                break;
        }
        return "";
    }

    //constructors
    public ReceptionOrder(int idReceptionOrder, LocalDate receptionDate, String responsableName, String responsableContact, int status, SupplierDeliveryOrder deliveryOrder, List<ArticleDetails> listeArticles) {
        this.idReceptionOrder = idReceptionOrder;
        this.receptionDate = receptionDate;
        this.responsableName = responsableName;
        this.responsableContact = responsableContact;
        this.status = status;
        this.deliveryOrder = deliveryOrder;
        this.listeArticles = listeArticles;
    }

    public ReceptionOrder(int idReceptionOrder, LocalDate receptionDate, String responsableName, String responsableContact, int status, SupplierDeliveryOrder deliveryOrder) {
        this.idReceptionOrder = idReceptionOrder;
        this.receptionDate = receptionDate;
        this.responsableName = responsableName;
        this.responsableContact = responsableContact;
        this.status = status;
        this.deliveryOrder = deliveryOrder;
    }

    public ReceptionOrder() {
    }

    public SupplierDeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(SupplierDeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public LocalDate getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(LocalDate receptionDate) {
        this.receptionDate = receptionDate;
    }

    public int getIdReceptionOrder() {
        return idReceptionOrder;
    }

    public void setIdReceptionOrder(int idReceptionOrder) {
        this.idReceptionOrder = idReceptionOrder;
    }

        public String getReference() {
        return DisplayUtil.prefix("BDR", 4, getIdReceptionOrder());
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

}
