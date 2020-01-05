package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    private SimpleStringProperty code;
    private SimpleStringProperty name;
    private SimpleIntegerProperty price;
    private SimpleStringProperty merk;
    private SimpleStringProperty supplier;
    private SimpleStringProperty spec;
    private SimpleIntegerProperty stock;

    public Product(String code, String name, int price, String merk, String supplier, String spec, int stock) {
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
        this.merk = new SimpleStringProperty(merk);
        this.supplier = new SimpleStringProperty(supplier);
        this.spec = new SimpleStringProperty(spec);
        this.stock = new SimpleIntegerProperty(stock);
    }

    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getPrice() {
        return price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public String getMerk() {
        return merk.get();
    }

    public SimpleStringProperty merkProperty() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk.set(merk);
    }

    public String getSupplier() {
        return supplier.get();
    }

    public SimpleStringProperty supplierProperty() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier.set(supplier);
    }

    public String getSpec() {
        return spec.get();
    }

    public SimpleStringProperty specProperty() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec.set(spec);
    }

    public int getStock() {
        return stock.get();
    }

    public SimpleIntegerProperty stockProperty() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }
}
