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

    private Connection c = null;
    private String dbPort;
    private String dbDriver;
    private String dbUser ;
    private String dbPass ;

    //////////// for testing only
    public static void main(String[] args) {
        try {
            DBManagment dbm = new DBManagment();
            User user = new User("nesr","nesr","nesr","duur",3,10,1);
             boolean s = dbm.checkPassword(3,"nesr");
             System.out.println(s);
        } catch (SQLException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ///////////////////////////
    
    public DBManagment() throws SQLException, ClassNotFoundException {
        try {
            //Class.forName("org.sqlite.JDBC");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //c = DriverManager.getConnection("jdbc:sqlite:USER1");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaGame", "root", "");
            System.out.println("Opened database successfully");
        } catch (InstantiationException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }

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
