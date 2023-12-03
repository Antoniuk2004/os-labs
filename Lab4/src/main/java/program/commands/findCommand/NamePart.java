package program.commands.findCommand;

public class NamePart implements ParamsSetter {
    private String namePart;

    @Override
    public void setParam(String value) {
        this.namePart = value;
    }

    @Override
    public String getParam() {
        return namePart;
    }

    public String getNamePart() {
        return namePart;
    }
}
