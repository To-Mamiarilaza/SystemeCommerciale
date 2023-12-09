/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.stock;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.utils.GenericUtil;

/**
 *
 * @author to
 */
@DBTable(name = "gestion_method", sequenceName = "seq_gestion_method")
public class GestionMethod {
    // Field
    @DBField(name="id_gestion_method", isPrimaryKey = true)
    int idGestionMethod;
    @DBField(name="gestion_method_name")
    String gestionMethodName;
    
    // Getter and Setter
    public int getIdGestionMethod() {
        return idGestionMethod;
    }

    public void setIdGestionMethod(int idGestionMethod) {
        this.idGestionMethod = idGestionMethod;
    }

    public String getGestionMethodName() {
        return gestionMethodName;
    }

    public void setGestionMethodName(String gestionMethodName) {
        this.gestionMethodName = gestionMethodName;
    }
    
    
    // Constructor
    
    public GestionMethod() {
    }

    public GestionMethod(int idGestionMethod, String gestionMethodName) {
        this.idGestionMethod = idGestionMethod;
        this.gestionMethodName = gestionMethodName;
    }
}
