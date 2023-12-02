/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.purchase;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.src.generalisation.utils.GenericUtil;

/**
 *
 * @author To Mamiarilaza
 */
@DBTable(name = "payment_method", sequenceName = "seq_payment_method")
public class PaymentMethod {
    // Field
    @DBField(name = "id_payment_method", isPrimaryKey = true)
    int idPaymentMethod;
    
    @DBField(name = "payment_method_name")
    String paymentMethodName;
    
    // Getter and setter

    public int getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(int idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }
    
    // Constructor

    public PaymentMethod() {
    }

    public PaymentMethod(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public PaymentMethod(int idPaymentMethod, String paymentMethodName) {
        this.idPaymentMethod = idPaymentMethod;
        this.paymentMethodName = paymentMethodName;
    }
}
