package chat_client.client_features;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.Buffer;

public class ClientReader extends Thread {
    Socket socket;
    BufferedReader bufferedReader;
    public ClientReader(Socket socket) throws IOException {
        this.socket = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        super.run();
        while (isAlive()) {
            try {
                String data = bufferedReader.readLine();
                System.out.println(data);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
