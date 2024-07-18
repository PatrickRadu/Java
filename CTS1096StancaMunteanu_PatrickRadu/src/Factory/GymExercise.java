package Factory;

public class GymExercise extends Exercitiu {
    private int repetitions;
    private int sets;
    private final double calorie=6.5;

    public GymExercise(String nume) {
        super(nume);
    }

    public GymExercise(String nume, int repetitions, int sets) {
        super(nume);
        this.repetitions = repetitions;
        this.sets = sets;
    }
    @Override
    public void caloriesburned() {
        System.out.println(calorie*repetitions*sets);
    }
}
