package Factory;

public abstract class Exercitiu {
    String nume;
    Exercitiu(String nume){
        this.nume=nume;
    }
    public abstract void caloriesburned();
}
