package program.params;

import program.CheckerTask;

import java.nio.file.Path;
import java.util.Map;

public class ProcessorParams {
    private JsonManager jsonManager;
    private Path absolutePathToDictionaries;
    private Path absolutePathToRequest;
    private Map<Path, CheckerTask> mapOfCheckerTask;

    public JsonManager getJsonManager() {
        return jsonManager;
    }

    public Path getAbsolutePathToDictionaries() {
        return absolutePathToDictionaries;
    }

    public Map<Path, CheckerTask> getMapOfCheckerTask() {
        return mapOfCheckerTask;
    }

    public void setJsonManager(JsonManager jsonManager) {
        this.jsonManager = jsonManager;
    }

    public void setAbsolutePathToDictionaries(Path absolutePathToDictionaries) {
        this.absolutePathToDictionaries = absolutePathToDictionaries;
    }

    public void setMapOfCheckerTask(Map<Path, CheckerTask> mapOfCheckerTask) {
        this.mapOfCheckerTask = mapOfCheckerTask;
    }

    public void setAbsolutePathToRequest(Path absolutePathToRequest) {
        this.absolutePathToRequest = absolutePathToRequest;
    }

    public Path getAbsolutePathToRequest() {
        return absolutePathToRequest;
    }
}
