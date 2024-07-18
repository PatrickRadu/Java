public class OperatorBancar extends Thread {
    private Cont cont;
    private String nume;
    private int timpPregatire; // in milliseconds
    private long suma;

    public OperatorBancar(Cont cont, String nume, int timpPregatire, long suma) {
        this.cont = cont;
        this.nume = nume;
        this.timpPregatire = timpPregatire;
        this.suma = suma;
    }

    @Override
    public void run() {
       while (cont.getSold()>=suma){
           try {
               sleep(timpPregatire);
               cont.retragere(suma,nume);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }
        System.out.println("sfarsit operator " + nume);
    }
}
