package UI.controller.listeners;

import UI.controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionController implements ActionListener {
    Controller controller;
    JTextField ascociatedField;
    public ActionController(Controller controller) {
        this.controller = controller;
    }
    public void setAscociatedField(JTextField ascociatedField) {
        this.ascociatedField = ascociatedField;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "registration":
                controller.execute("registration", ascociatedField.getText());
                break;
        }
    }
}
