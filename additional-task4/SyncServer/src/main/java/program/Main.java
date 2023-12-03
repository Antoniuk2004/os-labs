package program;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TCPServer tcpServer = new TCPServer(5000);
        tcpServer.startServer();
    }
}