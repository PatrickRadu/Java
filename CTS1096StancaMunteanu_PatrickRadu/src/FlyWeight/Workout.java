package FlyWeight;

public class Workout implements IWorkout{

    private String nume;
    private int cod;
    private String incalzire;

    public Workout(String nume,int cod, String incalzire) {
        this.cod = cod;
        this.incalzire = incalzire;
        this.nume = nume;
    }

    @Override
    public void doWorkout(Optionale optionale){
        System.out.println(nume + " Incalzire: " + incalzire + optionale + optionale.toString());
    }
}
