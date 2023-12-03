package program;

import program.params.VisitorParams;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        checkArguments(args);

        TCPClient tcpClient = new TCPClient("localhost", 5000);
        tcpClient.startClient();

        VisitorParams visitorParams = new VisitorParams();
        visitorParams.setDirectory(args[0]);
        visitorParams.setFirst(true);
        Map<Path, byte[]> fileSystemObjects = new HashMap<>();
        visitorParams.setFileSystemObjects(fileSystemObjects);
        MyVisitor myVisitor = new MyVisitor(visitorParams);
    }

    private static void checkArguments(String[] args){
        if (args.length == 0) {
            System.out.println("Specify the path to your directory.");
            System.exit(0);
        } else if (args.length > 1) {
            System.out.println("Too many arguments.");
            System.exit(0);
        }
    }
}