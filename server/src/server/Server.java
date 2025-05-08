package server;
import observer.Observer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server implements Observer {
    ServerSocket serverSocket;
    int serverPort;
    ArrayList<ServerThread> serverThreads;

    public Server(int serverPort) throws IOException {
        this.serverPort = serverPort;
        serverThreads = new ArrayList<>();
        serverSocket = new ServerSocket(serverPort);
    }
    public void runServer() {
        try {
            while(true) {
                Socket socket;
                socket = serverSocket.accept();
                ServerThread serverThread= new ServerThread(socket);
                serverThreads.add(serverThread);
                serverThread.addObserver(this);
                serverThread.start();
            }
        }
        catch (Exception ex) {
            throw new RuntimeException();
        }
    }
    public static void main(String[] args) throws IOException {
        Server server = new Server(4004);
        server.runServer();
    }

    @Override
    public void update(String data, Object object) throws IOException {

        for (int i = 0; i != serverThreads.size(); ++i) {
            if (!((ServerThread)object).equals(serverThreads.get(i))) {
                serverThreads.get(i).sendMessage(data);
            }
        }
    }
}
