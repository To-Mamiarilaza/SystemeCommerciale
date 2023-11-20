/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.supplier;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import model.article.Category;

/**
 *
 * @author Fy Botas
 */
@DBTable(name = "supplier_category_product", sequenceName = "seq_supplier_category_product")
public class SupplierCategoryProduct {

    @DBField(name = "id_supplier_category_product", isPrimaryKey = true)
    int idSupplierCategoryProduct;
    @DBField(name = "id_supplier", isForeignKey = true)
    Supplier supplier;
    @DBField(name = "id_category", isForeignKey = true)
    Category category;

    public SupplierCategoryProduct(int idSupplierCategoryProduct, Supplier supplier, Category category) {
        this.idSupplierCategoryProduct = idSupplierCategoryProduct;
        this.supplier = supplier;
        this.category = category;
    }

    public SupplierCategoryProduct() {
    }

    public int getIdSupplierCategoryProduct() {
        return idSupplierCategoryProduct;
    }

    public void setIdSupplierCategoryProduct(int idSupplierCategoryProduct) {
        this.idSupplierCategoryProduct = idSupplierCategoryProduct;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
