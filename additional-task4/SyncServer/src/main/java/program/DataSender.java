package program;

import java.io.*;
import java.net.Socket;

public class DataSender implements Runnable {
    final Socket socket;

    public DataSender(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String outputLine;
            while (true) {
                System.out.print("Server: ");
                outputLine = consoleReader.readLine();
                writer.write(outputLine + "\n");
                writer.flush();
                if (outputLine.equalsIgnoreCase("bye")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
