package Factory;

public class HiFactory implements IFactory{
    private String exerciseName;
    private int duration;

    public HiFactory(String exerciseName, int duration) {
        this.exerciseName = exerciseName;
        this.duration = duration;
    }
    @Override
    public Exercitiu build() {
        return new HIExercise(exerciseName, duration);
    }
}
