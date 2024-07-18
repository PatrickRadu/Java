//Să se definească o clasă Factura
//(denumireClient - șir de caractere, dataEmitere - dată calendaristică,
//linii - colecție de obiecte de tip linie) și o clasă internă statică Linie
//(produs - șir de caractere, preț - număr cu zecimale, cantitate - întreg).
//
//Clasa Factura trebuie să conțină minim:
//
//constructor cu parametri
//metode de citire pentru toate datele stocate
//posibilitatea de a adăuga linii noi în factură
//Clasa Linie trebuie să fie imutabilă.


package facturi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Factura {
    private String denumireClient;
    private LocalDate dataEmitere;
    List<Linie> linii;
    public double total()
    {
        double total=0.0;
        for(var el:linii)
            total+=el.getCantitate()*el.getPret();
        return total;
    }
    public static class Linie {
        private final String produs;
        private final double pret;
        private final int cantitate;

        public Linie(String produs, double pret, int cantitate) {
            this.produs = produs;
            this.pret = pret;
            this.cantitate = cantitate;
        }

        public String getProdus() {
            return produs;
        }

        public double getPret() {
            return pret;
        }

        public int getCantitate() {
            return cantitate;
        }

        @Override
        public String toString() {
            return "Linie{" +
                    "produs='" + produs + '\'' +
                    ", pret=" + pret +
                    ", cantitate=" + cantitate +
                    '}';
        }
    }

    public Factura() {
        this.denumireClient = null;
        this.dataEmitere = LocalDate.now();
        this.linii = new ArrayList<>();
    }

    public Factura(String denumireClient, LocalDate dataEmitere, List<Linie> linii) {
        this.denumireClient = denumireClient;
        this.dataEmitere = dataEmitere;
        this.linii = linii;
    }

    public String getDenumireClient() {
        return denumireClient;
    }

    public void setDenumireClient(String denumireClient) {
        this.denumireClient = denumireClient;
    }

    public LocalDate getDatEmitere() {
        return dataEmitere;
    }

    public void setDatEmitere(LocalDate dataEmitere) {
        this.dataEmitere = dataEmitere;
    }

    public List<Linie> getLinii() {
        return linii;
    }

    public void setLinii(List<Linie> linii) {
        this.linii = linii;
    }

    public void addLinie(Linie linie) {
        this.linii.add(linie);
    }

    @Override
    public String toString() {
        return "Factura{" +
                "denumireClient='" + denumireClient + '\'' +
                ", dataEmitere=" + dataEmitere +
                ", linii=" + linii +
                '}';
    }
}