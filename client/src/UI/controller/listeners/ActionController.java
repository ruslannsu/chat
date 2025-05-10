package UI.controller.listeners;

import UI.controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ActionController implements ActionListener {
    Controller controller;
    JTextField ascociatedField;
    HashMap<String, Object> ascociatedFields;
    public ActionController(Controller controller) {
        ascociatedFields = new HashMap<>();
        this.controller = controller;
    }
    public void setAscociatedField(JTextField ascociatedField) {
        ascociatedFields.put(ascociatedField.getName(), ascociatedField);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(e.getActionCommand());
        switch (command) {
            case "registration":
                System.out.println("wow");
                controller.execute("registration", ((JTextField)(ascociatedFields.get("registration"))).getText());
                break;
            case "send":

                controller.execute("send", ((JTextField)(ascociatedFields.get("send"))).getText());
                SwingUtilities.invokeLater(() -> {
                    ((JTextField)(ascociatedFields.get("send"))).setText("");
                });
                System.out.println("cleared");
                break;
        }
    }
}
