/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.statistics;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fy Botas
 */
@DBTable(name = "v_depense_mensuel", sequenceName = "")
public class DepenseMensuel {

    @DBField(name = "mois")
    int mois;
    @DBField(name = "annee")
    int annee;
    @DBField(name = "montant_mensuel")
    double montantMensuel;

    String moisString;

    double value_depense;

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public double getMontantMensuel() {
        return montantMensuel;
    }

    public void setMontantMensuel(double montantMensuel) {
        this.montantMensuel = montantMensuel;
    }

    public DepenseMensuel(int mois, int annee, double montantMensuel) {
        this.mois = mois;
        this.annee = annee;
        this.montantMensuel = montantMensuel;
    }

    public String getMoisString() {
        return moisString;
    }

    public void setMoisString(String moisString) {
        this.moisString = moisString;
    }

    public double getValue_depense() {
        return value_depense;
    }

    public void setValue_depense(double value_depense) {
        this.value_depense = value_depense;
    }

    public DepenseMensuel(int annee, String moisString, double value_depense) {
        this.annee = annee;
        this.moisString = moisString;
        this.value_depense = value_depense;
    }

    public DepenseMensuel() {
    }

    // mois en lettre
    public String getStringMonth(int mois){
        switch (mois) {
            case 1:
                return "'JAN'";
            case 2:
                return "'FEB'";
            case 3:
                return "'MAR'";
            case 4:
                return "'APR'";
            case 5:
                return "'MAY'";
            case 6:
                return "'JUN'";
            case 7:
                return "'JUL'";
            case 8:
                return "'AUG'";
            case 9:
                return "'SEP'";
            case 10:
                return "'OCT'";
            case 11:
                return "'NOV'";
            case 12:
                return "'DEC'";
            default:
                return " ";
        }
    }
    
    public double getPercentDepense(double sommeMontant, double depense){
        double value = (depense*100)/sommeMontant;
        return value;
    }
    
    public List<DepenseMensuel> getDepenseMensuel(double sommeMontant) throws Exception {
        List<DepenseMensuel> listeDepense = (List<DepenseMensuel>) GenericDAO.directQuery(DepenseMensuel.class, " select * from v_depense_mensuel ", null);
        List<DepenseMensuel> formattedDepense = new ArrayList<>();
        for(int i=0;i<listeDepense.size();i++){
            formattedDepense.add(new DepenseMensuel(listeDepense.get(i).getAnnee(), listeDepense.get(i).getStringMonth(listeDepense.get(i).getMois()), getPercentDepense(sommeMontant, listeDepense.get(i).getMontantMensuel())));
        }
        return formattedDepense;
    }
}
