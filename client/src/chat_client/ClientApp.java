package chat_client;

import UI.controller.Controller;
import UI.view.View;

import java.awt.*;
import java.io.IOException;

public class ClientApp {
    public void run() {

    }
    public static void main(String[] args) throws IOException {
        View view = new View();
        Client client = new Client();
        client.getClientHandler().addObserver(view.getChatView());
        client.runClient();
        Controller controller = new Controller(client, view);
    }
}

