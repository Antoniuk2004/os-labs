package program.SyncServer;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RootDirectoryController {
    private final DataBaseManager dataBaseManager;

    public RootDirectoryController(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    @GetMapping("/getRootDirectories")
    public Map<String, Object> getRootDirectories() throws SQLException {
        List<String> list = dataBaseManager.getListOfRootDirectories();
        return Map.of(
                "rootDirectories", list,
                "Status", "OK"
        );
    }

    @DeleteMapping("/deleteRootDirectory")
    public Map<String, Object> deleteRootDirectory(
            @RequestParam(value="path") String path
    ) throws SQLException {
        dataBaseManager.deleteRootDirectory(path);
        return Map.of(
                "Message", "Inserted",
                "Status", "OK"
        );
    }

    @PostMapping("/addRootDirectory")
    public Map<String, Object> addDirectory(
            @RequestParam(value="path") String path
    ) throws SQLException{
        dataBaseManager.addRootDirectory(path);
        return Map.of(
                "Message", "Added",
                "Status", "OK"
        );
    }
}
