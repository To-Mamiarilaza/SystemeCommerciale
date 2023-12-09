package model.stock;

import java.time.LocalDate;
import java.util.List;

public class EtatStock {
    // Field
    LocalDate startDate;
    LocalDate endDate;
    String idArticle;
    List<Stock> stockList;
    double stockTotalValue;

    // Getter and setter

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(String idArticle) {
        this.idArticle = idArticle;
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    public double getStockTotalValue() {
        return stockTotalValue;
    }

    public void setStockTotalValue(double stockTotalValue) {
        this.stockTotalValue = stockTotalValue;
    }

    // Constructor

    public EtatStock(LocalDate startDate, LocalDate endDate, String idArticle, List<Stock> stockList, double stockTotalValue) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.idArticle = idArticle;
        this.stockList = stockList;
        this.stockTotalValue = stockTotalValue;
    }

    public EtatStock(LocalDate startDate, LocalDate endDate, String idArticle) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.idArticle = idArticle;
    }

    // Class methods
    // Displaying etat stock detail
    public void showDetail() {
        System.out.println("ETAT DE STOCK");
        System.out.println(getStartDate() + "  -  " + getEndDate());
        System.out.println("ID Article : " + getIdArticle());
        System.out.println("----------");
        for (Stock stock :
                getStockList()) {
            System.out.println(stock.getArticle().getDesignation() + " " + stock.getArticle().getUnity().getName() + "  " + stock.getInitialQuantity() + " " + stock.getIncoming() + " " + stock.getOutgoing() + "  " + stock.getRemain() + "  " + stock.getUnitPrice() + "  " + stock.getAmount());
        }
    }
}
