package Instrumente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Instrument implements Evaluabil {
    private String symbol;
    private List<Operatiune> operatiuni;
    public static final class Operatiune{
        private TipOperatiune tip;
        private LocalDate data;
        private double pret;
        private int cantitate;

        public Operatiune(TipOperatiune tip, LocalDate data, double pret, int cantitate) {
            this.tip = tip;
            this.data = data;
            this.pret = pret;
            this.cantitate = cantitate;
        }

        public TipOperatiune getTip() {
            return tip;
        }

        public void setTip(TipOperatiune tip) {
            this.tip = tip;
        }

        public LocalDate getData() {
            return data;
        }

        public void setData(LocalDate data) {
            this.data = data;
        }

        public double getPret() {
            return pret;
        }

        public void setPret(double pret) {
            this.pret = pret;
        }

        public int getCantitate() {
            return cantitate;
        }

        public void setCantitate(int cantitate) {
            this.cantitate = cantitate;
        }

        @Override
        public String toString() {
//            return "Operatiune{" +
//                    "tip=" + tip +
//                    ", data=" + data +
//                    ", pret=" + pret +
//                    ", cantitate=" + cantitate +
//                    '}';
            StringBuilder sb=new StringBuilder();
            String separator=",";
            sb.append(this.tip.getTip());   sb.append(separator);
            sb.append(this.data.getYear()); sb.append(separator);
            sb.append(this.data.getMonthValue()); sb.append(separator);
            sb.append(this.data.getDayOfMonth()); sb.append(separator);
            sb.append(this.pret); sb.append(separator);
            sb.append(this.cantitate);sb.append(separator);
            return sb.toString();
        }
    }

    public Instrument(String symbol, List<Operatiune> operatiuni) {
        this.symbol = symbol;
        this.operatiuni = operatiuni;
    }

    public Instrument() {
        this.symbol = null;
        this.operatiuni = new ArrayList<>();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Operatiune> getOperatiuni() {
        return operatiuni;
    }

    public void setOperatiuni(List<Operatiune> operatiuni) {
        this.operatiuni = operatiuni;
    }

    @Override
    public String toString() {
//        return "Instrument{" +
//                "symbol='" + symbol + '\'' +
//                ", operatiuni=" + operatiuni +
//                '}';
        StringBuilder sb=new StringBuilder();
        String separator=",";
        sb.append(this.symbol);sb.append(separator);
        sb.append(this.valoare());
        sb.append(System.lineSeparator());
        for(var op : this.operatiuni) {
            sb.append(op.toString());
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    public double valoare() {
        double valoare = 0;
        for (var operatiune : this.operatiuni)
        {
            valoare += operatiune.getCantitate() * operatiune.getPret() * operatiune.getTip().pozitie();
        }
        return valoare;
    }

    public void adaugaOperatiune(Operatiune operatiune)
    {
        this.operatiuni.add(operatiune);
    }
}
