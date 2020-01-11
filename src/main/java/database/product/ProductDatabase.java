package database.product;

import database.ConnectionFactory;
import model.Product;
import model.User;
import model.Validation;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDatabase {
    Connection con = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;


    public ProductDatabase() {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) {
        ResultSet rs = null;
        try {
            String productCode = null;
            String oldProductCode = null;
            String query = "SELECT * FROM barang";
            rs = stmt.executeQuery(query);

            if(!rs.next()){
                productCode="prod"+"1";
            }
            else{
                String query2 = "SELECT * FROM barang ORDER by pid DESC";
                rs = stmt.executeQuery(query2);
                if(rs.next()){
                    oldProductCode = rs.getString("kode");
                    Integer pcode = Integer.parseInt(oldProductCode.substring(4));
                    pcode++;
                    productCode="prod" + pcode;
                }
            }
            String insertQuery = "INSERT INTO barang VALUES(null,?,?,?,?,?,?,?,?)";
            pstmt = (PreparedStatement) con.prepareStatement(insertQuery);
            pstmt.setString(1, productCode);
            pstmt.setString(2, product.getName());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setString(4, product.getMerk());
            pstmt.setString(5, product.getSpec());
            pstmt.setString(6, product.getSupplier());
            pstmt.setInt(7, product.getStock());
            pstmt.setLong(8, System.currentTimeMillis() / 1000L);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil menambahkan data barang baru");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllProduct() {
        ResultSet resultSet = null;
        try {
            String query = "SELECT kode, nama, harga, merk, pemasok, spesifikasi, stok FROM barang ORDER BY pid";
            resultSet = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getProductName() {
        ResultSet resultSet = null;
        try {
            String query = "SELECT pid, nama FROM barang ORDER BY pid";
            resultSet = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void editProduct(Product product, int productId) {
        try {
            String query = "UPDATE barang SET nama=?,harga=?,merk=?,spesifikasi=?, pemasok=?, stok=?, time=? WHERE pid=?";
            pstmt = (PreparedStatement) con.prepareStatement(query);
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setString(3, product.getMerk());
            pstmt.setString(4, product.getSpec());
            pstmt.setString(5, product.getSupplier());
            pstmt.setInt(6, product.getStock());
            pstmt.setLong(7, System.currentTimeMillis() / 1000L);
            pstmt.setInt(8, productId);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil memperbarui data barang");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int value){
        try{
            String query = "delete from barang where pid=?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, value);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil menghapus barang");
        }catch(SQLException  e){
            e.printStackTrace();
        }
    }

    public void addOutProduct(int productId, int amount, int price, long time) {
        try {
            String insertQuery = "INSERT INTO barang_keluar VALUES(null,?,?,?,?)";
            pstmt = (PreparedStatement) con.prepareStatement(insertQuery);
            pstmt.setInt(1, productId);
            pstmt.setInt(2, amount);
            pstmt.setInt(3, price);
            pstmt.setLong(4, time);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil menambahkan data barang keluar");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decrementStock(int productId, int amount) {
        int stock = 0;
        try {
            String queryGet = "SELECT stok from barang WHERE pid='" + productId + "'";
            ResultSet rs = stmt.executeQuery(queryGet);
            while (rs.next()){
                stock = rs.getInt("stok");
            }

            int newStock = stock - amount;

            String query = "UPDATE barang SET stok=? WHERE pid=?";
            pstmt = (PreparedStatement) con.prepareStatement(query);
            pstmt.setInt(1, newStock);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
            System.out.println("Berhasil mengupdate stock");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getStockById(int productId) {
        String query = "SELECT stok FROM barang WHERE pid='" + productId +"'";
        ResultSet rs = null;
        int stock = 0;
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()){
                stock = rs.getInt("stok");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }

    public List<Product> getAllStock() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM barang order by pid";
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()){
                String code = rs.getString(2);
                String nama = rs.getString(3);
                int price = rs.getInt(4);
                String merk = rs.getString(5);
                String spec = rs.getString(6);
                String supplier = rs.getString(7);
                int stok = rs.getInt(8);
                long time = rs.getLong(9);

                Product product = new Product(code, nama, price, merk, spec, supplier, stok);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public ResultSet getAllUser() {
        ResultSet resultSet = null;
        try {
            stmt = con.createStatement();
            String query = "SELECT * FROM pengguna order by id";
            resultSet = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void addUser(User user) {
        try {
            String insertQuery = "INSERT INTO pengguna VALUES(null,?,?,?,?)";
            pstmt = (PreparedStatement) con.prepareStatement(insertQuery);
            pstmt.setString(1, user.getNama());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getLevel());

            if(!isUsernameExist(user.getUserName(), user.getLevel())){
                pstmt.executeUpdate();
                Validation.showDialog( "Berhasil menambahkan data pengguna baru");
            } else {
                Validation.showDialog( "Username dengan level sudah digunakan oleh akun lain");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUserNames() {
        ResultSet resultSet = null;
        try {
            String query = "SELECT id, nama FROM pengguna ORDER BY id";
            resultSet = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void editUser(User user, int userId) {
        try {
            String query = "UPDATE pengguna SET nama=?,username=?,password=?,level=? WHERE id=?";
            pstmt = (PreparedStatement) con.prepareStatement(query);
            pstmt.setString(1, user.getNama());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getLevel());
            pstmt.setInt(5, userId);
            pstmt.executeUpdate();
            Validation.showDialog( "Berhasil memmperbarui data pengguna baru");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id){
        try{
            String query = "delete from pengguna where id=?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil menghapus pengguna");
        }catch(SQLException  e){
            e.printStackTrace();
        }
    }

    public boolean isUsernameExist(String username, String level) {
        String query = "SELECT * FROM pengguna WHERE username='" + username +"' AND  level='" + level +"'";
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(query);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
