package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        GUI gui = new GUI();
        CommandManager commandManager = new CommandManager();

        Path currentWorkingDir = Paths.get(System.getProperty("user.dir"));

        String path = "/home/alex/os-labs/additional-task2";

        String wakeupPath = path + "/wakeup";
        String pidPath = path + "/pid";

        long pid = ProcessHandle.current().pid();
        inputData(pidPath, pid + "");

        System.out.println(wakeupPath);

        while(true) {
            try (BufferedReader br = new BufferedReader(new FileReader(wakeupPath))) {
                String line = br.readLine();

                if (line != null) {
                    if (line.equals("1")) {
                        gui.showGUI(commandManager , path);

                        inputData(wakeupPath, "0\n");
                    }
                }
            } catch (IOException exception) {
            }
        }

    }

    static void inputData(String filePath, String value) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(value + "\n");
        } catch (IOException exception) {

        }
    }
}