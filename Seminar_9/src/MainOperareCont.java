public class MainOperareCont {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Numar incorect de parametri! \nUtilizare <sold> <nr. operatori>");
            System.exit(-1);
        }

        Cont cont = new Cont(Double.parseDouble(args[0]));
        int nrOperatori = Integer.parseInt(args[1]);
        Thread[] operatori = new OperatorBancar[nrOperatori];

        for (int i=0; i<nrOperatori; i++) {
            operatori[i] = new OperatorBancar(cont, "Operator " + (i+1),
                    i*10+50, i*100+50);
            operatori[i].start();
        }

        for (int i=0; i<nrOperatori; i++) {
            try {
                operatori[i].join();
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }

        System.out.println("Soldul final: " + cont.getSold());
    }
}
