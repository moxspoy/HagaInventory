package database.product;

import database.ConnectionFactory;

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
            String query = "SELECT nama FROM barang ORDER BY pid";
            resultSet = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
