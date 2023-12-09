/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.sale;

import connection.DBConnection;
import generalisation.GenericDAO.GenericDAO;
import java.sql.Connection;
import java.util.List;
import model.article.Article;
import model.stock.ArticleSalePrice;

/**
 *
 * @author to
 */
public class SaleService {
    // Classe responsable des ventes
    public static double getSalePrice(Article article, Connection connection) throws Exception {
        List<ArticleSalePrice> salePrice = (List<ArticleSalePrice>) GenericDAO.getAll(ArticleSalePrice.class, "WHERE id_article = " + article.getIdArticle(), connection);
        if (salePrice.size() == 0) {
            throw new Exception("Cette article n'éxiste pas dans le stock !");
        }
        
        return salePrice.get(0).getSalePrice();
    }
}
