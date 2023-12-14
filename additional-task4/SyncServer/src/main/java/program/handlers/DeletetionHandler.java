package program.handlers;

import org.json.JSONObject;
import program.client.ClientCommunicator;
import program.managers.FileManager;
import program.managers.PathManager;
import program.params.HandlerParams;

import java.io.IOException;

public class DeletetionHandler implements Handler {
    private final ClientCommunicator clientCommunicator;

    public DeletetionHandler(ClientCommunicator clientCommunicator) {
        this.clientCommunicator = clientCommunicator;
    }

    @Override
    public void exec(HandlerParams handlerParams) throws IOException {
        clientCommunicator.sendDataToOtherClients(handlerParams);

        JSONObject jsonObject = handlerParams.getJsonObject();

        PathManager pathManager = new PathManager();
        String objectPath = pathManager.formatClientPath(jsonObject);

        FileManager fileManager = handlerParams.getFileManager();
        fileManager.deleteSystemObject(objectPath);
    }
}
