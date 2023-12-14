package program.handlers;

import org.json.JSONObject;
import program.client.ClientCommunicator;
import program.managers.FileManager;
import program.managers.PathManager;
import program.params.HandlerParams;

import java.io.IOException;

public class CreationHandler implements Handler {
    private final ClientCommunicator clientCommunicator;

    public CreationHandler(ClientCommunicator clientCommunicator) {
        this.clientCommunicator = clientCommunicator;
    }

    @Override
    public void exec(HandlerParams handlerParams) throws IOException {
        clientCommunicator.sendDataToOtherClients(handlerParams);

        JSONObject jsonObject = handlerParams.getJsonObject();
        boolean isDirectory = jsonObject.getBoolean("isDirectory");

        PathManager pathManager = new PathManager();
        String objectPath = pathManager.formatClientPath(jsonObject);

        FileManager fileManager = handlerParams.getFileManager();

        if (isDirectory) {
            fileManager.createDirectory(objectPath);
        } else {
            boolean hasContent = jsonObject.getBoolean("hasContent");

            String content = "";
            if (hasContent) {
                content = jsonObject.getString("content");
            }
            fileManager.createFile(objectPath, content);
        }
    }
}
