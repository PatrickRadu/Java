package Factory;

public class HIExercise extends Exercitiu{
    private static final int calories=15;
    private int duration;

    public HIExercise(String nume) {
        super(nume);
    }

    public HIExercise(String nume, int duration) {
        super(nume);
        this.duration = duration;
    }

    @Override
    public void caloriesburned() {
        System.out.println(calories*this.duration);
    }
}
