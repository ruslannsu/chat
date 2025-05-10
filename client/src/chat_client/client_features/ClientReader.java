package chat_client.client_features;

import chat_client.client_handler.ClientHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.Buffer;

public class ClientReader extends Thread {
    Socket socket;
    BufferedReader bufferedReader;
    ClientHandler clientHandler;
    volatile boolean isRunning = true;
    public ClientReader(Socket socket, ClientHandler clientHandler) throws IOException {
        this.socket = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.clientHandler = clientHandler;
    }
    public void closeReader() {
        System.out.println("close reader");
        isRunning = false;
    }

    @Override
    public void run() {
        super.run();
        while (isRunning) {
            try {
                String data;
                data = bufferedReader.readLine();
                clientHandler.runHandler(data);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
