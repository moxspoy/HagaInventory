package database.product;

import database.ConnectionFactory;
import model.Product;
import model.User;

import javax.swing.*;
import java.sql.*;

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


    public ResultSet getAllUser() {
        ResultSet resultSet = null;
        try {
            stmt = con.createStatement();
            String query = "SELECT * FROM pengguna";
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

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil menambahkan data pengguna baru");
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
            JOptionPane.showMessageDialog(null, "Berhasil memperbarui data pengguna");
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

}
