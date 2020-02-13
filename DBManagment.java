/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo_game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mashael
 */
public class DBManagment {

    private static Connection c = null;
    private static String dbPort = "3306";
    private static String dbDriver = "com.mysql.jdbc.Driver";
    private static String dbUser = "root";
    private static String dbPass ="";

    //////////// for testing only
    public static void main(String[] args) {
        try {
            DBManagment dbm = new DBManagment();
            DBManagment.connect();
            User user = new User("nesr","nesr","nesr","duur",3,10,1);
             boolean s = dbm.checkPassword(3,"neesr");
             System.out.println(s);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ///////////////////////////
    public DBManagment(){
    }

    public static boolean connect() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(dbDriver).newInstance();
            c = DriverManager.getConnection("jdbc:mysql://localhost:"+dbPort+"/javaGame", dbUser, dbPass);
            return true;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void setPort(String dbPort) {
        DBManagment.dbPort = dbPort;
    }

    public static void setDriver(String dbDriver) {
        DBManagment.dbDriver = dbDriver;
    }

    public static void setDBUser(String dbUser) {
        DBManagment.dbUser = dbUser;
    }

    public static void setDBPassword(String dbPass) {
        DBManagment.dbPass = dbPass;
    }

    public static String getPort() {
        return dbPort;
    }

    public static String getDriver() {
        return dbDriver;
    }

    public static String getDBUser() {
        return dbUser;
    }

    public static String getDBPass() {
        return dbPass;
    }
    

    public int getScore(int id){
        try {
            PreparedStatement stmt = c.prepareStatement("SELECT score FROM user where id = ?;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, id);            
            ResultSet rs = stmt.executeQuery();
            rs.first();
            return rs.getInt("score");
        } catch (SQLException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public boolean setScore(int id, int newScore){
        
        try {
            PreparedStatement stmt = c.prepareStatement("UPDATE user SET score = ? where id = ?;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, newScore);
            stmt.setInt(2, id);            
            int rs = stmt.executeUpdate();
            return rs > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int logIn(User user){
        try {
            PreparedStatement stmt = c.prepareStatement("SELECT id FROM user where email = ? and password = ?;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());            
            ResultSet rs = stmt.executeQuery();
            rs.first();
            return rs.getInt("id");       
        } catch (SQLException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public boolean checkPassword(int id, String pass){
      try {
            PreparedStatement stmt = c.prepareStatement("SELECT password FROM user where id = ? ;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);            
            stmt.setInt(1, id);            
            ResultSet rs = stmt.executeQuery();
            rs.first();
            return rs.getString("password").equals(pass);      
        } catch (SQLException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
