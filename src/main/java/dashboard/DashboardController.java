package dashboard;

import database.product.ProductDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private ProductDatabase productDatabase = new ProductDatabase();

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

    @Override
    public void initialize(URL location, ResourceBundle resources){
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
            String productName = productNames.getString(1);
            items.add(productName);
        }
        return items;
    }
}
