package program;

import program.tcp.TCPServer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Path rootDirectoryPath = checkArguments(args);

        TCPServer tcpServer = new TCPServer(5000, rootDirectoryPath);
        tcpServer.startServer();
    }

    private static Path checkArguments(String[] args) {
        if (args.length == 0) {
            System.out.println("Specify the path to your directory.");
            System.exit(0);
        } else if (args.length > 1) {
            System.out.println("Too many arguments.");
            System.exit(0);
        }
        Path path;
        if (args[0].startsWith("/home")) {
            path = Path.of(args[0]);
        }
        else{
            String userDir = System.getProperty("user.dir");
            path = Path.of(userDir + "/" + args[0]);
        }

        if (!Files.exists(path)) {
            System.out.println("Such directory does not exist.");
            System.exit(0);
        }
        return path;
    }

}