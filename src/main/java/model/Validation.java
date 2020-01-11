package model;

import javax.swing.*;

public class Validation {

    public static final int MINIMUM_INPUT_VALUE = 1;
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
        }
        return true;
    }

    public static boolean isValidInputOutProduct(int price, int amount, long time) {
        if(price < MINIMUM_INPUT_VALUE || amount < MINIMUM_INPUT_VALUE || time < MINIMUM_INPUT_VALUE) {
            showEmptyAlertDialog();
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
