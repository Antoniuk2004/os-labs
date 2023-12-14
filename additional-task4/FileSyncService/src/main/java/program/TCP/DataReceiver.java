package program.TCP;

import org.json.JSONObject;
import program.SharedObject;
import program.Type;
import program.handlers.Handler;
import program.managers.JsonManager;
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
import java.util.UUID;

public class DataReceiver implements Runnable {
    private final Path rootPath;
    private final Map<Type, Handler> handlerMap;
    private final Map<Path, byte[]> fileSystemObjects;
    private Socket socket;
    private SharedObject sharedObject;

    public DataReceiver(DataReceiverParams dataReceiverParams) {
        this.rootPath = dataReceiverParams.getRootPath();
        this.handlerMap = dataReceiverParams.getHandlerMap();
        this.fileSystemObjects = dataReceiverParams.getFileSystemObjects();
        this.sharedObject = dataReceiverParams.getSharedObject();
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = initializeReader();

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println("Received data: " + inputLine + "\n");

                sharedObject.freezeChecker(true);

                JSONObject jsonObject = new JSONObject(inputLine);

                Type type = Type.valueOf(jsonObject.getString("type"));

                HandlerParams handlerParams;
                if (type == Type.SYNCHRONIZATION) {
                    handlerParams = initializeSyncHandlerParams(jsonObject);
                } else {
                    handlerParams = initializeHandlerParams(jsonObject);
                }

                Handler handler = handlerMap.get(type);
                handler.exec(handlerParams);

                sharedObject.freezeChecker(false);
            }
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HandlerParams initializeSyncHandlerParams(JSONObject jsonObject) {
        HandlerParams handlerParams = new HandlerParams();
        handlerParams.setRootPath(rootPath);

        JsonManager jsonManager = new JsonManager();
        List<String> directories = jsonManager.jsonToList(jsonObject, "directories");

        String filesStr = String.valueOf(jsonObject.get("files"));
        Map<String, String> files = jsonManager.jsonToMap(filesStr);

        handlerParams.setDirectoriesList(directories);
        handlerParams.setFilesMap(files);

        return handlerParams;
    }

    private BufferedReader initializeReader() throws IOException {
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }

    private HandlerParams initializeHandlerParams(JSONObject jsonObject) {
        HandlerParams handlerParams = new HandlerParams();

        handlerParams.setRootPath(rootPath);

        String objectPath = jsonObject.getString("objectPath");
        handlerParams.setAdditionalPath(objectPath);

        UUID uuid = UUID.fromString(jsonObject.getString("uuid"));
        handlerParams.setUuid(uuid);

        handlerParams.setFileSystemObjects(fileSystemObjects);

        boolean isDirectory = jsonObject.getBoolean("isDirectory");
        if (isDirectory) {
            handlerParams.setIsDirectory(true);
            return handlerParams;
        }

        boolean hasContent = jsonObject.getBoolean("hasContent");
        handlerParams.setHasContent(hasContent);
        if (hasContent) {
            String content = jsonObject.getString("content");
            handlerParams.setFileContent(content);
        }

        return handlerParams;
    }
}