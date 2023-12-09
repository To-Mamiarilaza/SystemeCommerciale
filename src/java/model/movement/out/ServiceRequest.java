/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.movement.out;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.util.List;
import model.base.Service;
import model.purchase.ArticleQuantity;

/**
 *
 * @author to
 */
public class ServiceRequest {
    /// Field
    @DBField(name = "id_service", isForeignKey = true)
    Service service;
    List<ArticleQuantity> articleQuantities;
    
    /// Getter and setter

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<ArticleQuantity> getArticleQuantities() {
        return articleQuantities;
    }

    public void setArticleQuantities(List<ArticleQuantity> articleQuantities) {
        this.articleQuantities = articleQuantities;
    }
    
    /// Constructor
    public ServiceRequest() {
    }

    public ServiceRequest(Service service) {
        this.service = service;
    }

    public ServiceRequest(Service service, List<ArticleQuantity> articleQuantities) {
        this.service = service;
        this.articleQuantities = articleQuantities;
    }
    
}
