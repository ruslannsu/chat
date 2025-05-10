package UI.view;

import UI.controller.listeners.ActionController;
import UI.controller.listeners.WindowController;

public class View {
    AuthoriszationScreen authoriszationScreen;
    ChatView chatView;

    public View() {
        authoriszationScreen = new AuthoriszationScreen();
        chatView = new ChatView();
    }
    public void initAuthoriszationScreenListener(ActionController actionController) {
        authoriszationScreen.initController(actionController);
    }
    public void initChatScreenListener(ActionController actionController) {
        chatView.initController(actionController);
    }
    public void initWindowListener(WindowController windowController) {
        chatView.initWindowController(windowController);
    }

    public ChatView getChatView() {
        return chatView;
    }
    public void closeRegistration() {
        authoriszationScreen.setVisible(false);
    }
    public void openChat() {
        chatView.setVisible(true);
    }
}
