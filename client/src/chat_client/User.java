package chat_client;

public class User {
    String userName;
    String token;
    public User(String name) {
        userName = name;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
