/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.proforma;

import connection.DBConnection;
import generalisation.GenericDAO.GenericDAO;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.article.Article;
import model.purchase.ArticleQuantity;
import model.purchase.ArticleRequest;
import model.purchase.InvoiceLineItem;
import model.purchase.PaymentCondition;
import model.purchase.PaymentMethod;
import model.purchase.Proforma;
import model.purchase.PurchaseOrder;
import model.purchase.PurchaseOrderLineItem;
import model.purchase.SupplierArticlePrice;
import model.supplier.Supplier;

/**
 *
 * @author To Mamiarilaza
 */
public class ProformaService {
    // A class who provide all required method for proforma managing
    
    // save a purchase order to the database
    public static void savePurchaseOrder(PurchaseOrder purchaseOrder, Connection connection) throws Exception {
        // Save the main purchaseOrder
        GenericDAO.save(purchaseOrder, connection);
        
        // Insert all payment condition
        for (PaymentCondition paymentCondition : purchaseOrder.getPaymentConditions()) {
            paymentCondition.setIdPurchaseOrder(purchaseOrder.getIdPurchaseOrder());
            GenericDAO.save(paymentCondition, connection);
        }
        
        // Save all line
        for (InvoiceLineItem lineItem : purchaseOrder.getLineItems()) {
            PurchaseOrderLineItem poLineItem = new PurchaseOrderLineItem(purchaseOrder.getIdPurchaseOrder(), lineItem);
            GenericDAO.save(poLineItem, connection);
            
            
            
            // Update all concerned article quantity line to 2
            List<ArticleQuantity> articleQuantityList = getRequestQuantityDetail(poLineItem.getArticle(), connection);
            for (ArticleQuantity articleQuantity : articleQuantityList) {
                articleQuantity.setStatus(2);   // Article quantity deja traité
                double articleAmount = (lineItem.getUnitPrice() * articleQuantity.getQuantity()) * (1 + (articleQuantity.getArticle().getTva() / 100));
                articleQuantity.setAmount(articleAmount);
                articleQuantity.setIdPurchaseOrder(purchaseOrder.getIdPurchaseOrder());
                GenericDAO.updateById(articleQuantity, articleQuantity.getIdArticleQuantity(), connection);
            }
            
            // Unchosen supplier 
            String query = "UPDATE supplier_article_price SET chosen = false WHERE id_article = " + poLineItem.getArticle().getIdArticle();
            GenericDAO.directUpdate(query, connection);
            
        }
        
    }
    
    // get all payment method
    public static List<PaymentMethod> getAllPaymentMehod(Connection connection) throws Exception {
        List<PaymentMethod> paymentMethods = (List<PaymentMethod>) GenericDAO.getAll(PaymentMethod.class, null, connection);
        return paymentMethods;
    }
    
    // calcul sum of tva_amount, ht_amount, ttc_amount
    public static double[] getInvoiceTotalAmount(List<InvoiceLineItem> invoiceLineItems) {
        double[] results = new double[3];
        
        results[0] = 0;     // TVA Amount
        results[1] = 0;     // HT Amount
        results[2] = 0;     // TTC Amount
        
        for (InvoiceLineItem invoiceLineItem : invoiceLineItems) {
            results[0] += invoiceLineItem.getTVAAmount();
            results[1] += invoiceLineItem.getHTAmount();
            results[2] += invoiceLineItem.getTTCAmount();
        }
        
        return results;
    }
    
    // get supplier proforma
    public static Proforma getProforma(String idSupplier) throws Exception {
        if (idSupplier == null || idSupplier.trim().equals("")) {
            throw new Exception("L' ID du fournisseur ne doit pas être vide !");
        }
        
        int idSupplierValue = Integer.valueOf(idSupplier);
        
        Connection connection = DBConnection.getConnection();
        
        Supplier supplier = GenericDAO.findById(Supplier.class, idSupplier, connection);
        Proforma proforma =  getProforma(supplier, connection);
        
        connection.close();
        
        return proforma;
    }
    
    // get supplier proforma
    public static Proforma getProforma(String idSupplier, Connection connection) throws Exception {
        if (idSupplier == null || idSupplier.trim().equals("")) {
            throw new Exception("L' ID du fournisseur ne doit pas être vide !");
        }
        
        int idSupplierValue = Integer.valueOf(idSupplier);
        
        Supplier supplier = GenericDAO.findById(Supplier.class, idSupplier, connection);
        Proforma proforma =  getProforma(supplier, connection);
        
        return proforma;
    }
    
