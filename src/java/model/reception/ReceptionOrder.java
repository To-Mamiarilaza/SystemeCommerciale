package model.reception;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.time.LocalDate;
import java.util.List;
import model.purchase.PurchaseOrder;

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

    @DBField(name = "reference")
    String reference;

    @DBField(name = "id_supplier_delivery_order", isForeignKey = true)
    SupplierDeliveryOrder deliveryOrder;

    List<ArticleDetails> listeArticles;

    //methods
    //constructors
    public ReceptionOrder(int idReceptionOrder, LocalDate receptionDate, String responsableName, String responsableContact, int status, String reference, SupplierDeliveryOrder deliveryOrder, List<ArticleDetails> listeArticles) {
        this.idReceptionOrder = idReceptionOrder;
        this.receptionDate = receptionDate;
        this.responsableName = responsableName;
        this.responsableContact = responsableContact;
        this.status = status;
        this.reference = reference;
        this.deliveryOrder = deliveryOrder;
        this.listeArticles = listeArticles;
    }

    public ReceptionOrder(int idReceptionOrder, LocalDate receptionDate, String responsableName, String responsableContact, int status, String reference, SupplierDeliveryOrder deliveryOrder) {
        this.idReceptionOrder = idReceptionOrder;
        this.receptionDate = receptionDate;
        this.responsableName = responsableName;
        this.responsableContact = responsableContact;
        this.status = status;
        this.reference = reference;
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
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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
        return 1;
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
