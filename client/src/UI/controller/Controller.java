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
    }
    public void execute(String input, String textData) {
        switch (input) {
            case "registration":
                System.out.println("!!!!!!!!--0");
                client.registrationPerfromed();
                client.setUserName(textData);;
                break;
        }
    }

}
