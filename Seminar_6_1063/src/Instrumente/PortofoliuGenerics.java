package Instrumente;

import java.util.HashMap;
import java.util.Map;

public class PortofoliuGenerics<T> //class generics
{
    private Map<String, T> portfoliu;

    public PortofoliuGenerics(Map<String, T> portfoliu) {
        this.portfoliu = portfoliu;
    }
    public PortofoliuGenerics()
    {
        this.portfoliu = new HashMap<>();
    }

    public Map<String, T> getPortfoliu() {
        return portfoliu;
    }

    public void setPortfoliu(Map<String, T> portfoliu) {
        this.portfoliu = portfoliu;
    }
    public void adaugaInstrument(String cheie, T valoare)
    {
        this.portfoliu.put(cheie, valoare);
    }
    public T getInstrument(String cheie) {
        return this.portfoliu.get(cheie);
    }


}
