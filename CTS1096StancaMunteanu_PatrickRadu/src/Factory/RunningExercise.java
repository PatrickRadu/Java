package Factory;

public class RunningExercise extends Exercitiu{
    private double distance;
    private int duration;
    private final int calorie=11;

    public RunningExercise(String nume) {
        super(nume);
    }

    public RunningExercise(String nume, double distance, int duration) {
        super(nume);
        this.distance = distance;
        this.duration = duration;
    }

    @Override
    public void caloriesburned() {
        System.out.println( calorie*distance/duration);
    }
}
