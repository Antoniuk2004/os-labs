package program;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class DataReceiver implements Runnable {
    private final Socket socket;

    public DataReceiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String inputLine = reader.readLine();
                if (inputLine != null) {
                    System.out.println("Client: " + inputLine);
                }
            }
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
