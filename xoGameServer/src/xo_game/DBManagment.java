package xo_game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package xo_game;

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
    private static DBManagment dbm = null;//= new DBManagment();
    private static Connection c = null;
    private static String dbPort = "3306";
    private static String dbDriver = "com.mysql.jdbc.Driver";
    private static String dbUser = "root";
    private static String dbPass ="";

    private DBManagment() throws SQLException, ClassNotFoundException{ 
        try {
            Class.forName(dbDriver).newInstance();
            c = DriverManager.getConnection("jdbc:mysql://localhost:"+dbPort+"/javaGame", dbUser, dbPass);
             System.out.println("DB connected successfully");
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DBManagment getInstance() throws SQLException, ClassNotFoundException{
        if(dbm==null) {
            dbm = new DBManagment();
            System.out.println("new DB instance");            
        }else System.out.println("old DB instance");
        return dbm;
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
        throw new NullPointerException("Can't connect to db");
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
        throw new NullPointerException("Can't connect to db");
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
        throw new NullPointerException("Can't connect to db");
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
        throw new NullPointerException("Can't connect to db");
    }

    public User getUser(int id) {
        User user;
        try {
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM user where id = ?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            rs.first();
            user = new User(rs.getString("username"),
                    rs.getString("nickname"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getInt("id"),
                    rs.getInt("score"),
                    rs.getInt("status"));
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new NullPointerException("Can't connect to db");

    }

    public boolean insertNewUser(User newUser) {
        try {

            PreparedStatement stmt = c.prepareStatement("INSERT INTO user(username,password,nickname,email,score) VALUES(?,?,?,?,?)", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, newUser.getUserName());
            stmt.setString(2, newUser.getPassword());
            stmt.setString(3, newUser.getNickName());
            stmt.setString(4, newUser.getEmail());
            stmt.setInt(5, newUser.getScore());

            int rs = stmt.executeUpdate();
            System.out.println(rs);

            return rs > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new NullPointerException("Can't connect to db");
    }

    public boolean setStatus(int id, int status) {
        try {
            PreparedStatement stmt = c.prepareStatement("UPDATE user set status=? where id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, String.valueOf(status));
            stmt.setInt(2, id);
            int rs = stmt.executeUpdate();
            return rs > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new NullPointerException("Can't connect to db");
    }

    public SimpleUser getStatus(int id) {
        try {
            PreparedStatement stmt = c.prepareStatement("select id,score,status,username,nickname from user where id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.first()) {
                SimpleUser simpleUser = new SimpleUser(rs.getString("username"),
                        rs.getString("nickname"),
                        rs.getInt("id"),
                        rs.getInt("score"),
                        Integer.valueOf(rs.getString("status"))
                );
                return simpleUser;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }

        throw new NullPointerException("Can't connect to db");

    }

    public boolean setPassword(int id, String newPassword) {
        try {
            PreparedStatement stmt = c.prepareStatement("UPDATE user set password=? where id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, newPassword);
            stmt.setInt(2, id);
            int rs = stmt.executeUpdate();
            return rs > 0;

        } catch (SQLException ex) {
            Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
        }

        throw new NullPointerException("can't update user table by new password");
    }
    

    public Vector getAllUsersStatus(){
        Vector<SimpleUser> statusVector = new Vector();
        
        try {
            PreparedStatement stmt = c.prepareStatement("select id,score,status,username,nickname from user ",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                statusVector.add(new SimpleUser(rs.getString("username"),
                        rs.getString("nickname"),
                        rs.getInt("id"),
                        rs.getInt("score"),
                        Integer.valueOf(rs.getString("status"))
                ));                
            }
            return statusVector;
        } catch (SQLException ex) {
            //Logger.getLogger(DBManagment.class.getName()).log(Level.SEVERE, null, ex);
            throw new NullPointerException("Can't connect to db");
        }       
    }
}
