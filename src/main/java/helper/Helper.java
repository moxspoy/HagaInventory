package helper;

import model.User;

import java.io.*;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Properties;

public class Helper {
    public static String formatCurrency(int number) {
        Locale locale = new Locale("id", "ID");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String formatted = currencyFormatter.format(number);
        return formatted;
    }

    public static String getDateFromTime(long time) {
        LocalDate date =
                Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDate();
        return date.toString();
    }

    public static void saveCurrentUser(User user)  {
        try (OutputStream out = new FileOutputStream(constant.User.KEY_PROPERTIES)) {
            Properties properties = new Properties();
            properties.setProperty(constant.User.KEY_NAME, user.getNama());
            properties.setProperty(constant.User.KEY_USERNAME, user.getUserName());
            properties.setProperty(constant.User.KEY_LEVEL, user.getLevel());
            properties.store(out, "User Properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getCurrentUser() {
        User user = null;
        File file = new File(constant.User.KEY_PROPERTIES);
        try (InputStream in = new FileInputStream(file)) {
            Properties prop = new Properties();
            prop.load(in);
            String name = prop.getProperty(constant.User.KEY_NAME);
            String username = prop.getProperty(constant.User.KEY_USERNAME);
            String level = prop.getProperty(constant.User.KEY_LEVEL);
            user = new User(0, name, username, "", level);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}
