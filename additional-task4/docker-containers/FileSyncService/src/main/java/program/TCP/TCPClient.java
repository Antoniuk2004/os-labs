package program.TCP;

import program.TCP.DataReceiver;
import program.TCP.DataSender;
import program.Type;
import program.params.JsonParams;

import java.io.*;
import java.net.Socket;

public class TCPClient {
    private final String serverAddress;
    private final int serverPort;

    public TCPClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void startClient(DataSender dataSender, DataReceiver dataReceiver) throws IOException {
        Socket socket = new Socket(serverAddress, serverPort);
        System.out.println("Connected to server");

        dataSender.setSocket(socket);
        new Thread(dataSender).start();

        dataReceiver.setSocket(socket);
        new Thread(dataReceiver).start();

        JsonParams jsonParams = new JsonParams();
        jsonParams.setType(Type.CONNECTION);

        dataSender.sendData(jsonParams);
    }
}
