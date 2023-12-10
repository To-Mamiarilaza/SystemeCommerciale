/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dept;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.movement.out.OutgoingOrder;
import model.reception.ArticleDetails;
import service.util.DisplayUtil;

/**
 *
 * @author Fy Botas
 */
@DBTable(name = "dept_reception", sequenceName = "seq_dept_reception")
public class DeptReception {

    @DBField(name = "id_dept_reception", isPrimaryKey = true)
    int idDeptReception;

    @DBField(name = "responsable_name")
    String responsableName;

    @DBField(name = "responsable_contact")
    String responsableContact;

    @DBField(name = "date")
    LocalDate date;

    @DBField(name = "status")
    int status;

    @DBField(name = "id_outgoing_order", isForeignKey = true)
    OutgoingOrder outgoingOrder;
    
    List<ArticleDetails> listeArticles = new ArrayList<>();

    public DeptReception(int idDeptReception, OutgoingOrder outgoingOrder, String responsableName, String responsableContact, LocalDate date, int status, List<ArticleDetails> listeArticles) {
        this.idDeptReception = idDeptReception;
        this.outgoingOrder = outgoingOrder;
        this.responsableName = responsableName;
        this.responsableContact = responsableContact;
        this.date = date;
        this.status = status;
        this.listeArticles = listeArticles;
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

    public DeptReception(int idDeptReception, String responsableName, String responsableContact, LocalDate date, int status, OutgoingOrder outgoingOrder) {
        this.idDeptReception = idDeptReception;
        this.responsableName = responsableName;
        this.responsableContact = responsableContact;
        this.date = date;
        this.status = status;
        this.outgoingOrder = outgoingOrder;
    }

    public String getReference() {
        return DisplayUtil.prefix("ACR", 4, getIdDeptReception());
    }

    public DeptReception() {
    }

    public int getIdDeptReception() {
        return idDeptReception;
    }

    public void setIdDeptReception(int idDeptReception) {
        this.idDeptReception = idDeptReception;
    }

    public OutgoingOrder getOutgoingOrder() {
        return outgoingOrder;
    }

    public void setOutgoingOrder(OutgoingOrder outgoingOrder) {
        this.outgoingOrder = outgoingOrder;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ArticleDetails> getListeArticles() {
        return listeArticles;
    }

    public void setListeArticles(List<ArticleDetails> listeArticles) {
        this.listeArticles = listeArticles;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
