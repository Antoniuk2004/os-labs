package program;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class TCPClient {
    private final String serverAddress;
    private final int serverPort;

    public TCPClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void startClient() throws IOException {
        Socket socket = new Socket(serverAddress, serverPort);
        System.out.println("Connected to server");

        DataReceiver dataReceiver = new DataReceiver(socket);
        new Thread(dataReceiver).start();

        DataSender dataSender = new DataSender(socket);
        new Thread(dataSender).start();

        dataSender.sendData("WOW");
        System.out.println("attempting to send data!");
    }
}
