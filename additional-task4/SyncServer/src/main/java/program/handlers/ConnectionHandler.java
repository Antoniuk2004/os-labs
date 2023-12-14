package program.handlers;

import org.json.JSONObject;
import program.Type;
import program.client.Client;
import program.managers.FileManager;
import program.params.HandlerParams;

import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

public class ConnectionHandler implements Handler {
    @Override
    public void exec(HandlerParams handlerParams) throws IOException {
        Client client = createClient(handlerParams);
        handlerParams.getClientList().add(client);

        FileManager fileManager = handlerParams.getFileManager();
        Object[] dirsAndFiles = fileManager.getFilesAndDirectories();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", Type.SYNCHRONIZATION);
        jsonObject.put("directories", dirsAndFiles[0]);
        jsonObject.put("files", dirsAndFiles[1]);

        System.out.println("Sent data: " + jsonObject);
        client.sendData(jsonObject);
    }

    private Client createClient(HandlerParams handlerParams) {
        JSONObject jsonObject = handlerParams.getJsonObject();
        Socket socket = handlerParams.getSocket();

        UUID uuid = UUID.fromString(jsonObject.getString("uuid"));
        String rootPath = jsonObject.getString("rootPath");

        Client client = new Client();
        client.setSocket(socket);
        client.setUuid(uuid);
        client.setRootPath(rootPath);
        return client;
    }
}
