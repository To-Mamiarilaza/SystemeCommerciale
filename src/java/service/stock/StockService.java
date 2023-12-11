/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.stock;

import connection.DBConnection;
import generalisation.GenericDAO.GenericDAO;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import model.article.Article;
import model.purchase.ArticleQuantity;
import model.stock.EtatStock;
import model.stock.Stock;

/**
 *
 * @author to
 */
public class StockService {
    // Service pour l'etat de stock
    
    // Get the remaining quantity of an article
    public static double getRemainQuantity(Article article, Connection connection) throws Exception {
        LocalDate now = LocalDate.now();
        String idArticle = String.valueOf(article.getIdArticle());
        EtatStock etatStock = getEtatStock(now.toString(), now.toString(), idArticle, connection);
        if (etatStock.getStockList().size() == 0) {
            return 0;
        }
        else return etatStock.getStockList().get(0).getRemain();
    }
    
    // Check if there is available quantity on the store
    public static void checkStock(ArticleQuantity articleQuantity, Connection connection) throws Exception {
        double remain = getRemainQuantity(articleQuantity.getArticle(), connection);
        if (remain < articleQuantity.getQuantity()) {
            throw new Exception("Il n'y a pas assez de stock pour l' article " + articleQuantity.getArticle().getDesignation());
        }
    }
    
    // Fonction pour prendre l'etat de stock
    public static double getStockAmount(List<Stock> stockList) {
        double sum = 0;
        for (Stock stock :
                stockList) {
            sum += stock.getAmount();
        }
        return sum;
    }
    
    // Get the remaining and stock value between two date
    public static EtatStock getEtatStock(String dateDebut, String dateFin, String idArticle, Connection connection) throws Exception {
        LocalDate dateDebutValue = LocalDate.parse(dateDebut);  // Throw if date debut not given
        LocalDate dateFinValue = LocalDate.parse(dateFin);
        
        String query = "SELECT * FROM get_stock_availability('" + dateDebut + "', '" + dateFin + "') WHERE code LIKE '%" + idArticle + "%'";

        List<Stock> stockList = (List<Stock>) GenericDAO.directQuery(Stock.class, query, connection);
        double stockAmount = getStockAmount(stockList);

        return new EtatStock(dateDebutValue, dateFinValue, idArticle, stockList, stockAmount);
    }
}
