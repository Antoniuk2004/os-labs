package program;

import program.TCP.DataReceiver;
import program.TCP.DataSender;
import program.TCP.TCPClient;
import program.checkers.FileSystemChecker;
import program.handlers.FileModificationHandler;
import program.handlers.SystemObjectDeletionHandler;
import program.handlers.SystemObjectCreationHandler;
import program.handlers.Handler;
import program.params.DataReceiverParams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IOException {
        Path rootPath = checkArguments(args);

        UUID clientUuid = UUID.randomUUID();

        TCPClient tcpClient = new TCPClient("localhost", 5000);
        DataSender dataSender = new DataSender(rootPath, clientUuid);

        SharedObject sharedObject = new SharedObject();

        DataReceiverParams dataReceiverParams = initializeDataReceiverParams(rootPath, sharedObject);
        DataReceiver dataReceiver = new DataReceiver(dataReceiverParams);

        tcpClient.startClient(dataSender, dataReceiver);

        FileSystemChecker fileSystemChecker = new FileSystemChecker();
        fileSystemChecker.setRootPath(rootPath);
        fileSystemChecker.setDataSender(dataSender);
        fileSystemChecker.setDataReceiverParams(dataReceiverParams);
        fileSystemChecker.setSharedObject(sharedObject);

        new Thread(fileSystemChecker).start();
    }

    private static Map<Type, Handler> createHandlerMap() {
        return Map.of(
                Type.SYSTEM_OBJECT_CREATION, new SystemObjectCreationHandler(),
                Type.SYSTEM_OBJECT_DELETION, new SystemObjectDeletionHandler(),
                Type.FILE_MODIFICATION, new FileModificationHandler()
        );
    }

    private static DataReceiverParams initializeDataReceiverParams(Path path, SharedObject sharedObject) {
        DataReceiverParams dataReceiverParams = new DataReceiverParams();
        dataReceiverParams.setHandlerMap(createHandlerMap());
        dataReceiverParams.setFileSystemObjects(new HashMap<>());
        dataReceiverParams.setPath(path);
        dataReceiverParams.setSharedObject(sharedObject);

        return dataReceiverParams;
    }

    private static Path checkArguments(String[] args) {
        if (args.length == 0) {
            System.out.println("Specify the path to your directory.");
            System.exit(0);
        } else if (args.length > 1) {
            System.out.println("Too many arguments.");
            System.exit(0);
        }
        Path path = Path.of(args[0]);

        if (!Files.exists(path)) {
            System.out.println("Such directory does not exist.");
            System.exit(0);
        }
        return path;
    }
}