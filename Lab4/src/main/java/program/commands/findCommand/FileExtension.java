package program.commands.findCommand;

public class FileExtension implements ParamsSetter {
    private String fileExtension;

    @Override
    public void setParam(String value) {
        this.fileExtension = value;
    }

    @Override
    public String getParam() {
        return fileExtension;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
