package chat_client;

import UI.view.AuthoriszationScreen;
import UI.view.View;
import chat_client.client_features.ClientReader;
import chat_client.client_features.ClientWriter;

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


    public Client() throws IOException {
        address = "localhost";
        port = 4004;
        socket = new Socket(InetAddress.getByName(null), port);
        clientReader = new ClientReader(socket);
        clientWriter = new ClientWriter(socket);
        user = new User("kto-to");
    }
    public void run() throws IOException {
        clientWriter.start();
        clientReader.start();
    }
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.run();
    }
}
