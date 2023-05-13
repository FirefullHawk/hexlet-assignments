package exercise;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection {
    private Connection connection;
    private final int port;
    private final String ip;

    TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.connection = new Disconnected(this);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getCurrentState() {
        return connection.getCurrentState();
    }

    public void connect() {
        this.connection.connect();
    }

    public void disconnect() {
        this.connection.disconnect();
    }

    void write(String data) {
        List<String> buffer = new ArrayList<>();
        connection.write(buffer, data);
    }
}
// END
