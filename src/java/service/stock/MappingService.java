/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.stock;

import generalisation.GenericDAO.GenericDAO;
import generalisation.utils.GenericUtil;
import java.sql.Connection;
import java.util.List;
import model.article.Article;
import model.stock.ArticleMethodMapping;
import model.stock.GestionMethod;

/**
 *
 * @author to
 */
public class MappingService {
    // Classe pour le gestion de stock
    
    // Prendre tous les methodes de gestion
    public static List<GestionMethod> getAllGestionMethod(Connection connection) throws Exception {
        return (List<GestionMethod>) GenericDAO.getAll(GestionMethod.class, null, connection);
    }
    
    // Prendre tous les articles avec leurs methode de gestion
    public static List<ArticleMethodMapping> getAllArticleMethod(Connection connection) throws Exception {
        return (List<ArticleMethodMapping>) GenericDAO.getAll(ArticleMethodMapping.class, null, connection);
    }
    
    // Trouver la methode de gestion d'une article
    public static ArticleMethodMapping getArticleMethod(Article article, Connection connection) throws Exception {
        String whereClause = "WHERE id_article = " + article.getIdArticle();
        List<ArticleMethodMapping> mapping = (List<ArticleMethodMapping>) GenericDAO.getAll(ArticleMethodMapping.class, whereClause, connection);
        if (mapping.size() == 0) {
            throw new Exception("L' article " + article.getDesignation() + " n'est pas encore mappé a une methode !");
        }
        return mapping.get(0);
    }
    
    // Get all article without article method
    public static List<Article> getMissingMethodArticle(Connection connection) throws Exception {
        String whereClause = "WHERE id_article NOT IN (SELECT id_article FROM article_method_mapping)";
        return (List<Article>) GenericDAO.getAll(Article.class, whereClause, connection);
    }
    
    // Set new mapping
    public static void setArticleMappingMethod(String idArticle, String idGestionMethod) throws Exception {
        ArticleMethodMapping newMapping = new ArticleMethodMapping(idArticle, idGestionMethod);
        GenericDAO.save(newMapping, null);
    }
    
    // Remove mapping
    public static void removeArticleMappingMethod(String idArticleMappingMethod) throws Exception {
        GenericDAO.deleteById(ArticleMethodMapping.class, idArticleMappingMethod, null);
    }
    
}