    public static Proforma getProforma(Supplier supplier, Connection connection) throws Exception {
        // Get all invoice Line
        LocalDate date = LocalDate.now();

        String query = "SELECT * FROM get_supplier_proforma(%d)";
        query = String.format(query, supplier.getIdSupplier());
        List<InvoiceLineItem> invoiceLineItems = (List<InvoiceLineItem>) GenericDAO.directQuery(InvoiceLineItem.class, query, connection);
        
        double[] totalAmount = getInvoiceTotalAmount(invoiceLineItems);
        
        Proforma proforma = new Proforma();
        proforma.setDate(date);
        proforma.setSupplier(supplier);
        proforma.setInvoiceLineItems(invoiceLineItems);
        proforma.setTotalTVA(totalAmount[0]);
        proforma.setTotalHT(totalAmount[1]);
        proforma.setTotalTTC(totalAmount[2]);
        
        return proforma;
    }
    
    // get all available proforma
    public static List<Proforma> getAllAvailableProforma(Connection connection) throws Exception {
        List<Proforma> proformas = new ArrayList<>();
                
        String query = "SELECT * FROM v_chosen_supplier";
        List<Supplier> supplierWithProforma = (List<Supplier>) GenericDAO.directQuery(Supplier.class, query, connection);
        
        for (Supplier supplier : supplierWithProforma) {
            proformas.add(getProforma(supplier, connection));
        }
        
        return proformas;
    }
    
    // get all request quantity source of the article request
    public static List<ArticleQuantity> getRequestQuantityDetail(Article article, Connection connection) throws Exception {
        String query = "SELECT * FROM v_article_quantity_valid WHERE id_article = " + article.getIdArticle();
        List<ArticleQuantity> articleQuantityList = (List<ArticleQuantity>) GenericDAO.directQuery(ArticleQuantity.class, query, connection);
        return articleQuantityList;
    }
    
    // get supplier who sell the category of the article
    public static List<Supplier> getConvenableSupplier(Article article, Connection connection) throws Exception {
        String query = "SELECT * FROM supplier WHERE id_supplier IN (SELECT id_supplier FROM supplier_category_product WHERE id_category = %s)";
        query = String.format(query, article.getCategory().getIdCategory());
        List<Supplier> convenableSupplier = (List<Supplier>) GenericDAO.directQuery(Supplier.class, query, connection);
        return convenableSupplier;
    }
    
    // get all supplier price of the article
    public static List<SupplierArticlePrice> getAllSupplierArticlePrice(Article article, Connection connection) throws Exception {
        String query = "SELECT * FROM v_supplier_article_price_valid WHERE id_article = " + article.getIdArticle() + "ORDER BY unit_price ASC";
        List<SupplierArticlePrice> supplilerArticlePrices = (List<SupplierArticlePrice>) GenericDAO.directQuery(SupplierArticlePrice.class, query, connection);
        return supplilerArticlePrices;
    }
    
    // get all article request
    public static List<ArticleRequest> getAllArticleRequest(Connection connection) throws Exception {
        List<ArticleRequest> articleRequests = (List<ArticleRequest>) GenericDAO.getAll(ArticleRequest.class, null, connection);
        
        for (ArticleRequest articleRequest : articleRequests) {
            List<ArticleQuantity> articleQuantityList = getRequestQuantityDetail(articleRequest.getArticle(), connection);
            List<Supplier> convenableSupplier = getConvenableSupplier(articleRequest.getArticle(), connection);
            List<SupplierArticlePrice> supplierArticlePriceList = getAllSupplierArticlePrice(articleRequest.getArticle(), connection);
            
            articleRequest.setArticleQuantityDetails(articleQuantityList);
            articleRequest.setConvenableSuppliers(convenableSupplier);
            articleRequest.setSupplierArticlePrices(supplierArticlePriceList);
        }
        
        return articleRequests;
    }
    
    // get all article request
    public static List<ArticleRequest> getAllArticleRequest(String idArticle, Connection connection) throws Exception {
        if (idArticle == null || idArticle.trim().equals("")) {
            throw new Exception("L' ID de l'article ne doit pas être vide !");
        }
        
        String whereClause = "WHERE id_article = " + idArticle;
        List<ArticleRequest> articleRequests = (List<ArticleRequest>) GenericDAO.getAll(ArticleRequest.class, whereClause, connection);
        
        for (ArticleRequest articleRequest : articleRequests) {
            List<ArticleQuantity> articleQuantityList = getRequestQuantityDetail(articleRequest.getArticle(), connection);
            List<Supplier> convenableSupplier = getConvenableSupplier(articleRequest.getArticle(), connection);
            List<SupplierArticlePrice> supplierArticlePriceList = getAllSupplierArticlePrice(articleRequest.getArticle(), connection);
            
            articleRequest.setArticleQuantityDetails(articleQuantityList);
            articleRequest.setConvenableSuppliers(convenableSupplier);
            articleRequest.setSupplierArticlePrices(supplierArticlePriceList);
        }
        
        return articleRequests;
    }
}
