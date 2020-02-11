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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mashael
 */
public class DBManagment {

    private Connection c = null;

    //////////// for testing only
    public static void main(String[] args) {
        try {
            DBManagment dbm = new DBManagment();
            User user = dbm.getUser(1);
            System.out.println(user.toString());
            user.setId(3);
            user.setUserName("mina");
            dbm.insertNewUser(user);
            user.setStatus(1);
            System.out.println(dbm.setStatus(user.getId(), 1));
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
            c = DriverManager.getConnection("jdbc:mysql://localhost:3307/XoGameDB", "root", "");
            System.out.println("Opened database successfully");
        } catch (InstantiationException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public User getUser(int id) {
        User user;
        try {
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM user where id = ?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            //if (rs.getFetchSize() != 0) {
                rs.first();
                //System.out.println(rs.toString());
                user = new User(rs.getString("username"),
                        rs.getString("nickname"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("id"),
                        rs.getInt("score"),
                        rs.getInt("status"));
                return user;

            //}
        } catch (SQLException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean insertNewUser(User newUser){
        try {
            
            PreparedStatement stmt = c.prepareStatement("INSERT INTO user(username,password,nickname,email,score) VALUES(?,?,?,?,?)", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, newUser.getUserName());
            stmt.setString(2, newUser.getPassword());
            stmt.setString(3, newUser.getNickName());
            stmt.setString(4, newUser.getEmail());
            stmt.setInt(5, newUser.getScore());

            int rs = stmt.executeUpdate();
            System.out.println(rs);
            
            return rs>0;
        } catch (SQLException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean setStatus(int id,int status){
        try {
            PreparedStatement stmt = c.prepareStatement("UPDATE user set status=? where id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, String.valueOf(status));
            stmt.setInt(2, id);
            int rs = stmt.executeUpdate();
            return rs>0;
        } catch (SQLException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
