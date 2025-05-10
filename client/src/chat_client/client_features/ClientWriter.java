package chat_client.client_features;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;

import chat_client.User;
import processor.XMLprocessor;
public class ClientWriter extends Thread {
    Socket socket;
    BufferedWriter writer;
    XMLprocessor xmlProcessor;
    User user;
    String mainCommand = "";
    String mainData = "";
    public ClientWriter(Socket socket, User user) throws IOException {
        super();
        this.socket = socket;
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        xmlProcessor = new XMLprocessor();
        this.user = user;
    }

    @Override
    public void run() {
        super.run();
        while (isAlive()) {
            try {
                if (mainCommand.isEmpty()) {
                    continue;
                }
                /*
                if (mainCommand.equals("/reg")) {
                    String xmlFile = new String(Files.readAllBytes(Paths.get("client/src/chat_client/xml_client_messages/login.xml")), StandardCharsets.UTF_8);
                    xmlFile = xmlFile.replaceAll("[\n\r]", "");
                    xmlFile = xmlFile.replaceFirst("\\s", "<<<SPACE>>>");
                    xmlFile = xmlFile.replaceAll("\\s", "");
                    xmlFile = xmlFile.replace("<<<SPACE>>>", " ");
                    xmlFile = xmlFile + '\n';
                    xmlFile = xmlProcessor.replacePlaceholder(xmlFile, "USER_NAME", user.getUserName());
                    writer.write(xmlFile);
                    writer.flush();
                    waitCommand();
                }
                else {
                    writer.write(mainCommand + '\n');
                    writer.flush();
                }

                 */
                switch (mainCommand) {
                    case "/reg":
                        String xmlFile = new String(Files.readAllBytes(Paths.get("client/src/chat_client/xml_client_messages/login.xml")), StandardCharsets.UTF_8);
                        xmlFile = xmlFile.replaceAll("[\n\r]", "");
                        xmlFile = xmlFile.replaceFirst("\\s", "<<<SPACE>>>");
                        xmlFile = xmlFile.replaceAll("\\s", "");
                        xmlFile = xmlFile.replace("<<<SPACE>>>", " ");
                        xmlFile = xmlFile + '\n';
                        xmlFile = xmlProcessor.replacePlaceholder(xmlFile, "USER_NAME", user.getUserName());
                        writer.write(xmlFile);
                        writer.flush();
                        waitCommand();
                        break;
                    case "/send":
                        String sendXml = new String(Files.readAllBytes(Paths.get("client/src/chat_client/xml_client_messages/sendmessage.xml")), StandardCharsets.UTF_8);
                        sendXml = xmlProcessor.fixXML(sendXml);
                        sendXml = xmlProcessor.replacePlaceholder(sendXml, "TOKEN", user.getToken());
                        sendXml = xmlProcessor.replacePlaceholder(sendXml, "MESSAGE", mainData);

                        writer.write(sendXml);
                        writer.flush();
                        waitCommand();
                        waitData();
                        break;
                    default:
                        writer.write(mainCommand + '\n');
                        writer.flush();
                        break;
                }

            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void loginPerfomed() {
        mainCommand = "/reg";
    }
    public void waitCommand() {
        mainCommand = "";
    }
    public void sendMessagePerfomed(String data) {
        mainCommand = "/send";
        mainData = data;
    }
    public void waitData() {
        mainData = "";
    }
}
