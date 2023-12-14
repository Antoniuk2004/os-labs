package program.checkers;

import program.MyVisitor;
import program.SharedObject;
import program.TCP.DataSender;
import program.params.DataReceiverParams;
import program.params.VisitorParams;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class FileSystemChecker implements Runnable {
    private Path rootPath;
    private DataSender dataSender;
    private DataReceiverParams dataReceiverParams;
    private SharedObject sharedObject;

    @Override
    public void run() {
        VisitorParams visitorParams = initializeVisitorParams(rootPath, dataSender);
        visitorParams.setFileSystemObjects(dataReceiverParams.getFileSystemObjects());
        MyVisitor myVisitor = new MyVisitor(visitorParams);

        Checker checker = new Checker();
        try {
            checker.checkIfCreated(rootPath, myVisitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        myVisitor.setFirst(false);
        while (true) {
            try {
                sharedObject.checkIfFrozen();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                checker.checkIfCreated(rootPath, myVisitor);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Map<Path, byte[]> fileSystemObjects = visitorParams.getFileSystemObjects();
            checker.checkIfDeleted(fileSystemObjects, dataSender);
        }
    }

    private static VisitorParams initializeVisitorParams(Path path, DataSender dataSender) {
        VisitorParams visitorParams = new VisitorParams();
        visitorParams.setDirectory(path);
        visitorParams.setDataSender(dataSender);
        return visitorParams;
    }

    public void setRootPath(Path rootPath) {
        this.rootPath = rootPath;
    }

    public void setDataSender(DataSender dataSender) {
        this.dataSender = dataSender;
    }

    public void setDataReceiverParams(DataReceiverParams dataReceiverParams) {
        this.dataReceiverParams = dataReceiverParams;
    }

    public void setSharedObject(SharedObject sharedObject) {
        this.sharedObject = sharedObject;
    }
}
