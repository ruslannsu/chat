package server.handler;

import org.xml.sax.SAXException;
import server.Server;
import server.ServerThread;
import server.data_base.DataBase;
import server.data_base.User;

import javax.xml.crypto.Data;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.UUID;
import processor.XMLprocessor;

public class Handler {
    DataBase dataBase;
    ArrayList<ServerThread> serverThreads;
    XMLprocessor xmlProcessor;
    public Handler(DataBase dataBase, ArrayList<ServerThread> serverThreads) {
        this.dataBase = dataBase;
        this.serverThreads = serverThreads;
        xmlProcessor = new XMLprocessor();
    }
    boolean isNameCorrect(String name) {

        return !(name.isEmpty());
    }
    void loginHandler(String name, ServerThread serverThread) throws IOException {
        if (isNameCorrect(name)) {
            String token = UUID.randomUUID().toString();
            User user = new User(name, token);
            dataBase.addUser(token, user);
            String xmlFile = new String(Files.readAllBytes(Paths.get("server/src/server/xml_server/answers/reg_access.xml")), StandardCharsets.UTF_8);
           // String xml2 = new String(Files.readAllBytes(Paths.get("server/src/server/xml_server/reg_deny.xml")), StandardCharsets.UTF_8);
            xmlFile = xmlProcessor.fixXML(xmlFile);
            xmlFile = xmlProcessor.replacePlaceholder(xmlFile, "$TOKEN$", token);
            try {
                serverThread.sendMessage(xmlFile);
            }
            catch (Exception e) {
                throw new IOException();
            }
            eventConnectionHandler(name, serverThread);
        }
    }
    void eventConnectionHandler(String name, ServerThread source) {
        System.out.println("BREAKPOINT");
        try {

            String xmlFile = new String(Files.readAllBytes(Paths.get("server/src/server/xml_server/events/user_connection.xml")), StandardCharsets.UTF_8);
            xmlFile = xmlProcessor.fixXML(xmlFile);
            xmlFile = xmlProcessor.replacePlaceholder(xmlFile, "USER_NAME", name);
            for (ServerThread serverThread : serverThreads) {
                if (serverThread.equals(source)) {

                }
                serverThread.sendMessage(xmlFile);
            }
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public void runHandler(String data, ServerThread serverThread)  {
        String mainTag = xmlProcessor.getMainTag(data);
        try {
            switch (mainTag) {
                case "login":
                    String name = xmlProcessor.getTagContent(data, "name");
                    System.out.println(name);
                    loginHandler(name, serverThread);
                    break;
            }
        }
        catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
