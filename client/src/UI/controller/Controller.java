package UI.controller;

import UI.controller.listeners.ActionController;
import UI.controller.listeners.WindowController;
import UI.view.View;
import chat_client.Client;

import java.awt.event.ActionListener;

public class Controller {
    Client client;
    ActionController actionController;
    WindowController windowController;
    View view;
    public Controller(Client client, View view) {
        this.client = client;
        actionController = new ActionController(this);
        this.view = view;
        view.initAuthoriszationScreenListener(actionController);
        view.initChatScreenListener(actionController);
        windowController = new WindowController(this);
        view.initWindowListener(windowController);
    }
    public void execute(String input, String textData) {
        switch (input) {
            case "registration":
                client.setUserName(textData);
                client.registrationPerfromed();
                view.closeRegistration();
                view.openChat();
                break;
            case "send":
                 client.sendMessagePerfromed(textData);
                 break;
            case "close":
                client.closePerfromed();
                break;

        }
    }

}
