package server.handler;

import server.Server;
import server.ServerThread;
import server.data_base.DataBase;
import server.data_base.User;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;




public class Handler {
    DataBase dataBase;
    ArrayList<ServerThread> serverThreads;
    public Handler(DataBase dataBase, ArrayList<ServerThread> serverThreads) {
        this.dataBase = dataBase;
        this.serverThreads = serverThreads;
    }
    boolean isNameCorrect(String name) {

        return !(name.isEmpty());
    }
    void loginHandler(String name, ServerThread serverThread) throws IOException {
        if (isNameCorrect(name)) {
            String token = UUID.randomUUID().toString();
            User user = new User(name, token);
            dataBase.addUser(token, user);
            String xmlFile = new String(Files.readAllBytes(Paths.get("server/src/server/xml_server/reg_access.xml")), StandardCharsets.UTF_8);
            serverThread.sendMessage(xmlFile);
        }

    }
    public void runHandler(String data, ServerThread serverThread) throws IOException {
        loginHandler(data, serverThread);
    }
}
