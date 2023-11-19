/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.purchase;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.utils.GenericUtil;

/**
 *
 * @author To Mamiarilaza
 */
@DBTable(name = "payment_method", sequenceName = "seq_payment_method")
public class PaymentMethod {
    // Field
    @DBField(name = "id_payment_method")
    int id_payment_method;
    
    @DBField(name = "payment_method_name")
    String payment_method_name;
    
    // Getter and Setter

    public int getId_payment_method() {
        return id_payment_method;
    }

    public void setId_payment_method(int id_payment_method) {
        this.id_payment_method = id_payment_method;
    }

    public String getPayment_method_name() {
        return payment_method_name;
    }

    public void setPayment_method_name(String payment_method_name) {
        this.payment_method_name = payment_method_name;
    }
    
    // Constructor

    public PaymentMethod() {
    }

    public PaymentMethod(String payment_method_name) {
        this.payment_method_name = payment_method_name;
    }

    public PaymentMethod(int id_payment_method, String payment_method_name) {
        this.id_payment_method = id_payment_method;
        this.payment_method_name = payment_method_name;
    }
}
