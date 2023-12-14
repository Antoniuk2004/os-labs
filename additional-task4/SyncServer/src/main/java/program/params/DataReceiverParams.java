package program.params;

import program.client.Client;
import program.Type;
import program.handlers.Handler;

import java.net.Socket;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DataReceiverParams {
    private Socket socket;
    private Map<Type, Handler> handlerMap;
    private List<Client> clientList;
    private Path rootDirectoryPath;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Map<Type, Handler> getHandlerMap() {
        return handlerMap;
    }

    public void setHandlerMap(Map<Type, Handler> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public Path getRootDirectoryPath() {
        return rootDirectoryPath;
    }

    public void setRootDirectoryPath(Path rootDirectoryPath) {
        this.rootDirectoryPath = rootDirectoryPath;
    }
}
