package Instrumente;

import java.util.List;

public class Actiune extends Instrument implements Evaluabil {
    private double procentDivident; //3.5% inseamna 3.5/100

    public Actiune(String symbol, List<Operatiune> operatiuni, double procentDivident) {
        super(symbol, operatiuni);
        this.procentDivident = procentDivident;
    }

    public Actiune() {
        super();
        this.procentDivident = 0.0;
    }

    public double getProcentDivident() {
        return procentDivident;
    }

    public void setProcentDivident(double procentDivident) {
        this.procentDivident = procentDivident;
    }

    @Override
    public String toString() {
//        return "Actiune{" + super.toString() +
//                "procentDivident=" + procentDivident +
//                "} " ;
        StringBuilder sb=new StringBuilder();
        String separator=",";
        sb.append(this.getSymbol());sb.append(separator);
        sb.append(this.valoare());sb.append(separator);
        sb.append(this.procentDivident);
        sb.append(System.lineSeparator());
        for(var op : this.getOperatiuni()) {
            sb.append(op.toString());
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
