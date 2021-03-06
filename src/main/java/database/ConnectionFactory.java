package database;

import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionFactory {
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private boolean flag = false;
    public User currentUser;

    private static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/sipb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";

    public ConnectionFactory() {
        try {
            Class.forName(CLASS_NAME);
            con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        try{
            Class.forName(CLASS_NAME);
            con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }

    public boolean login(String username, String password, String role){
        String query="SELECT * FROM pengguna WHERE username='" + username +
                "' AND password='" + password +
                "' AND level='" + role + "'";
        try{
            rs=stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt(1);
                String nama = rs.getString(2);
                String userName = rs.getString(3);
                String level = rs.getString(5);
                currentUser = new User(id, nama, userName, "", level);
                flag=true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
