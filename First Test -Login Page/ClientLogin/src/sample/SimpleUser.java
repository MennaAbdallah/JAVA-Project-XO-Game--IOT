
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mashael
 */
public class SimpleUser {
    protected String userName,nickName;
    protected int _id,score,status;

    public SimpleUser() {
    }

    
    public SimpleUser(String userName, String nickName, int _id, int score, int status) {
        this.userName = userName;
        this.nickName = nickName;
        this._id = _id;
        this.score = score;
        this.status = status;
    }
    
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SimpleUser{" + "userName=" + userName + ", nickName=" + nickName + ", _id=" + _id + ", score=" + score + ", status=" + status + '}';
    }

    
}
