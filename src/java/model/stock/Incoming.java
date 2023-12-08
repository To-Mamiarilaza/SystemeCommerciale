package model.stock;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.utils.GenericUtil;

import java.time.LocalDate;
import model.article.Article;

@DBTable(name = "incoming", sequenceName = "seq_incoming", prefix = "INC")
public class Incoming {
    // Field
    @DBField(name = "id_incoming", isPrimaryKey = true)
    String idIncoming;

    @DBField(name = "date")
    LocalDate date;
    
    
    @DBField(name = "id_bde")
    int idBDE;
    
    @DBField(name = "id_article", isForeignKey = true)
    Article article;

    @DBField(name = "quantity")
    double quantity;

    @DBField(name = "unit_price")
    double unitPrice;
    
    @DBField(name = "current_unit_price")
    double currentUnitPrice;    // For CUMP

    @DBField(name = "etat")
    int etat;

    // Getter and setter


    public String getIdIncoming() {
        return idIncoming;
    }

    public void setIdIncoming(String idIncoming) {
        this.idIncoming = idIncoming;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public void setDate(String incomingDate) throws Exception {
        if (incomingDate == null || incomingDate.trim().equals("")) {
            throw new Exception("La date d'entree ne doit pas être vide");
        }
        
        LocalDate dateValue;
       
        try {
            dateValue = LocalDate.parse(incomingDate);
        } catch (Exception e) {
            throw new Exception("Vérifier le format du date");
        }
        
        this.date = dateValue;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    
    public void setArticle(String idArticle) throws Exception  {
        if (idArticle == null || idArticle.trim().equals("")) {
            throw new Exception("L' ID de l'article ne doit pas être vide !");
        }
        
        Article article = new Article();
        article.setIdArticle(Integer.valueOf(idArticle));
        
        this.article = article;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    
    public void setQuantity(String quantity) throws Exception {
        if (quantity == null || quantity.trim().equals("")) {
            throw new Exception("La quantite ne doit pas être vide !");
        }
        double quantityValue = 0;
        try {
            quantityValue = Double.valueOf(quantity);
        } catch (Exception e) {
            throw e;
        }
        
        if (quantityValue <= 0) {
            throw new Exception("La quantite a sortir ne doit pas être vide ou null !");
        }
        
        this.quantity = quantityValue;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public void setUnitPrice(String unitPrice) throws Exception {
        if (unitPrice == null || unitPrice.trim().equals("")) {
            throw new Exception("Le prix unitaire ne doit pas être vide !");
        }
        
        double unitPriceValue;
        try {
            unitPriceValue = Double.valueOf(unitPrice);
        } catch (Exception e) {
            throw new Exception("Le prix unitaire doit être un nombre !");
        }
        
        if (unitPriceValue <= 0) {
            throw new Exception("La valeur du quantite ne doit pas être négative");
        }
        
        this.unitPrice = unitPriceValue;
    }

    public int getIdBDE() {
        return idBDE;
    }

    public void setIdBDE(int idBDE) {
        this.idBDE = idBDE;
    }
    
    public void setIdBDE(String idBDE) throws Exception {
        if (idBDE == null || idBDE.equals("")) {
            throw new Exception("L' ID du bon d'entree ne doit pas être vide !");
        }
        
        this.idBDE = Integer.valueOf(idBDE);
    }

    public double getCurrentUnitPrice() {
        return currentUnitPrice;
    }

    public void setCurrentUnitPrice(double currentUnitPrice) {
        this.currentUnitPrice = currentUnitPrice;
    }

    public void setCurrentUnitPrice(String currentUnitPrice) throws Exception {
        if (currentUnitPrice == null || currentUnitPrice.equals("")) {
            throw new Exception("Le prix unitaire courant ne doit pas être null !");
        }
        
        double currentUnitPriceValue;
        try {
            currentUnitPriceValue = Double.valueOf(currentUnitPrice);
        } catch (Exception e) {
            throw new Exception("Le prix unitaire courant doit être un nombre !");
        }
        
        if (currentUnitPriceValue <= 0) {
            throw new Exception("Le prix unitaire courant ne doit pas être négative");
        }
        
        this.currentUnitPrice = currentUnitPriceValue;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    // Constructor

    public Incoming() {
    }

    public Incoming(LocalDate date, int idBDE, Article article, double quantity, double unitPrice, double currentUnitPrice, int etat) {
        this.date = date;
        this.idBDE = idBDE;
        this.article = article;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.currentUnitPrice = currentUnitPrice;
        this.etat = etat;
    }

    public Incoming(String idIncoming, LocalDate date, int idBDE, Article article, double quantity, double unitPrice, double currentUnitPrice, int etat) {
        this.idIncoming = idIncoming;
        this.date = date;
        this.idBDE = idBDE;
        this.article = article;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.currentUnitPrice = currentUnitPrice;
        this.etat = etat;
    }
    
    public Incoming(String incomingDate, String idBDE, String idArticle, String quantity, String unitPrice) throws Exception {
        setDate(incomingDate);
        setIdBDE(idBDE);
        setArticle(idArticle);
        setQuantity(quantity);
        setUnitPrice(unitPrice);
        setEtat(1);
    }
    
}
