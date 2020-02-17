/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import java.io.Serializable;

/**
 *
 * @author mashael
 */
public class User extends SimpleUser implements Serializable{
    
    private String password,email;

    public User() {
    }

    public User(String userName, String nickName, String password, String email, int _id, int score, int status) {
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this._id = _id;
        this.score = score;
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    


    @Override
    public String toString() {
        return "User{" + "userName=" + userName + ", nickName=" + nickName + ", password=" + password + ", email=" + email + ", _id=" + _id + ", score=" + score + ", status=" + status + '}';
    }
    
    
    
}
