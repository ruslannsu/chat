package UI.view;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

import UI.controller.listeners.ActionController;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class AuthoriszationScreen extends JFrame {
    private JTextField usernameField;
    private JButton joinButton;

    public AuthoriszationScreen() {
        FlatLightLaf.setup();
        UIManager.put("Button.arc", 20);
        UIManager.put("Component.arc", 15);
        UIManager.put("ProgressBar.arc", 10);
        UIManager.put("Component.focusWidth", 2);
        UIManager.put("Component.focusColor", Color.WHITE);
        UIManager.put("defaultFont", new FontUIResource("Segoe UI", Font.PLAIN, 14));
        UIManager.put("Table.showHorizontalLines", false);
        UIManager.put("Table.showVerticalLines", false);
        UIManager.put("Table.selectionBackground", new Color(255, 220, 150));
        UIManager.put("Table.selectionForeground", Color.BLACK);
        FlatLaf.updateUI();
        initUI();
        setVisible(true);
    }
    private void initUI() {
        setTitle("Chat Registration");
        setSize(400, 200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel label = new JLabel("Username:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, usernameField.getPreferredSize().height));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        joinButton = new JButton("Join");
        joinButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        joinButton.setActionCommand("registration");
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(usernameField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(joinButton);
        add(panel);
    }
    public void initController(ActionController controller) {
        controller.setAscociatedField(usernameField);
        joinButton.addActionListener(controller);
    }
}
