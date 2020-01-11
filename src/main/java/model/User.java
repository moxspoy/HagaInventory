package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
    private SimpleIntegerProperty id;
    private SimpleStringProperty nama;
    private SimpleStringProperty userName;
    private SimpleStringProperty password;
    private SimpleStringProperty level;

    public User(int id, String nama, String userName, String password, String level) {
        this.id = new SimpleIntegerProperty(id);
        this.nama = new SimpleStringProperty(nama);
        this.userName = new SimpleStringProperty(userName);
        this.password = new SimpleStringProperty(password);
        this.level = new SimpleStringProperty(level);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNama() {
        return nama.get();
    }

    public SimpleStringProperty namaProperty() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getLevel() {
        return level.get();
    }

    public SimpleStringProperty levelProperty() {
        return level;
    }

    public void setLevel(String level) {
        this.level.set(level);
    }
}
