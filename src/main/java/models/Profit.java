package models;

import nosqlite.annotations.Document;
import nosqlite.annotations.Id;

// use with collections
@Document
public class Profit {

    @Id // uuid for this document
    private String id;
    private Double totalProfit;

    // default constructor
    public Profit() { }

    public Profit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    @Override
    public String toString() {
        return "Profit{" +
                "id='" + id + '\'' +
                ", totalProfit='" + totalProfit + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }
}
