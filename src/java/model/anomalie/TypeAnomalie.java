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
@DBTable(name = "type_anomalie", sequenceName = "seq_type_anomalie")
public class TypeAnomalie {

    @DBField(name = "id_type_anomalie", isPrimaryKey = true)
    int idTypeAnomalie;
    
    @DBField(name = "type_anomalie")
    String typeAnomalie;

    public int getIdTypeAnomalie() {
        return idTypeAnomalie;
    }

    public void setIdTypeAnomalie(int idTypeAnomalie) {
        this.idTypeAnomalie = idTypeAnomalie;
    }

    public String getTypeAnomalie() {
        return typeAnomalie;
    }

    public void setTypeAnomalie(String typeAnomalie) {
        this.typeAnomalie = typeAnomalie;
    }

    public TypeAnomalie(int idTypeAnomalie, String typeAnomalie) {
        this.idTypeAnomalie = idTypeAnomalie;
        this.typeAnomalie = typeAnomalie;
    }

    public TypeAnomalie() {
    }

}
