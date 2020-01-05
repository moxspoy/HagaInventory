package dashboard;

import database.product.ProductDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DashboardController implements Initializable {

    private ProductDatabase productDatabase = new ProductDatabase();
    private List<Integer> productCodeList = new ArrayList<>();

    /* 1. VIEW BARANG */
    @FXML
    private TableView<Product> tableProduct;
    @FXML
    TableColumn<Product, String> code;
    @FXML
    TableColumn<Product, String> name;
    @FXML
    TableColumn<Product, Integer> price;
    @FXML
    TableColumn<Product, String> merk;
    @FXML
    TableColumn<Product, String> supplier;
    @FXML
    TableColumn<Product, String> spec;
    @FXML
    TableColumn<Product, Integer> stock;

    private ObservableList<Product> list;


    /* 2. INSERT AND UPDATE BARANG */
    @FXML
    TextField newProductName;
    @FXML
    TextField newProductPrice;
    @FXML
    TextField newProductMerk;
    @FXML
    TextField newProductSpec;
    @FXML
    TextField newProductSupplier;
    @FXML
    TextField newProductNumber;

    @FXML
    ChoiceBox changedChoiceProduct;
    @FXML
    TextField changedProductName;
    @FXML
    TextField changedProductPrice;
    @FXML
    TextField changedProductMerk;
    @FXML
    TextField changedProductSpec;
    @FXML
    TextField changedProductSupplier;
    @FXML
    TextField changedProductNumber;
    @FXML
    Button changeProductButton;
    @FXML
    Button deleteProductButton;
    @FXML
    Button refreshButton;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        initializeComponent();
    }

    private void initializeComponent() {
        initializeProductTable();

        try {
            initializeChangedChoiceBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeProductTable() {
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        merk.setCellValueFactory(new PropertyValueFactory<>("merk"));
        supplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        spec.setCellValueFactory(new PropertyValueFactory<>("spec"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        try {
            tableProduct.setItems(getUserList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<Product> getUserList() throws SQLException {
        ResultSet allProduct = productDatabase.getAllProduct();

        list = FXCollections.observableArrayList();

        while(allProduct.next()){
            Product productFromDatabase = new Product(
                    allProduct.getString(1),
                    allProduct.getString(2),
                    allProduct.getInt(3),
                    allProduct.getString(4),
                    allProduct.getString(5),
                    allProduct.getString(6),
                    allProduct.getInt(7)
                    );
            list.add(productFromDatabase);
        }

        return list;
    }

    private void initializeChangedChoiceBox() throws SQLException {
        changedChoiceProduct.setItems(getProductNames());
        changedChoiceProduct.setValue(getProductNames().get(0));
    }

    private ObservableList<String> getProductNames() throws SQLException {
        ObservableList<String> items = FXCollections.observableArrayList();
        ResultSet productNames = productDatabase.getProductName();

        while(productNames.next()){
            int productId = productNames.getInt(1);
            String productName = productNames.getString(2);
            productCodeList.add(productId);
            items.add(productName);
        }
        return items;
    }

    @FXML
    public void addNewProduct(ActionEvent event) throws SQLException, IOException {
        String name = newProductName.getText();
        int price = Integer.parseInt(newProductPrice.getText());
        String merk = newProductMerk.getText();
        String spec = newProductSpec.getText();
        String supplier = newProductSupplier.getText();
        int number = Integer.parseInt(newProductNumber.getText());

        Product product = new Product("code", name, price, merk, spec, supplier, number);
        productDatabase.addProduct(product);
        initializeComponent();
    }

    private int getSelectedProductId() {
        int selectedIndex = changedChoiceProduct.getSelectionModel().getSelectedIndex();
        int selectedCode = productCodeList.get(selectedIndex);
        return selectedCode;
    }

    @FXML
    public void changeProduct(ActionEvent event) throws SQLException, IOException {
        int selectedCode = getSelectedProductId();

        String name = changedProductName.getText();
        int price = Integer.parseInt(changedProductPrice.getText());
        String merk = changedProductMerk.getText();
        String spec = changedProductSpec.getText();
        String supplier = changedProductSupplier.getText();
        int number = Integer.parseInt(changedProductNumber.getText());

        Product product = new Product("code", name, price, merk, spec, supplier, number);
        productDatabase.editProduct(product, selectedCode);
        initializeComponent();
    }

    @FXML
    public void deleteProduct(ActionEvent event) throws SQLException, IOException {
        int selectedCode = getSelectedProductId();
        productDatabase.deleteProduct(selectedCode);
        initializeComponent();
    }

    @FXML
    public void refreshData(ActionEvent event) throws SQLException, IOException {
        initializeComponent();
    }
}
