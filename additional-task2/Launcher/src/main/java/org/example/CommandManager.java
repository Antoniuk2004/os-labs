package org.example;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    JSONObject jsonObject;

    public void readData(String path) throws FileNotFoundException {
        FileReader fileReader = new FileReader(path);

        JSONTokener jsonTokener = new JSONTokener(fileReader);

        this.jsonObject = new JSONObject(jsonTokener);
    }
    public List<String> getCommands() {
        List<String> listOfCommands = new ArrayList<>(jsonObject.keySet());

        return listOfCommands;
    }

    public void executeCommand(String commandName) throws IOException {
        String command = jsonObject.getString(commandName);

        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.start();
    }
}


