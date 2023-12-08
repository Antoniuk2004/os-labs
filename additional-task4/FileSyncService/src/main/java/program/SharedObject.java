package program;

public class SharedObject {
    private boolean shouldFreeze;

    public synchronized void freezeChecker(boolean shouldFreeze){
        this.shouldFreeze = shouldFreeze;
        notify();
    }

    public synchronized void checkIfFrozen() throws InterruptedException {
        while (shouldFreeze) {
            wait();
        }
    }
}
