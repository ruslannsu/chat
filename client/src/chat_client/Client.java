package chat_client;

import chat_client.client_features.ClientReader;
import chat_client.client_features.ClientWriter;
import chat_client.client_handler.ClientHandler;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    User user;
    Socket socket;
    String address;
    int port;
    ClientReader clientReader;
    ClientWriter clientWriter;
    ClientHandler clientHandler;
    public Client() throws IOException {
        address = "localhost";
        port = 4004;
        socket = new Socket(InetAddress.getByName(null), port);
        user = new User("kto-to");
        clientHandler = new ClientHandler(user);
        clientReader = new ClientReader(socket, clientHandler);
        clientWriter = new ClientWriter(socket, user);
    }
    public void runClient() throws IOException {
        clientWriter.start();
        clientReader.start();
    }
    public void registrationPerfromed() {
        clientWriter.loginPerfomed();
    }
    public void sendMessagePerfromed(String messageData) {
        clientWriter.sendMessagePerfomed(messageData);
    }
    public void setUserName(String name) {
        user.setUserName(name);
    }
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.runClient();
    }

    public User getUser() {
        return user;
    }

    public ClientHandler getClientHandler() {
        return clientHandler;
    }
}
