public class Cont {
    private double sold;
    private boolean inOperare;

    public Cont(double sold) {
        this.sold = sold;
        this.inOperare = false;
    }

/** Sa se implementeze clasa Cont (double sold, boolean inOperare) pentru a oferi operatiuni de:
 * - depunere(long suma, String mesaj)
 * - retragere(long suma, String mesaj)
 * - getSold()
 */
    public synchronized double depunere(long suma, String mesaj) {
        while(inOperare) {
            System.out.println(mesaj + " Contul este in operare. Asteptam ...");
            try {
                wait();  // asteptare indefinita
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }
        inOperare = true;
        this.sold += suma;
        System.out.println(mesaj + " depus cu succes "  + suma + ", sold " + this.sold);
        inOperare = false;
        notifyAll();

        return this.sold;
    }

    public synchronized double retragere(long suma, String mesaj) {
        double rezultat;

        while (inOperare) {
            System.out.println(mesaj + " Contul este in operare. Asteptam ...");
            try {
                wait();  // asteptare indefinita
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }
        inOperare = true;
        if (this.sold - suma >= 0) {
            this.sold -= suma;
            rezultat = this.sold;
            System.out.println(mesaj + " retras cu succes " + suma + ", sold " + this.sold);
        } else {
            rezultat = -1;
            System.out.println(mesaj + " are fonduri insuficiente pentru a retrage suma " + suma);
        }

        inOperare = false;
        notifyAll();
        return rezultat;
    }

    public synchronized double getSold() {
        return this.sold;
    }

}
