public class Main {
    public static void main (String[] args) {
        Cont cont = new Cont(Double.parseDouble(args[0]));
        int nrOperatori = Integer.parseInt(args[1]);
        OperatorBancar[] operatori = new OperatorBancar[nrOperatori];
        for (int i = 0; i < nrOperatori; i++) {
            operatori[i] = new OperatorBancar(cont, "Operator" + i, 1000 * i, 100);
            System.out.println("Operator" + i + " a fost creat.");
            operatori[i].start();
            System.out.println("Operator" + i + " a fost pornit.");
            System.out.println("Soldul contului este: " + cont.getSold());

        }
        for (int i = 0; i < nrOperatori; i++) {
            try {
                operatori[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
