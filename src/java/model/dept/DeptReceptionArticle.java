/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dept;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import model.article.Article;

/**
 *
 * @author Fy Botas
 */
@DBTable(name = "dept_reception_article", sequenceName = "seq_dept_reception_article")
public class DeptReceptionArticle {

    @DBField(name = "id_dept_reception_article", isPrimaryKey = true)
    int dept_reception_article;
    
    @DBField(name = "id_dept_reception", isForeignKey = true)
    DeptReception deptReception;
    
    @DBField(name = "id_article", isForeignKey = true)
    Article article;
    
    @DBField(name = "quantity")
    double quantity;

    public DeptReceptionArticle(int dept_reception_article, DeptReception deptReception, Article article, double quantity) {
        this.dept_reception_article = dept_reception_article;
        this.deptReception = deptReception;
        this.article = article;
        this.quantity = quantity;
    }

    public DeptReceptionArticle() {
    }

    public int getDept_reception_article() {
        return dept_reception_article;
    }

    public void setDept_reception_article(int dept_reception_article) {
        this.dept_reception_article = dept_reception_article;
    }

    public DeptReception getDeptReception() {
        return deptReception;
    }

    public void setDeptReception(DeptReception deptReception) {
        this.deptReception = deptReception;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

}
