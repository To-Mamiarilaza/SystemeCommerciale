/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.proforma;

import generalisation.GenericDAO.GenericDAO;
import java.sql.Connection;
import java.util.List;
import model.article.Category;
import model.supplier.Supplier;

/**
 *
 * @author To Mamiarilaza
 */
public class SupplierService {
    
    // Get all owned category of the supplier
    public static void loadSupplierOwnedCategory(Supplier supplier, Connection connection) throws Exception {
        String whereClause = "SELECT * FROM category WHERE id_category IN (SELECT id_category FROM supplier_category_product WHERE id_supplier = %d)";
        whereClause = String.format(whereClause, supplier.getIdSupplier());
        List<Category> categoryList = (List<Category>) GenericDAO.directQuery(Category.class, whereClause, connection);
        supplier.setOwnedCategoryList(categoryList);
    }
    
    // Get all supplier email
    public static String[] getSuppliersEmail(Connection connection) throws Exception {
        List<Supplier> suppliersEmail = (List<Supplier>) GenericDAO.getAll(Supplier.class, null, connection);
        String[] emails = new String[suppliersEmail.size()];
        
        for (int i = 0; i < emails.length; i++) {
            emails[i] = suppliersEmail.get(i).getMail();
        }
        
        return emails;
    }
}
