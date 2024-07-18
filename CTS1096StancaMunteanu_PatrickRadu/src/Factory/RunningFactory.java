package Factory;

public class RunningFactory implements IFactory{
    private String exerciseName;
    private double distance;
    private int duration;

    public RunningFactory(String exerciseName, double distance, int duration) {
        this.exerciseName = exerciseName;
        this.distance = distance;
        this.duration = duration;
    }
    @Override
    public Exercitiu build() {
        return new RunningExercise(exerciseName, distance, duration);
    }
}
