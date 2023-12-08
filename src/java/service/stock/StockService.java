/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.stock;

import generalisation.GenericDAO.GenericDAO;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import model.stock.EtatStock;
import model.stock.Stock;

/**
 *
 * @author to
 */
public class StockService {
    // Service pour l'etat de stock
    
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
    
    public static void main(String[] args) throws Exception {
        getEtatStock("2023-12-01", "2023-12-10", "", null).showDetail();
    }
}
