package model.stock;


import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;

import java.time.LocalDate;

@DBTable(name = "outgoing", sequenceName = "seq_outgoing", prefix = "OUT")
public class Outgoing {
    // Field
    @DBField(name = "id_outgoing", isPrimaryKey = true)
    String idOutgoing;

    @DBField(name = "date")
    LocalDate date;
    
    @DBField(name = "id_bds")
    int idBDS;

    @DBField(name = "id_incoming", isForeignKey = true)
    Incoming incoming;

    @DBField(name = "quantity")
    double quantity;

    @DBField(name = "unit_price")
    double unitPrice;

    @DBField(name = "etat")
    int etat;

    // Getter and setter

    public String getIdOutgoing() {
        return idOutgoing;
    }

    public void setIdOutgoing(String idOutgoing) {
        this.idOutgoing = idOutgoing;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Incoming getIncoming() {
        return incoming;
    }

    public void setIncoming(Incoming incoming) {
        this.incoming = incoming;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIdBDS() {
        return idBDS;
    }

    public void setIdBDS(int idBDS) {
        this.idBDS = idBDS;
    }
    
    // Constructor

    public Outgoing() {
    }

    public Outgoing(LocalDate date, int idBDS, Incoming incoming, double quantity, double unitPrice, int etat) {
        this.date = date;
        this.idBDS = idBDS;
        this.incoming = incoming;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.etat = etat;
    }

    public Outgoing(String idOutgoing, LocalDate date, int idBDS, Incoming incoming, double quantity, double unitPrice, int etat) {
        this.idOutgoing = idOutgoing;
        this.date = date;
        this.idBDS = idBDS;
        this.incoming = incoming;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.etat = etat;
    }
}
