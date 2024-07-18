package FlyWeight;

public class ProgMain {
    public static void main(String[] args)
    {
        Optionale optionale = new Optionale(true, true, true, true);
        Optionale optionale2 = new Optionale(false, false, false, true);
        Factory factory = new Factory();
        Workout workout = (Workout) factory.getWorkout(1);
        workout.doWorkout(optionale);
        factory.getWorkout(2).doWorkout(optionale2);
        factory.getWorkout(3).doWorkout(optionale);
        factory.getWorkout(4).doWorkout(optionale2);

    }
}
