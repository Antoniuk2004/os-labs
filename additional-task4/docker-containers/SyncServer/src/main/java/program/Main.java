package program;

import program.tcp.TCPServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(System.getenv("PORT_ENV"));
        String host = System.getenv("HOST_ENV");

        TCPServer tcpServer = new TCPServer(5000, host);
        tcpServer.startServer();
    }
}