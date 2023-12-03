package program.SyncServer;

public class ResponseMessage {
    public String status;
    public Object message;

    public ResponseMessage(String status, Object message){
        this.status = status;
        this.message = message;
    }
}
