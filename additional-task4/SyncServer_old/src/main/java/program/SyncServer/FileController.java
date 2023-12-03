package program.SyncServer;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

public class FileController {
    private final DataBaseManager dataBaseManager;

    public FileController(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    @PostMapping(value = "/insertFile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String insertFile(
            @RequestParam(value = "path") String path,
            @RequestBody byte[] content
    ) throws SQLException {
        dataBaseManager.insertFile(path, content);
        return "Inserted";
    }

    @GetMapping("/getContentFromFile")
    public String getContent(@RequestParam(value = "path") String path) throws SQLException {
        byte[] result = dataBaseManager.getContentByPath(path);
        return new String(result);
    }

    @PutMapping("/updateFile")
    public String update(
            @RequestParam(value = "path") String path,
            @RequestParam(value = "content") byte[] content
    ) throws SQLException {
        dataBaseManager.updateContentByPath(path, content);
        return "Updated";
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(String path) throws SQLException {
        dataBaseManager.deleteFile(path);
        return "Deleted";
    }
}