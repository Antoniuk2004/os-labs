package program.handlers;

import org.json.JSONObject;
import program.Client;
import program.params.HandlerParams;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class OtherHandler implements Handler {
    @Override
    public void exec(HandlerParams handlerParams) throws IOException {
        List<Client> clientList = handlerParams.getClientList();
        JSONObject jsonObject = handlerParams.getJsonObject();

        UUID currentClientUuid = UUID.fromString(jsonObject.getString("uuid"));
        for (Client client : clientList) {
            UUID uuid = client.getUuid();

            if (!uuid.equals(currentClientUuid)) {
                String formattedObjectPath = formatPath(jsonObject);

                jsonObject.put("objectPath", formattedObjectPath);

                client.sendData(jsonObject);
                System.out.println("Sent data: " + jsonObject);
                System.out.println("");
            }
        }
    }

    private String formatPath(JSONObject jsonObject) {
        String clientRootPath = jsonObject.getString("rootPath");
        String objectPath = jsonObject.getString("objectPath");

        String pathToCreate = objectPath.replace(clientRootPath, "");
        char[] arr = pathToCreate.toCharArray();
        if ((arr[0] == '/') || (arr[0] == '\\')) {
            return pathToCreate.substring(1);
        } else return pathToCreate;
    }
}
