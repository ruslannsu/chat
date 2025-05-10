package server.data_base;

public class User {
    String name;
    String token;
    public User(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }
}
