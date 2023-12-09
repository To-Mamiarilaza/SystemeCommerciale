/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.anomalie;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;

/**
 *
 * @author Fy Botas
 */
@DBTable(name = "anomalie", sequenceName = "seq_anomalie")
public class Anomalie {

    @DBField(name = "id_anomalie", isPrimaryKey = true)
    int idAnomalie;

    @DBField(name = "id_type_anomalie", isForeignKey = true)
    TypeAnomalie typeAnomalie;

    @DBField(name = "explication")
    String explication;

    @DBField(name = "id")
    int id;

    public Anomalie(int idAnomalie, TypeAnomalie typeAnomalie, String explication, int id) {
        this.idAnomalie = idAnomalie;
        this.typeAnomalie = typeAnomalie;
        this.explication = explication;
        this.id = id;
    }

    public Anomalie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAnomalie() {
        return idAnomalie;
    }

    public void setIdAnomalie(int idAnomalie) {
        this.idAnomalie = idAnomalie;
    }

    public TypeAnomalie getTypeAnomalie() {
        return typeAnomalie;
    }

    public void setTypeAnomalie(TypeAnomalie typeAnomalie) {
        this.typeAnomalie = typeAnomalie;
    }

    public String getExplication() {
        return explication;
    }

    public void setExplication(String explication) {
        this.explication = explication;
    }

}
