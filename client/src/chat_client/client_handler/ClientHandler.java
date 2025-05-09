package chat_client.client_handler;

import chat_client.User;
import org.xml.sax.SAXException;
import processor.XMLprocessor;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ClientHandler {
    User user;
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
    public void runHandler(String data) throws ParserConfigurationException, IOException, SAXException {
        String mainTag = xmlProcessor.getMainTag(data);
        switch (mainTag) {
            case "access":
                loginAccessHandler(data);
                break;
        }
    }
}
