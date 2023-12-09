/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dept;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.time.LocalDate;
import java.util.List;
import model.reception.ArticleDetails;

/**
 *
 * @author Fy Botas
 */
@DBTable(name = "dept_reception", sequenceName = "seq_dept_reception")
public class DeptReception {

    @DBField(name = "id_dept_reception", isPrimaryKey = true)
    int idDeptReception;

    @DBField(name = "out_order")
    String outOrder; // need bon de sortie Class 

    @DBField(name = "responsable_name")
    String responsableName;

    @DBField(name = "responsable_contact")
    String responsableContact;

    @DBField(name = "date")
    LocalDate date;

    @DBField(name = "status")
    int status;
    List<ArticleDetails> listeArticles;

    public DeptReception(int idDeptReception, String outOrder, String responsableName, String responsableContact, LocalDate date, int status, List<ArticleDetails> listeArticles) {
        this.idDeptReception = idDeptReception;
        this.outOrder = outOrder;
        this.responsableName = responsableName;
        this.responsableContact = responsableContact;
        this.date = date;
        this.status = status;
        this.listeArticles = listeArticles;
    }

    public DeptReception(int idDeptReception, String outOrder, String responsableName, String responsableContact, LocalDate date, int status) {
        this.idDeptReception = idDeptReception;
        this.outOrder = outOrder;
        this.responsableName = responsableName;
        this.responsableContact = responsableContact;
        this.date = date;
        this.status = status;
    }

    public DeptReception() {
    }

    public int getIdDeptReception() {
        return idDeptReception;
    }

    public void setIdDeptReception(int idDeptReception) {
        this.idDeptReception = idDeptReception;
    }

    public String getOutOrder() {
        return outOrder;
    }

    public void setOutOrder(String outOrder) {
        this.outOrder = outOrder;
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
