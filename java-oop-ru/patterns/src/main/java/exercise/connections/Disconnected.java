package exercise.connections;

import exercise.TcpConnection;

import java.util.List;

// BEGIN
public class Disconnected implements Connection {
    TcpConnection tcpConnection;

    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }
    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void connect() {
        this.tcpConnection.setConnection(new Connected(this.tcpConnection));
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Connection already disconnected");
    }

    @Override
    public void write(List<String> buffer, String data) {
        System.out.println("Error! Connection disconnected");
    }
}
// END
