package program.handlers;

import org.json.JSONObject;
import program.Client;
import program.params.HandlerParams;

import java.net.Socket;
import java.util.UUID;

public class ConnectionHandler implements Handler {
    @Override
    public void exec(HandlerParams handlerParams) {
        JSONObject jsonObject = handlerParams.getJsonObject();
        Socket socket = handlerParams.getSocket();

        UUID uuid = UUID.fromString(jsonObject.getString("uuid"));
        String rootPath = jsonObject.getString("rootPath");

        Client client = new Client();
        client.setSocket(socket);
        client.setUuid(uuid);
        client.setRootPath(rootPath);

        handlerParams.getClientList().add(client);
    }
}
