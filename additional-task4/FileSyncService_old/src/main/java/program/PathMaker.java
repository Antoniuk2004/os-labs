package program;

import java.nio.file.Path;

public class PathMaker {
    public Path makePath(String pathToFile){
        String[] splittedPathToFile = pathToFile.split("/");
        Path path = Path.of(System.getProperty("user.dir"));
        for(int i = 0; i < splittedPathToFile.length; i++){
            path = path.resolve(splittedPathToFile[i]);
        }
        return path;
    }
}
