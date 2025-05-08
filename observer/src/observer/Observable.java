package observer;

import java.io.IOException;

public interface Observable {
    public void addObserver(Observer observer);
    public void removeObserver();
    public void notifyObserver(String data, Object object) throws IOException;
}
