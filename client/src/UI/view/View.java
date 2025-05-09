package UI.view;

import UI.controller.listeners.ActionController;

public class View {
    AuthoriszationScreen authoriszationScreen;

    public View() {
        authoriszationScreen = new AuthoriszationScreen();
    }
    public void initAuthoriszationScreenListener(ActionController actionController) {
        authoriszationScreen.initController(actionController);
    }
}
