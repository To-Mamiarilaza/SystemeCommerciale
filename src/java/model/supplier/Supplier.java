/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.supplier;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.src.generalisation.utils.GenericUtil;
import java.util.ArrayList;
import java.util.List;
import model.article.Category;

/**
 *
 * @author To Mamiarilaza
 */
@DBTable(name = "supplier", sequenceName = "seq_supplier")
public class Supplier {

    // Field
    @DBField(name = "id_supplier", isPrimaryKey = true)
    int idSupplier;

    @DBField(name = "supplier_name")
    String supplierName;

    @DBField(name = "supplier_address")
    String supplierAddress;

    @DBField(name = "responsable_contact")
    String responsableContact;

    @DBField(name = "mail")
    String mail;

    @DBField(name = "status")
    int status;

    List<Category> ownedCategoryList = new ArrayList<>();

    public void setCategory() throws Exception {
        List<SupplierCategoryProduct> supplierCategory = (List<SupplierCategoryProduct>) GenericDAO.directQuery(SupplierCategoryProduct.class, "select * from supplier_category_product where id_supplier = " + this.getIdSupplier(), null);
        for (int i = 0; i < supplierCategory.size(); i++) {
            Category cat = (Category)GenericDAO.findById(Category.class, supplierCategory.get(i).getCategory(), null);
            this.getOwnedCategoryList().add(cat);
        }
    }
    // Getter and setter

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getResponsableContact() {
        return responsableContact;
    }

    public void setResponsableContact(String responsableContact) {
        this.responsableContact = responsableContact;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Category> getOwnedCategoryList() {
        return ownedCategoryList;
    }

    public void setOwnedCategoryList(List<Category> ownedCategoryList) {
        this.ownedCategoryList = ownedCategoryList;
    }

    // Constructor
    public Supplier() {
    }

    public Supplier(String supplierName, String supplierAddress, String responsableContact, String mail, int status) {
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.responsableContact = responsableContact;
        this.mail = mail;
        this.status = status;
    }

    public Supplier(int idSupplier, String supplierName, String supplierAddress, String responsableContact, String mail, int status) {
        this.idSupplier = idSupplier;
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.responsableContact = responsableContact;
        this.mail = mail;
        this.status = status;
    }
}
