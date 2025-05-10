package UI.view;

import UI.controller.listeners.ActionController;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import observer.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.IOException;

public class ChatView extends JFrame implements Observer {
    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_HEIGHT = 750;
    JButton sendButton;
    JPanel messagesPanel;
    JTextField inputField;

    public ChatView() {
        super();
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
        setTitle("Chat");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        messagesPanel = new JPanel();
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
        messagesPanel.setBackground(Color.WHITE);
        /*
        for (int i = 1; i <= 20; i++) {
            JTextArea message = new JTextArea();
            message.setLineWrap(true);
            message.setWrapStyleWord(true);
            message.setEditable(false);
            message.setBackground(new Color(230, 230, 250));
            message.setBorder(new EmptyBorder(8, 8, 8, 8));
            String text = "Message #" + i + ": ";
            text += "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ";
            if (i % 3 == 0) {
                text += "Vestibulum nec odio ipsum. Suspendisse cursus malesuada facilisis. ijer8u89748ifjiosdoifjsdofu89uofuw89ruwe089ruwe09r87. jf89u89fwe89fuwefu84324823942390482390";
            }
            if (i % 5 == 0) {
                text += "Nunc sed turpis. Quisque porta sagittis purus, nec convallis lectus accumsan ut. ";
            }
            message.setText(text);
            message.setMaximumSize(new Dimension(WINDOW_WIDTH - 50, Integer.MAX_VALUE));
            message.setAlignmentX(Component.LEFT_ALIGNMENT);
            messagesPanel.add(message);
            messagesPanel.add(Box.createVerticalStrut(10));
        }

         */
        JScrollPane scrollPane = new JScrollPane(messagesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setName("send");
        inputPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        inputField = new JTextField();
        inputPanel.add(inputField, BorderLayout.CENTER);
        sendButton = new JButton("Send");
        sendButton.setActionCommand("send");
        inputPanel.add(sendButton, BorderLayout.EAST);
        contentPane.add(inputPanel, BorderLayout.SOUTH);
        setVisible(false);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatView ui = new ChatView();
            ui.setVisible(true);
        });
    }
    public void initController(ActionController actionController) {
        sendButton.addActionListener(actionController);
        inputField.setName("send");
        actionController.setAscociatedField(inputField);
    }

    @Override
    public void update(String data, Object object) {
        SwingUtilities.invokeLater(() -> {
            System.out.println("updated");
            JTextArea message = new JTextArea();
            message.setLineWrap(true);
            message.setWrapStyleWord(true);
            message.setEditable(false);
            message.setBackground(new Color(230, 230, 250));
            message.setBorder(new EmptyBorder(8, 8, 8, 8));
            message.setText(data);
            message.setMaximumSize(new Dimension(WINDOW_WIDTH - 50, Integer.MAX_VALUE));
            message.setAlignmentX(Component.LEFT_ALIGNMENT);
            messagesPanel.add(message);
            messagesPanel.add(Box.createVerticalStrut(10));
            messagesPanel.revalidate();
            messagesPanel.repaint();
        });
    }
}
