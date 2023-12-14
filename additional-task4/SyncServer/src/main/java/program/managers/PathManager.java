package program.managers;

import org.json.JSONObject;

import java.nio.file.Path;

public class PathManager {
    public String formatClientPath(JSONObject jsonObject) {
        String clientRootPath = jsonObject.getString("rootPath");
        String objectPath = jsonObject.getString("objectPath");

        String pathToCreate = objectPath.replace(clientRootPath, "");
        char[] arr = pathToCreate.toCharArray();
        if ((arr[0] == '/') || (arr[0] == '\\')) {
            return pathToCreate.substring(1);
        } else return pathToCreate;
    }

    public Path formatServerPath(String objectPath, Path rootDirectoryPath){
        String rootDirectoryPathStr = String.valueOf(rootDirectoryPath);

        return Path.of(rootDirectoryPathStr + "/" + objectPath);
    }
}
