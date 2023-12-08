package program.tcp;

import program.Client;
import program.Type;
import program.handlers.ConnectionHandler;
import program.handlers.OtherHandler;
import program.handlers.Handler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TCPServer {
    private final int port;

    public TCPServer(int serverPort) {
        this.port = serverPort;
    }

    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server waiting for client on port " + port);

        Map<Type, Handler> handlerMap = Map.of(
                Type.CONNECTION, new ConnectionHandler(),
                Type.SYSTEM_OBJECT_CREATION, new OtherHandler(),
                Type.SYSTEM_OBJECT_DELETION, new OtherHandler(),
                Type.FILE_MODIFICATION, new OtherHandler()
        );

        List<Client> clientList = new ArrayList<>();

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            System.out.println("");
            new Thread(new DataReceiver(socket, handlerMap, clientList)).start();
        }
    }
}