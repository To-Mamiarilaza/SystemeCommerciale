/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.article;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import model.stock.ArticleMethodMapping;

/**
 *
 * @author chalman
 */
@DBTable(name = "article", sequenceName = "seq_article")
public class Article {
    @DBField(name = "id_article", isPrimaryKey = true)
    int idArticle;
    @DBField(name = "code")
    String code;
    @DBField(name = "description")
    String description;
    @DBField(name = "designation")
    String designation;
    @DBField(name = "id_category", isForeignKey = true)
    Category category;
    @DBField(name = "tva")
    Double tva;
    @DBField(name = "id_unity", isForeignKey = true)
    Unity unity;
    @DBField(name = "status")
    int status;

///Getters et setters
    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) throws Exception {
        if(code.trim().equals("") || code == null) {
            throw new Exception("Aucune valeur de code saisie");
        }
        this.code = code;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) throws Exception {
        if(description.trim().equals("") || description == null) {
            throw new Exception("Aucune valeur de description saisie");
        }
        this.description = description;
    }

    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) throws Exception {
        if(designation.trim().equals("") || designation == null) {
            throw new Exception("Aucune valeur de designation saisie");
        }
        this.designation = designation;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setCategory(String category) throws Exception {
        if(category.trim().equals("") || category == null) {
            throw new Exception("Aucune valeur de categorie saisie");
        }
        Integer idCategory = Integer.valueOf(category);
        Category categoryFind = GenericDAO.findById(Category.class, idCategory, null);
        this.setCategory(categoryFind);
    }

    public Double getTva() {
        return tva;
    }
    public void setTva(Double tva) {
        this.tva = tva;
    }
    public void setTva(String tva) throws Exception {
        if(tva.trim().equals("") || tva == null) {
            throw new Exception("Aucune valeur de tva saisie");
        }
        Double tvaParsed = Double.valueOf(tva);
        if(tvaParsed < 0) {
            throw new Exception("La valeur du tva doit etre posiive");
        }
        this.setTva(tvaParsed);
    }

    public Unity getUnity() {
        return unity;
    }
    public void setUnity(Unity unity) {
        this.unity = unity;
    }
    public void setUnity(String unity) throws Exception {
        if(unity.trim().equals("") || unity == null) {
            throw new Exception("Aucune valeur de unity saisie");
        }
        Integer idUnity = Integer.valueOf(unity);
        Unity unityFind = GenericDAO.findById(Unity.class, idUnity, null);
        this.setUnity(unityFind);
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    
///Constructors

    public Article() {
    }

    public Article(int idArticle, String code, String description, String designation, Category category, Double tva, Unity unity, int status) {
        this.idArticle = idArticle;
        this.code = code;
        this.description = description;
        this.designation = designation;
        this.category = category;
        this.tva = tva;
        this.unity = unity;
        this.status = status;
    }

    public Article(String code, String description, String designation, Category category, Double tva, Unity unity, int status) {
        this.code = code;
        this.description = description;
        this.designation = designation;
        this.category = category;
        this.tva = tva;
        this.unity = unity;
        this.status = status;
    }
    
    public Article(String code, String description, String designation, String category, String tva, String unity) throws Exception {
        try {
            this.setCode(code);
            this.setDescription(description);
            this.setDesignation(designation);
            this.setCategory(category);
            this.setTva(tva);
            this.setUnity(unity);
            this.setStatus(1);
        } catch(Exception e) {
            throw e;
        }
    }
///Fonctions
    
}
