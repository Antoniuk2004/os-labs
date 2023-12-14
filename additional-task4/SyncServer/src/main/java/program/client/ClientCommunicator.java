package program.client;

import org.json.JSONObject;
import program.managers.PathManager;
import program.params.HandlerParams;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class ClientCommunicator {
    public void sendDataToOtherClients(HandlerParams handlerParams) throws IOException {
        List<Client> clientList = handlerParams.getClientList();
        JSONObject jsonObject = handlerParams.getJsonObject();

        UUID currentClientUuid = UUID.fromString(jsonObject.getString("uuid"));
        for (Client client : clientList) {
            UUID uuid = client.getUuid();

            if (!uuid.equals(currentClientUuid)) {
                PathManager pathManager = new PathManager();
                String formattedObjectPath = pathManager.formatClientPath(jsonObject);

                jsonObject.put("objectPath", formattedObjectPath);

                client.sendData(jsonObject);
                System.out.println("Sent data: " + jsonObject);
                System.out.println("");
            }
        }
    }
}
