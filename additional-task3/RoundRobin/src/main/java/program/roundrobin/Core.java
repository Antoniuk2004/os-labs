package program.roundrobin;

public class Core {
    private final int number;
    private MyProcess process;

    public Core(int number){
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setProcess(MyProcess myProcess){
        this.process = myProcess;
    }

    public MyProcess getProcess() {
        return process;
    }
}
