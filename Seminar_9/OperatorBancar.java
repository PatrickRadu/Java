//Se se defineasca clasa OperatorBancar, clasa derivata din clasa Thread care sa permita
// * operatiuni pe un obiect de timp cont. Clasa OperatorBancar are urmatoarele atribute:
//        *     private Cont cont;
// *     private String nume;
// *     private int timpPregatire;
// *     private long suma;
// *
//         * Sa se scrie un program care utilizeaza mai multi operatori bancari ce realizeza operatii
// * in regim concurential pe o resursa comuna reprezentata de un Cont bancar.
//        * Programul primeste soldul contului si numarul de operatori ca parametri in linia de comanda.
public class OperatorBancar extends Thread {
    private Cont cont;
    private String nume;
    private int timpPregatire;
    private long suma;

    public OperatorBancar(Cont cont, String nume, int timpPregatire, long suma) {
        this.cont = cont;
        this.nume = nume;
        this.timpPregatire = timpPregatire;
        this.suma = suma;
    }
@Override
    public void run() {
        try {
            Thread.sleep(timpPregatire);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cont.depunere(suma, nume);
    }
}
