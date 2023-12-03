package program.commands.findCommand;

public class FileDirectory implements ParamsSetter {
    private String fileDirectory;

    @Override
    public void setParam(String value) {
        this.fileDirectory = value;
    }

    @Override
    public String getParam() {
        return fileDirectory;
    }
}
