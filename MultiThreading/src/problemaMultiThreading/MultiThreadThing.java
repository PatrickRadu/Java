package problemaMultiThreading;

public class MultiThreadThing extends Thread{
    private int number;
    public MultiThreadThing(int number){
        this.number = number;
    }
    @Override
    public void run(){
        for(int i=1;i<=5;i++)
        {
            System.out.println(i + " Thread " + number);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
