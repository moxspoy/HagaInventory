package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Report {
    private SimpleIntegerProperty number;
    private SimpleStringProperty productName;
    private SimpleStringProperty soldDate;
    private SimpleIntegerProperty amount;
    private SimpleStringProperty revenue;

    public Report(int number, String productName, String soldDate, int amount, String revenue) {
        this.number = new SimpleIntegerProperty(number);
        this.productName = new SimpleStringProperty(productName);
        this.soldDate = new SimpleStringProperty(soldDate);
        this.amount = new SimpleIntegerProperty(amount);
        this.revenue = new SimpleStringProperty(revenue);
    }

    public int getNumber() {
        return number.get();
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public String getProductName() {
        return productName.get();
    }

    public SimpleStringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public String getSoldDate() {
        return soldDate.get();
    }

    public SimpleStringProperty soldDateProperty() {
        return soldDate;
    }

    public void setSoldDate(String soldDate) {
        this.soldDate.set(soldDate);
    }

    public int getAmount() {
        return amount.get();
    }

    public SimpleIntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public String getRevenue() {
        return revenue.get();
    }

    public SimpleStringProperty revenueProperty() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue.set(revenue);
    }
}
