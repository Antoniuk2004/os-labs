package program.managers;

import program.commands.findCommand.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ArgumentManager {
    public String getFilePath(String args[]) {
        if (args.length == 2) {
            return args[1];
        } else if (args.length == 1) {
            System.out.println("Specify the path of the file!");
            System.exit(0);
        } else {
            System.out.println("Too many arguments!");
            System.exit(0);
        }
        return null;
    }

    public Map<String, ParamsSetter> getParams(String[] args) {
        Map<String, ParamsSetter> mapOfParams = Map.of(
                "--dir", new FileDirectory(),
                "--ext", new FileExtension(),
                "--name-contains", new NamePart()
        );

        int numOfSpecifiedParams = 0;
        for (int i = 0; i < args.length; i++) {
            for (String key : mapOfParams.keySet()) {
                if (Objects.equals(key, args[i])) {
                    ParamsSetter paramsSetter = mapOfParams.get(key);
                    setParamIfPossible(i, paramsSetter, args);
                    numOfSpecifiedParams++;
                }
            }
        }

        if ((numOfSpecifiedParams * 2 + 1) != args.length) {
            System.out.println("Something's wrong!");
            System.exit(0);
        }

        return mapOfParams;
    }

    private void setParamIfPossible(int i, ParamsSetter paramsSetter, String[] args) {
        if (i + 1 < args.length) {
            paramsSetter.setParam(args[i + 1]);
        } else {
            System.out.println("Specify the parameter!");
            System.exit(0);
        }
    }
}
