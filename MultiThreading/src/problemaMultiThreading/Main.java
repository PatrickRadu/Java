package problemaMultiThreading;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            MultiThreadThing thread = new MultiThreadThing(i);
            thread.start();
            thread.isAlive();
        }
    }
}