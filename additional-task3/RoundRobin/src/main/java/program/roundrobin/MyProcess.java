package program.roundrobin;

public class MyProcess {
    private final int number;
    private final int burstTime;

    public MyProcess(int number, int time) {
        this.number = number;
        this.burstTime = time;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getNumber() {
        return number;
    }
}
