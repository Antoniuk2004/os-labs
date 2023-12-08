package program.tcp;

import org.json.JSONObject;
import program.Client;
import program.Type;
import program.handlers.Handler;
import program.params.HandlerParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class DataReceiver implements Runnable {
    private final Socket socket;
    private final Map<Type, Handler> handlerMap;
    private final HandlerParams handlerParams;

    public DataReceiver(Socket socket, Map<Type, Handler> handlerMap, List<Client> clientList) {
        this.socket = socket;
        this.handlerMap = handlerMap;
        this.handlerParams = new HandlerParams();
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