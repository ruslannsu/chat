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
                String input = reader.readLine();
                if (input.equals("/reg")) {
                    String xmlFile = new String(Files.readAllBytes(Paths.get("client/src/chat_client/xml_client_messages/login.xml")), StandardCharsets.UTF_8);
                    writer.write(xmlFile);
                    writer.flush();
                }
                else {
                    writer.write(input + '\n');
                    writer.flush();
                }

            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
