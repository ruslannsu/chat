package server;

import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;
import observer.*;
public class ServerThread extends Thread implements Observable {
    Socket socket;
    BufferedReader reader;
    BufferedWriter writer;
    Observer observer;
    volatile boolean isRunning = true;
    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
    public void sendMessage(String data) throws IOException {
        System.out.println("here");
        writer.write(data);
        writer.flush();
    }

    public BufferedReader getReader() {
        return reader;
    }
    public void closeServerThread() {
        try {
            socket.close();
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
        isRunning = false;
    }
    @Override
    public void run() {
        String data;
        while (isRunning) {
            try {
                data = reader.readLine();
                System.out.println(data);
                notifyObserver(data, this);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void addObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void removeObserver() {

    }

    @Override
    public void notifyObserver(String data, Object object) throws IOException {
        observer.update(data, object);
    }
}