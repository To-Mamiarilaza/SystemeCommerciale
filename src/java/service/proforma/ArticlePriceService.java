/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.proforma;

import connection.DBConnection;
import generalisation.GenericDAO.GenericDAO;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import model.article.Article;
import model.purchase.SupplierArticlePrice;
import model.supplier.Supplier;

/**
 *
 * @author To Mamiarilaza
 */
public class ArticlePriceService {
    // Class that manage the price of article in supplier
    
    // Get all article
    public static List<Article> getAllArticle(Connection connection) throws Exception {
        List<Article> articleList = (List<Article>) GenericDAO.getAll(Article.class, null, connection);
        return articleList;
    }
    
    // Save to database the price of article in a supplier
    public static void insertArticlePrice(String idSupplier, String idArticle, String unitPrice) throws Exception {
        // Check all input data
        if (idSupplier == null || idSupplier.trim().equals("")) {
            throw new Exception("L' ID du fournisseur ne doit pas être null !");
        }
        if (idArticle == null || idArticle.trim().equals("")) {
            throw new Exception("L' ID de l'article ne doit pas être null !");
        }
        if (unitPrice == null || unitPrice.trim().equals("")) {
            throw new Exception("Le prix unitaire ne doit pas être null !");
        }
        
        // Save to database
        Connection connection = DBConnection.getConnection();
        
        Supplier supplier = GenericDAO.findById(Supplier.class, Integer.valueOf(idSupplier), connection);
        Article article = GenericDAO.findById(Article.class, Integer.valueOf(idArticle), connection);
        double unitPriceValue = Double.valueOf(unitPrice);
        
        SupplierArticlePrice articlePrice = new SupplierArticlePrice(LocalDate.now(), article, supplier, unitPriceValue, false, 1);
        System.out.println("Article : " + articlePrice);
        GenericDAO.save(articlePrice, connection);
        
        connection.commit();
        connection.close();
    }
    
    public static void deleteArticlePrice(String idArticlePrice) throws Exception {
        if (idArticlePrice == null || idArticlePrice.trim().equals("")) {
            throw new Exception("L' ID du prix d'article par fournisseur ne doit pas être null !");
        }
        
        String query = "UPDATE supplier_article_price SET status = 0 WHERE id_supplier_article_price = %d";
        query = String.format(query, Integer.valueOf(idArticlePrice));
        
        GenericDAO.directUpdate(query, null);
    }
    
    public static void chooseSupplierForArticle(String idSupplierArticlePrice, boolean etat) throws Exception {
        if (idSupplierArticlePrice == null || idSupplierArticlePrice.trim().equals("")) {
            throw new Exception("L' ID du prix d'article par fournisseur ne doit pas être null !");
        }
        
        Connection connection = DBConnection.getConnection();
        
        SupplierArticlePrice supplierArticlePrice = GenericDAO.findById(SupplierArticlePrice.class, Integer.valueOf(idSupplierArticlePrice), connection);
        
        // Setting all to false
        String query = "UPDATE supplier_article_price SET chosen = false WHERE status = 1 AND date + 30 >= CURRENT_DATE AND id_article = %d";
        query = String.format(query, supplierArticlePrice.getArticle().getIdArticle());
        GenericDAO.directUpdate(query, connection);
        
        // Update the chosen to true
        if (etat) {
            query = "UPDATE supplier_article_price SET chosen = true WHERE id_supplier_article_price = %d";
            query = String.format(query, supplierArticlePrice.getIdSupplierArticlePrice());
            GenericDAO.directUpdate(query, connection);
        }
        
        connection.commit();
        connection.close();
    }
}
