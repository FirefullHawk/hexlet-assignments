package exercise.connections;

import exercise.TcpConnection;

import java.util.List;

// BEGIN
public class Connected implements Connection {
    TcpConnection tcpConnection;

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection already formed");
    }

    @Override
    public void disconnect() {
        this.tcpConnection.setConnection(new Disconnected(this.tcpConnection));
    }

    @Override
    public void write(List<String> buffer, String data) {
        buffer.add(data);
    }
}
// END
