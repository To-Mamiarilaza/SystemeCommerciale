/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.base;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.src.generalisation.utils.GenericUtil;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import model.statistics.ServiceDepense;

/**
 *
 * @author To Mamiarilaza
 */
@DBTable(name = "service", sequenceName = "seq_service")
public class Service {

    // Field
    @DBField(name = "id_service", isPrimaryKey = true)
    int idService;

    @DBField(name = "service")
    String service;

    @DBField(name = "fonction")
    String fonction;

    @DBField(name = "creation_date")
    LocalDate creationDate;

    @DBField(name = "status")
    int status;

//Getter and setter
    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // Constructor
    public Service() {
    }

    public Service(String service, String fonction, LocalDate creationDate, int status) {
        this.service = service;
        this.fonction = fonction;
        this.creationDate = creationDate;
        this.status = status;
    }

    public Service(int idService, String service, String fonction, LocalDate creationDate, int status) {
        this.idService = idService;
        this.service = service;
        this.fonction = fonction;
        this.creationDate = creationDate;
        this.status = status;
    }

    //get the value of purchase validate by service
    public ServiceDepense getServiceDepense(double montantTotal) throws Exception {
        List<ServiceDepense> listeDepense = (List<ServiceDepense>) GenericDAO.directQuery(ServiceDepense.class, "select sum(amount) as montant from v_purchase_article_request where id_service = " + this.idService + " group by id_service", null);
        System.out.println(listeDepense.size());
        ServiceDepense sd = new ServiceDepense();
        if (listeDepense.size() > 0) {
            sd.setMontant(listeDepense.get(0).getMontant() * 100 / montantTotal);
        }
        else { 
            sd.setMontant(0);
        }
        return sd;
    }

    public static void main(String[] args) throws Exception {
        List<Service> listeService = (List<Service>) GenericDAO.directQuery(Service.class, "select * from service where status = 1", null);
        for (int i = 0; i < listeService.size(); i++) {
            ServiceDepense sp = listeService.get(i).getServiceDepense(1.00);
            System.out.println(sp.getMontant());
        }
        Arrays.toString(listeService.toArray());
    }
}
