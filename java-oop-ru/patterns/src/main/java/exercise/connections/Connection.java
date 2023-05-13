package exercise.connections;

import java.util.List;

public interface Connection {
    // BEGIN
    String getCurrentState();

    void connect();

    void disconnect();

    void write(List<String> buffer, String data);
    // END
}
