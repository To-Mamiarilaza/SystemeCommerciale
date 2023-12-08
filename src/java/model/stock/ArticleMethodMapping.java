/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.stock;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import model.article.Article;

/**
 *
 * @author to
 */
@DBTable(name = "article_method_mapping", sequenceName = "seq_article_method_mapping")
public class ArticleMethodMapping {
    // Field
    @DBField(name = "id_article_method_mapping", isPrimaryKey = true)
    int idArticleMethodMapping;
    @DBField(name = "id_article", isForeignKey = true)
    Article article;
    @DBField(name = "id_gestion_method", isForeignKey = true)
    GestionMethod gestionMethod;
    
    // Getter and setter

    public int getIdArticleMethodMapping() {
        return idArticleMethodMapping;
    }

    public void setIdArticleMethodMapping(int idArticleMethodMapping) {
        this.idArticleMethodMapping = idArticleMethodMapping;
    }
    
    public void setIdArticleMethodMapping(String idArticleMethodMapping) throws Exception {
        if (idArticleMethodMapping == null || idArticleMethodMapping.trim().equals("")) {
            throw new Exception("L' ID du article mapping method ne doit pas être vide !");
        }
        this.idArticleMethodMapping = Integer.valueOf(idArticleMethodMapping);
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    
    public void setArticle(String idArticle) throws Exception {
        if (idArticle == null || idArticle.trim().equals("")) {
            throw new Exception("L' ID de l'article ne doit pas être null !");
        }
        Article article = new Article();
        article.setIdArticle(Integer.valueOf(idArticle));
        setArticle(article);
    }

    public GestionMethod getGestionMethod() {
        return gestionMethod;
    }

    public void setGestionMethod(GestionMethod gestionMethod) {
        this.gestionMethod = gestionMethod;
    }
    
    public void setGestionMethod(String idGestionMethod) throws Exception {
        if (idGestionMethod == null || idGestionMethod.trim().equals("")) {
            throw new Exception("L' ID du methode de gestion ne doit pas être null !");
        }
        
        GestionMethod gestionMethod = new GestionMethod();
        gestionMethod.setIdGestionMethod(Integer.valueOf(idGestionMethod));
        
        setGestionMethod(gestionMethod);
    }
    
    // Constructor

    public ArticleMethodMapping() {
    }
    
    public ArticleMethodMapping(int idArticleMethodMapping, Article article, GestionMethod gestionMethod) {
        this.idArticleMethodMapping = idArticleMethodMapping;
        this.article = article;
        this.gestionMethod = gestionMethod;
    }
    
    public ArticleMethodMapping(String idArticle, String idGestionMethod) throws Exception {
        setArticle(idArticle);
        setGestionMethod(idGestionMethod);
    }
    
    public ArticleMethodMapping(String idArticleMethodMapping) throws Exception {
        setIdArticleMethodMapping(idArticleMethodMapping);
    }
    
}
