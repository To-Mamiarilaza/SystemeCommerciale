/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entry;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.reception.ArticleDetails;
import model.reception.ReceptionOrder;

/**
 *
 * @author Fy Botas
 */
@DBTable(name = "entry_order", sequenceName = "seq_entry_order")
public class EntryOrder {

    @DBField(name = "id_entry_order", isPrimaryKey = true)
    int idEntryOrder;

    @DBField(name = "id_reception_order", isForeignKey = true)
    ReceptionOrder receptionOrder;

    @DBField(name = "date")
    LocalDate date;

    @DBField(name = "responsable_name")
    String responsableName;

    @DBField(name = "responsable_contact")
    String responsableContact;

    @DBField(name = "status")
    int status;

    List<ArticleDetails> listeArticle = new ArrayList<>();

    public String getStatusString(int status) {
        switch (status) {
            case 2:
                return "<label class=\"badge badge-gradient-warning label-width\"> En attente </label>";
            case 5:
                return "<label class=\"badge badge-gradient-success label-width\"> Confirmer </label>";
            case 0:
                return "<label class=\"badge badge-gradient-danger label-width\"> Rejeter </label>";
            default:
                break;
        }
        return "";
    }

    public EntryOrder(int idEntryOrder, ReceptionOrder receptionOrder, LocalDate date, String responsableName, String responsableContact, int status, List<ArticleDetails> listeArticle) {
        this.idEntryOrder = idEntryOrder;
        this.receptionOrder = receptionOrder;
        this.date = date;
        this.responsableName = responsableName;
        this.responsableContact = responsableContact;
        this.status = status;
        this.listeArticle = listeArticle;
    }

    public EntryOrder(int idEntryOrder, ReceptionOrder receptionOrder, LocalDate date, String responsableName, String responsableContact, int status) {
        this.idEntryOrder = idEntryOrder;
        this.receptionOrder = receptionOrder;
        this.date = date;
        this.responsableName = responsableName;
        this.responsableContact = responsableContact;
        this.status = status;
    }

    public EntryOrder() {
    }

    public int getIdEntryOrder() {
        return idEntryOrder;
    }

    public void setIdEntryOrder(int idEntryOrder) {
        this.idEntryOrder = idEntryOrder;
    }

    public ReceptionOrder getReceptionOrder() {
        return receptionOrder;
    }

    public void setReceptionOrder(ReceptionOrder receptionOrder) {
        this.receptionOrder = receptionOrder;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ArticleDetails> getListeArticle() {
        return listeArticle;
    }

    public void setListeArticle(List<ArticleDetails> listeArticle) {
        this.listeArticle = listeArticle;
    }

}
