package program.TCP;

import org.json.JSONObject;
import program.managers.JsonManager;
import program.params.JsonParams;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.util.UUID;

public class DataSender implements Runnable {
    private Socket socket;
    private volatile boolean shouldSendData;
    private volatile JSONObject dataToSend;

    private final Path rootPath;
    private final UUID clientUuid;

    public DataSender(Path path, UUID clientUuid) {
        this.rootPath = path;
        this.clientUuid = clientUuid;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
        this.dataToSend = null;
    }

    @Override
    public synchronized void run() {
        try {
            while (true) {
                while (!shouldSendData) {
                    wait();
                }

                if (dataToSend != null) {
                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);
                    writer.println(dataToSend);

                    System.out.println("Sent data: " + dataToSend);
                    System.out.println("");

                    shouldSendData = false;
                    dataToSend = null;
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void sendData(JsonParams jsonParams) {
        jsonParams.setUuid(clientUuid);
        jsonParams.setRootPath(String.valueOf(rootPath));

        JsonManager jsonManager = new JsonManager();
        this.dataToSend = jsonManager.createJsonObject(jsonParams);
        shouldSendData = true;
        notify();
    }
}