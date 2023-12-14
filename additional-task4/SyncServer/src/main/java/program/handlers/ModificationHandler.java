package program.handlers;

import org.json.JSONObject;
import program.client.ClientCommunicator;
import program.managers.FileManager;
import program.managers.PathManager;
import program.params.HandlerParams;

import java.io.IOException;

public class ModificationHandler implements Handler{
    private final ClientCommunicator clientCommunicator;

    public ModificationHandler(ClientCommunicator clientCommunicator){
        this.clientCommunicator = clientCommunicator;
    }

    @Override
    public void exec(HandlerParams handlerParams) throws IOException {
        clientCommunicator.sendDataToOtherClients(handlerParams);

        JSONObject jsonObject = handlerParams.getJsonObject();

        PathManager pathManager = new PathManager();
        String objectPath = pathManager.formatClientPath(jsonObject);

        String content = jsonObject.getString("content");

        FileManager fileManager = handlerParams.getFileManager();
        fileManager.modifyFile(objectPath, content);
    }
}
