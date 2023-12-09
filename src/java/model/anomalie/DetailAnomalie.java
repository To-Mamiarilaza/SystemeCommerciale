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
@DBTable(name = "detail_anomalie", sequenceName = "seq_detail_anomalie")
public class DetailAnomalie {

    @DBField(name = "id_detail_anomalie", isPrimaryKey = true)
    int idDetailAnomalie;
    
    @DBField(name = "id_detail_anomalie", isForeignKey = true)
    Anomalie anomalie;
    
    @DBField(name = "detail")
    String detail;

    public DetailAnomalie(int idDetailAnomalie, Anomalie anomalie, String detail) {
        this.idDetailAnomalie = idDetailAnomalie;
        this.anomalie = anomalie;
        this.detail = detail;
    }

    public DetailAnomalie() {
    }

    public int getIdDetailAnomalie() {
        return idDetailAnomalie;
    }

    public void setIdDetailAnomalie(int idDetailAnomalie) {
        this.idDetailAnomalie = idDetailAnomalie;
    }

    public Anomalie getAnomalie() {
        return anomalie;
    }

    public void setAnomalie(Anomalie anomalie) {
        this.anomalie = anomalie;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
