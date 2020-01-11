package model;

import database.product.ProductDatabase;

import javax.swing.*;

public class Validation {

    public static final int MINIMUM_INPUT_VALUE = 1;
    public static final int PASSWORD_LENGTH = 6;
    public static final int MINIMUM_STOCK = 5;
    private static final String EMPTY_ALERT = "Tidak boleh ada field yang kosong!";

    public static boolean isValidInputProduct(Product product) {
        if(product.getName().isEmpty() || product.getPrice() < MINIMUM_INPUT_VALUE ||
                product.getMerk().isEmpty() || product.getSpec().isEmpty() ||
                product.getSupplier().isEmpty() || product.getStock() < MINIMUM_INPUT_VALUE
        ) {
            showEmptyAlertDialog();
            return false;
        }
        return true;
    }

    public static boolean isValidInputUser(User user) {
        if(user.getNama().isEmpty() || user.getUserName().isEmpty() ||
                user.getPassword().isEmpty() || user.getLevel().isEmpty()
        ) {
            showEmptyAlertDialog();
            return false;
        } else if(user.getPassword().length() < PASSWORD_LENGTH) {
            showDialog("Password minimal 6 karakter");
            return false;
        }
        return true;
    }

    public static boolean isValidInputOutProduct(int productId, int price, int amount, String date) {
        ProductDatabase productDatabase = new ProductDatabase();
        boolean isLimitedStock = productDatabase.getStock(productId) < MINIMUM_STOCK;
        if(price < MINIMUM_INPUT_VALUE || amount < MINIMUM_INPUT_VALUE || date.isEmpty()) {
            showEmptyAlertDialog();
            return false;
        } else if(isLimitedStock) {
            showDialog("Tidak dapat melakukan penjualan. Stok barang yang dipilih kurang dari 5");
            return false;
        }
        return true;
    }

    public static void showDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void showEmptyAlertDialog() {
        JOptionPane.showMessageDialog(null, EMPTY_ALERT);
    }
}
