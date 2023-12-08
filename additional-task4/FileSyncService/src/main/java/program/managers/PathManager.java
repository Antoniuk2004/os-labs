package program.managers;

import program.params.HandlerParams;

import java.nio.file.Path;

import static java.lang.String.valueOf;

public class PathManager {
    public Path makePath(HandlerParams handlerParams) {
        Path newPath = Path.of(new String(valueOf(handlerParams.getRootPath())));

        String additionalPath = handlerParams.getAdditionalPath();

        String[] pathComponents = additionalPath.split("/");

        for (int i = 0; i < pathComponents.length; i++) {
            newPath = newPath.resolve(pathComponents[i]);
        }

        return newPath;
    }
}
