package UI.controller;

import UI.controller.listeners.ActionController;
import UI.view.View;
import chat_client.Client;

import java.awt.event.ActionListener;

public class Controller {
    Client client;
    ActionController actionController;
    View view;
    public Controller(Client client, View view) {
        this.client = client;
        actionController = new ActionController(this);
        this.view = view;
        view.initAuthoriszationScreenListener(actionController);
        view.initChatScreenListener(actionController);
    }
    public void execute(String input, String textData) {
        switch (input) {
            case "registration":
                client.registrationPerfromed();
                client.setUserName(textData);
                view.closeRegistration();
                view.openChat();
                break;
        }
    }

}
