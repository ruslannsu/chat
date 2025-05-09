package chat_client;

public class User {
    String userName;
    String token;
    public User(String name) {
        userName = name;
    }
    public void setToken(String token) {
        this.token = token;
        System.out.println(token);
    }
    public String getToken() {
        return token;
    }
    public void setUserName(String name) {
        this.userName = name;
        System.out.println(name);
    }
    public String getUserName() {
        return userName;
    }
}
