package program;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

public class Client {
    private UUID uuid;
    private String rootPath;
    private Socket socket;

    public UUID getUuid() {
        return uuid;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void sendData(JSONObject jsonObject) throws IOException {
        OutputStream output = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);
        writer.println(jsonObject);
    }
}
