package chat_client.client_handler;

import chat_client.User;
import org.xml.sax.SAXException;
import processor.XMLprocessor;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import observer.*;
public class ClientHandler implements Observable {
    User user;
    Observer observer;
    XMLprocessor xmlProcessor;
    public ClientHandler(User user) {
        this.user = user;
        xmlProcessor = new XMLprocessor();
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
    public void runHandler(String data) throws ParserConfigurationException, IOException, SAXException {
        String mainTag = xmlProcessor.getMainTag(data);
        switch (mainTag) {
            case "access":
                loginAccessHandler(data);
                break;
            case "userlogin":
                eventAccessHandler(data);
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
