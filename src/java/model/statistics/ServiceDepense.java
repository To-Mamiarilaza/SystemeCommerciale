/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.statistics;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;

/**
 *
 * @author Fy Botas
 */
@DBTable(name = "v_depense_service", sequenceName = "")
public class ServiceDepense {

    @DBField(name = "montant")
    double montant;

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
    
    public ServiceDepense(double montant) {
        this.montant = montant;
    }

    public ServiceDepense() {
    }
}
