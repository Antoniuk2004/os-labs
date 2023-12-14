package program.params;

import org.json.JSONObject;
import program.client.Client;
import program.managers.FileManager;

import java.net.Socket;
import java.util.List;

public class HandlerParams {
    public FileManager fileManager;
    private String[] args;
    private Socket socket;
    private List<Client> clientList;
    private JSONObject jsonObject;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }
}
