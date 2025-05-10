package chat_client.client_handler;

import chat_client.Client;
import chat_client.User;
import org.xml.sax.SAXException;
import processor.XMLprocessor;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import observer.*;
public class ClientHandler implements Observable {
    Client client;
    User user;
    Observer observer;
    XMLprocessor xmlProcessor;
    public ClientHandler(User user, Client client) {
        this.user = user;
        xmlProcessor = new XMLprocessor();
        this.client = client;
    }
    void loginAccessHandler(String data) throws ParserConfigurationException, IOException, SAXException {
        String token =  xmlProcessor.getTagContent(data, "session");
        user.setToken(token);
        System.out.println(user.getToken());
    }
    void eventAccessHandler(String data) throws IOException {
        System.out.println(xmlProcessor.getTagContentModified(data, "name"));
        observer.update("Server: user" + " " + xmlProcessor.getTagContentModified(data, "name") + " " + "connected", null);
    }
    void eventMessageHandler(String data) throws IOException {
        String name = xmlProcessor.getTagContentModified(data, "name");
        String message = xmlProcessor.getTagContentModified(data, "message");
        observer.update( name + ": " + message, null);
    }
    void logoutAccessHandler(String data) {
        client.closeClient();
        System.out.println(data);
    }
    public void runHandler(String data) throws ParserConfigurationException, IOException, SAXException {
        String mainTag = xmlProcessor.getMainTag(data);
        switch (mainTag) {
            case "access":
                if (xmlProcessor.getTagContentModified(data, "access").equals("confirmed")) {
                    System.out.println("wow");
                    logoutAccessHandler(data);
                }
                else {
                    loginAccessHandler(data);
                }
                break;
            case "userlogin":
                eventAccessHandler(data);
                break;
            case "message":
                eventMessageHandler(data);
                break;



        }
    }

    @Override
    public void addObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void removeObserver() {
        observer = null;
    }

    @Override
    public void notifyObserver(String data, Object object) throws IOException {

    }
}
