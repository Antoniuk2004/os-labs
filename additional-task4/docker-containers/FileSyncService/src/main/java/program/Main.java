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
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    private static Path rootPath;
    private static String host;
    private static int port;

    public static void main(String[] args) throws IOException {
        checkEnvVars();

        UUID clientUuid = UUID.randomUUID();

        TCPClient tcpClient = new TCPClient(host, port);
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

    private static void checkEnvVars() {
        rootPath = Path.of(System.getenv("PATH_ENV"));
        host = System.getenv("HOST_ENV");
        port = Integer.parseInt(System.getenv("PORT_ENV"));

        Predicate<Object[]> allVariablesExist = objects ->
                Stream.of(objects).allMatch(Objects::nonNull);

        if (!allVariablesExist.test(new Object[]{rootPath, host, port})) {
            System.out.println("Environment variables are not detected.");
            System.exit(0);
        }
    }
}