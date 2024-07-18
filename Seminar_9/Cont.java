
/** Sa se implementeze clasa Cont (double sold, boolean inOperare) pentru a oferi operatiuni de:
 * - depunere(long suma, String mesaj)
 * - retragere(long suma, String mesaj)
 * - getSold()
 *
 * Se se defineasca clasa OperatorBancar, clasa derivata din clasa Thread care sa permita
 * operatiuni pe un obiect de timp cont. Clasa OperatorBancar are urmatoarele atribute:
 *     private Cont cont;
 *     private String nume;
 *     private int timpPregatire;
 *     private long suma;
 *
 * Sa se scrie un program care utilizeaza mai multi operatori bancari ce realizeza operatii
 * in regim concurential pe o resursa comuna reprezentata de un Cont bancar.
 * Programul primeste soldul contului si numarul de operatori ca parametri in linia de comanda.
 *
 */



public class Cont {
    private double sold;
    private boolean inOperare;

    public Cont(double sold, boolean inOperare) {
        this.sold = sold;
        this.inOperare = inOperare;
    }

    public synchronized void depunere(long suma, String mesaj) {
        while (inOperare) {
            try {
                wait();
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }
        inOperare = true;
        sold += suma;
        System.out.println(mesaj);
        inOperare = false;
        notifyAll();
    }

    public synchronized void retragere(long suma, String mesaj) {
        while (inOperare) {
            try {
                wait();
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }
        inOperare = true;
        sold -= suma;
        System.out.println(mesaj);
        inOperare = false;
        notifyAll();
    }

    public synchronized double getSold() {
        return sold;
    }
}
