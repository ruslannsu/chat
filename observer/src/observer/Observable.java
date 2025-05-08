package observer;

import java.io.IOException;
import java.net.Socket;

public interface Observable {
    public void addObserver(Observer observer);
    public void removeObserver();
    public void notifyObserver(String data, Object object) throws IOException;
}
