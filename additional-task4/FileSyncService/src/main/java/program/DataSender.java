package program;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class DataSender implements Runnable {
    private final Socket socket;
    private volatile boolean shouldSendData;
    private volatile String dataToSend;
    private BufferedWriter writer;


    public DataSender(Socket socket) throws IOException {
        this.dataToSend = null;
        this.socket = socket;
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public synchronized void run() {



        try {

            while (true) {
                while (!shouldSendData) {
                    wait();
                }

                if (dataToSend != null) {
                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);
                    writer.println(dataToSend);

                    shouldSendData = false;
                    dataToSend = null;
                    System.out.println("Data was sent!");
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void sendData(String data) throws IOException {
        this.dataToSend = data;
        shouldSendData = true;
        notify();
    }
}
