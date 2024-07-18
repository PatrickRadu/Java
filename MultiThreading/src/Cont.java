public class Cont {
    private double sold;
    private boolean inOperare;

    public Cont(double initialSold) {
        this.sold = initialSold;
        this.inOperare = true; // assuming the account can be operated on initially
    }

    public synchronized void depunere(long suma, String mesaj) {
        if (inOperare) {
            sold += suma;
            System.out.println("Depunere: " + suma + " | " + mesaj);
        } else {
            System.out.println("Contul este inoperabil pentru depuneri.");
        }
    }

    public synchronized double retragere(long suma, String mesaj) {
        double rezultat;
       while (inOperare) {
           System.out.println(mesaj + "contul este deja in operare");
           try {
               wait();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }
           inOperare= true;
           if(this.sold-suma>=0)
           {
               this.sold-=suma;
               rezultat=this.sold;
                System.out.println(mesaj + "retras cu succes suma " + suma + " sold " + this.sold);
           }
           else
           {
               rezultat=-1;
               System.out.println(mesaj + "are fonduri insuficiente pentru a retrage suma " + suma);
           }

         inOperare=false;
            notifyAll();
            return rezultat;
    }

    public double getSold() {
        return sold;
    }
}
