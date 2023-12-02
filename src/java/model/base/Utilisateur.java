/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.base;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.src.generalisation.utils.GenericUtil;

/**
 *
 * @author To Mamiarilaza
 */
@DBTable(name = "utilisateur", sequenceName = "seq_utilisateur")
public class Utilisateur {
    // Field
    @DBField(name = "id_utilisateur", isPrimaryKey = true)
    int idUtilisateur;
    
    @DBField(name = "id_service", isForeignKey = true)
    Service service;
    
    @DBField(name = "username")
    String username;
    
    @DBField(name = "password")
    String password;
    
    @DBField(name = "mail")
    String mail;
    
    @DBField(name = "admin")
    boolean isAdmin;
    
    @DBField(name = "photo")
    String photo;
    
    @DBField(name = "status")
    int status;
    
    // Service checking
    public static final int APPROVISIONNEMENT_SERVICE_ID = 4;
    public static final int FINANCE_SERVICE_ID = 5;
    
    // Getter and Setter

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    // Constructor

    public Utilisateur() {
    }

    public Utilisateur(Service service, String username, String password, String mail, boolean isAdmin, String photo, int status) {
        this.service = service;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.isAdmin = isAdmin;
        this.photo = photo;
        this.status = status;
    }

    public Utilisateur(int idUtilisateur, Service service, String username, String password, String mail, boolean isAdmin, String photo, int status) {
        this.idUtilisateur = idUtilisateur;
        this.service = service;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.isAdmin = isAdmin;
        this.photo = photo;
        this.status = status;
    }
    
    // Method
    
    // Checking user departement
    public boolean inFinanceService() {
        if (getService().getIdService() == FINANCE_SERVICE_ID) {
            return true;
        }
        return false;
    }
    
    
    public boolean inAchatService() {
        if (getService().getIdService() == APPROVISIONNEMENT_SERVICE_ID) {
            return true;
        }
        return false;
    }
    
}
