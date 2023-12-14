package program.tcp;

import program.client.ClientCommunicator;
import program.Type;
import program.handlers.*;
import program.params.DataReceiverParams;

import java.io.*;
import java.net.ServerSocket;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

public class TCPServer {
    private final int port;
    private final Path rootDirectoryPath;
    private final ClientCommunicator clientCommunicator;

    public TCPServer(int serverPort, Path rootDirectoryPath) {
        this.port = serverPort;
        this.rootDirectoryPath = rootDirectoryPath;
        this.clientCommunicator = new ClientCommunicator();
    }

    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server waiting for client on port " + port);

        while (true) {
            DataReceiverParams dataReceiverParams = initializeDataReceiverParams(serverSocket);

            System.out.println("Client connected\n");

            new Thread(new DataReceiver(dataReceiverParams)).start();
        }
    }

    private DataReceiverParams initializeDataReceiverParams(ServerSocket serverSocket) throws IOException {
        DataReceiverParams dataReceiverParams = new DataReceiverParams();
        dataReceiverParams.setSocket(serverSocket.accept());
        dataReceiverParams.setHandlerMap(initializeHandlerMap());
        dataReceiverParams.setClientList(new ArrayList<>());
        dataReceiverParams.setRootDirectoryPath(rootDirectoryPath);
        return dataReceiverParams;
    }

    private Map<Type, Handler> initializeHandlerMap(){
        return Map.of(
                Type.CONNECTION, new ConnectionHandler(),
                Type.SYSTEM_OBJECT_CREATION, new CreationHandler(clientCommunicator),
                Type.SYSTEM_OBJECT_DELETION, new DeletetionHandler(clientCommunicator),
                Type.FILE_MODIFICATION, new ModificationHandler(clientCommunicator)
        );
    }
}