/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.purchase;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.utils.GenericUtil;
import java.time.LocalDate;

/**
 *
 * @author To Mamiarilaza
 */
@DBTable(name = "payment_condition", sequenceName = "seq_payment_condition")
public class PaymentCondition {
    /// Field
    @DBField(name = "id_payment_condition", isPrimaryKey = true)
    int idPaymentCondition;
    
    @DBField(name = "percentage")
    double percentage;
    
    @DBField(name = "payment_date")
    LocalDate paymentDate;
    
    /// Getter and Setter

    public int getIdPaymentCondition() {
        return idPaymentCondition;
    }

    public void setIdPaymentCondition(int idPaymentCondition) {
        this.idPaymentCondition = idPaymentCondition;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    /// Constructor

    public PaymentCondition() {
    }

    public PaymentCondition(double percentage, LocalDate paymentDate) {
        this.percentage = percentage;
        this.paymentDate = paymentDate;
    }
    
    public PaymentCondition(int idPaymentCondition, double percentage, LocalDate paymentDate) {
        this.idPaymentCondition = idPaymentCondition;
        this.percentage = percentage;
        this.paymentDate = paymentDate;
    }
}
