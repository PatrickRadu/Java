package Factory;

public class GymFactory implements IFactory{
    private String exerciseName;
    private int repetitions;
    private int sets;

    public GymFactory(String exerciseName, int repetitions, int sets) {
        this.exerciseName = exerciseName;
        this.repetitions = repetitions;
        this.sets = sets;
    }
    @Override
    public Exercitiu build() {
        return new GymExercise(exerciseName, repetitions, sets);
    }
}
