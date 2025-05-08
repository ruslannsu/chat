package observer;

import java.io.IOException;
import java.net.Socket;

public interface Observer {
    public void update(String data, Object object) throws IOException;

}
