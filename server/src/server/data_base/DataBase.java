package server.data_base;

import java.util.HashMap;

public class DataBase {
    HashMap<String, User> users;
    public DataBase() {
        users = new HashMap<>();
    }
    public void addUser(String token, User user) {
        users.put(token, user);
    }
    public User getUserByToken(String token) {
        return users.get(token);
    }
}
