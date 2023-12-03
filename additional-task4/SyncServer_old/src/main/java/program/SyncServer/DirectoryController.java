package program.SyncServer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class DirectoryController {
    private final DataBaseManager dataBaseManager;

    public DirectoryController(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    @PostMapping(value = "/insertDirectory")
    public ResponseEntity<ResponseMessage> insertDirectory(@RequestParam String path) throws SQLException {
        System.out.println("wow");
        dataBaseManager.insertDirectory(path);
        String status = "Error";
        String message = "Inserted";
        ResponseMessage responseMessage = new ResponseMessage(status, message);
        return ResponseEntity.ok(responseMessage);
    }

    @DeleteMapping("/deleteDirectory")
    public ResponseEntity<String> deleteDirectory(@RequestParam String path) throws SQLException {
        dataBaseManager.deleteDirectory(path);
        return ResponseEntity.ok("Deleted");
    }

    @RequestMapping("/{path:[^\\.]*}")
    public ResponseEntity<ResponseMessage> handleUnknownPaths() {
        String status = "Error";
        String message = "Endpoint does not exist.";
        return ResponseEntity.status(404).body(new ResponseMessage(status, message));
    }
}