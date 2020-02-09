
package server;
class User{
    private String username;
    private String email;
    private String password;
    private int score;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }
    
}
public class Server {
    
    public User logIn(String username,String password){
        //return==>Method from Data Base Class
    }
    public int signUp(User user){
        
    }
    public User getUserData(){
    }
    

    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
