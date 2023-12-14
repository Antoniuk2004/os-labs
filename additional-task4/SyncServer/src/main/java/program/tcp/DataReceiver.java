package program.tcp;

import org.json.JSONObject;
import program.client.Client;
import program.Type;
import program.handlers.Handler;
import program.managers.FileManager;
import program.params.DataReceiverParams;
import program.params.HandlerParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DataReceiver implements Runnable {
    private final Socket socket;
    private final Map<Type, Handler> handlerMap;
    private final HandlerParams handlerParams;

    public DataReceiver(DataReceiverParams dataReceiverParams) {
        this.socket = dataReceiverParams.getSocket();
        this.handlerMap = dataReceiverParams.getHandlerMap();
        List<Client> clientList = dataReceiverParams.getClientList();
        Path rootDirectoryPath = dataReceiverParams.getRootDirectoryPath();

        this.handlerParams = new HandlerParams();
        handlerParams.setFileManager(new FileManager(rootDirectoryPath));
        handlerParams.setClientList(clientList);
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            while (true) {
                handleReceivedData(reader);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleReceivedData(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null) return;

        System.out.println("Received Data: " + line);
        System.out.println("");

        JSONObject jsonObject = new JSONObject(line);

        handlerParams.setSocket(socket);
        handlerParams.setJsonObject(jsonObject);

        Type type = Type.valueOf(jsonObject.getString("type"));

        Handler handler = handlerMap.get(type);
        handler.exec(handlerParams);
    }
}