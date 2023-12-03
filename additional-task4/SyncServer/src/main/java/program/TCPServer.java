package program;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private final int port;

    public TCPServer(int serverPort) {
        this.port = serverPort;
    }

    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server waiting for client on port 5000");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            new Thread(new DataReceiver(socket)).start();
            new Thread(new DataSender(socket)).start();
        }
    }
}