package chat_client.client_features;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;

public class ClientWriter extends Thread {
    Socket socket;
    BufferedWriter writer;
    public ClientWriter(Socket socket) throws IOException {
        super();
        this.socket = socket;
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {
        super.run();
        while (isAlive()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String data = reader.readLine();
                if (data.equals("/reg")) {
                    String xmlFile = new String(Files.readAllBytes(Paths.get("client/src/chat_client/xml_client_messages/login.xml")), StandardCharsets.UTF_8);
                    xmlFile = xmlFile.replaceAll("[\n\r]", "");
                    xmlFile = xmlFile.replaceAll("\s+", "").trim();
                    xmlFile = xmlFile + '\n';
                    writer.write(xmlFile);
                    writer.flush();
                }
                else {
                    writer.write(data + '\n');
                    writer.flush();
                }

            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
