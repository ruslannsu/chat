package observer;

import java.io.IOException;

public interface Observer {
    public void update(String data, Object object) throws IOException;

}
