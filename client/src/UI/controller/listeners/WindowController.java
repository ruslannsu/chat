package UI.controller.listeners;

import UI.controller.Controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowController extends WindowAdapter {
    Controller controller;
    public WindowController(Controller controller) {
        super();
        this.controller = controller;
    }
    @Override
    public void windowClosing(WindowEvent e) {
        e.getWindow().setVisible(false);
        controller.execute("close", null);
    }

}
