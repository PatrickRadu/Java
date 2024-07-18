package FlyWeight;

public class Optionale {
    private boolean cardio;
    private boolean stretching;
    private boolean forta;
    private boolean rezistenta;

    public Optionale(boolean cardio, boolean stretching, boolean forta, boolean rezistenta) {
        this.cardio = cardio;
        this.stretching = stretching;
        this.forta = forta;
        this.rezistenta = rezistenta;
    }
    public String toString(){
        return "Cardio: " + cardio + " Stretching: " + stretching + " Forta: " + forta + " Rezistenta: " + rezistenta;
    }
}
