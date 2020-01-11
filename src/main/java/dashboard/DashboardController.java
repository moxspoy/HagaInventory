package dashboard;

import auth.LoginController;
import database.product.ProductDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import model.User;
import model.Validation;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DashboardController implements Initializable {

    private ProductDatabase productDatabase = new ProductDatabase();
    private List<Integer> productCodeList = new ArrayList<>();
    private List<Integer> userIdList = new ArrayList<>();

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

    /* 3. OUT PRODUCT / SALES */
    @FXML
    ChoiceBox outChoiceProduct;
    @FXML
    TextField outProductPrice;
    @FXML
    TextField outProductNumber;
    @FXML
    DatePicker outProductTime;
    @FXML
    Button outProductButton;

    /* 4. USER MANAGEMENT */
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private ChoiceBox levelChoiceBox;

    @FXML
    private ChoiceBox changedChoiceUser;
    @FXML
    private TextField changedUserNama;
    @FXML
    private TextField changedUserUsername;
    @FXML
    private TextField changedUserPassword;
    @FXML
    private ChoiceBox levelChangedChoiceBox;
    @FXML
    private Button changeUserButton;
    @FXML
    private Button deleteUserButton;

    @FXML
    private TableView<User> tableUser;
    @FXML
    TableColumn<User, Integer> idUser;
    @FXML
    TableColumn<User, String> nama;
    @FXML
    TableColumn<User, String> userName;
    @FXML
    TableColumn<User, String> password;
    @FXML
    TableColumn<User, String> level;

    private ObservableList<User> userList;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        initializeComponent();
    }

    private void initializeComponent() {
        initializeDefaultValue();
        initializeProductTable();
        initializeUserTable();

        try {
            initializeChangedChoiceBox();
            initializeOutChoiceBox();
            initializeUserChoiceBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeDefaultValue() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        outProductTime.setValue(now);
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
            tableProduct.setItems(getProductList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeUserTable() {
        idUser.setCellValueFactory(new PropertyValueFactory<>("id"));
        nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        level.setCellValueFactory(new PropertyValueFactory<>("level"));

        try {
            tableUser.setItems(getUserList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<Product> getProductList() throws SQLException {
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

    private ObservableList<User> getUserList() throws SQLException {
        ResultSet allUser = productDatabase.getAllUser();

        userList = FXCollections.observableArrayList();

        while(allUser.next()){
            User userFromDatabase = new User(
                    allUser.getInt(1),
                    allUser.getString(2),
                    allUser.getString(3),
                    allUser.getString(4),
                    allUser.getString(5)
            );
            userList.add(userFromDatabase);
        }

        return userList;
    }

    private void initializeChangedChoiceBox() throws SQLException {
        changedChoiceProduct.setItems(getProductNames());
        changedChoiceProduct.setValue(getProductNames().get(0));
    }

    private void initializeOutChoiceBox() throws SQLException {
        outChoiceProduct.setItems(getProductNames());
        outChoiceProduct.setValue(getProductNames().get(0));
    }

    private void initializeUserChoiceBox() throws SQLException {
        changedChoiceUser.setItems(getUserNames());
        changedChoiceUser.setValue(getUserNames().get(0));
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

    private ObservableList<String> getUserNames() throws SQLException {
        ObservableList<String> items = FXCollections.observableArrayList();
        ResultSet userNames = productDatabase.getUserNames();

        while(userNames.next()){
            int userId = userNames.getInt(1);
            String nama = userNames.getString(2);
            userIdList.add(userId);
            items.add(nama);
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
        if(Validation.isValidInputProduct(product)) {
            productDatabase.addProduct(product);
            initializeComponent();
        }
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
        if(Validation.isValidInputProduct(product)) {
            productDatabase.editProduct(product, selectedCode);
            initializeComponent();
        }
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


    /* 3. SALES REPORT */
    @FXML
    public void outProduct(ActionEvent event) throws SQLException, IOException, ParseException {
        int selectedIndex = outChoiceProduct.getSelectionModel().getSelectedIndex();
        int productId = productCodeList.get(selectedIndex);
        int price = Integer.parseInt(outProductPrice.getText());
        int amount = Integer.parseInt(outProductNumber.getText());
        String dateFromInput = outProductTime.getValue().toString();

        if (Validation.isValidInputOutProduct(productId, price, amount, dateFromInput)) {
            String dateTime = dateFromInput  + " 00:00:01";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(dateTime);
            long time = date.getTime() / 1000L;

            productDatabase.addOutProduct(productId, price, amount, time);
            productDatabase.decrementStock(productId, amount);
            initializeComponent();
        }
    }

    /* 4. USER MANAGEMENT */
    @FXML
    public void addUser(ActionEvent event)throws SQLException, IOException, ParseException {
        String password = LoginController.encryptPassword(passwordTextField.getText());
        User user = new User(
                1,
                nameTextField.getText(),
                userNameTextField.getText(),
                password,
                levelChoiceBox.getValue().toString()
        );

        if(Validation.isValidInputUser(user)) {
            productDatabase.addUser(user);
            initializeComponent();
        }
    }

    @FXML
    public void changeUser(ActionEvent event)throws SQLException, IOException, ParseException {
        int selectedCode = getSelectedUserId();
        String nama = changedUserNama.getText();
        String userName = changedUserUsername.getText();
        String password = LoginController.encryptPassword(changedUserPassword.getText());
        String level = levelChangedChoiceBox.getValue().toString();
        User user = new User(1, nama, userName, password, level);

        if(Validation.isValidInputUser(user)) {
            productDatabase.editUser(user, selectedCode);
            initializeComponent();
        }
    }

    private int getSelectedUserId() {
        int selectedIndex = changedChoiceUser.getSelectionModel().getSelectedIndex();
        int selectedId = userIdList.get(selectedIndex);
        return selectedId;
    }

    @FXML
    public void deleteUser(ActionEvent event)throws SQLException, IOException, ParseException {
        int selectedUserId = getSelectedUserId();
        productDatabase.deleteUser(selectedUserId);
        initializeComponent();
    }

}