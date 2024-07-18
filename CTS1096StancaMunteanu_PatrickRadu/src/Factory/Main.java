package Factory;

public class Main {
    public static void main(String[] args) {
        IFactory factory = new RunningFactory("Running", 1000.0, 30);
        IFactory factory2 = new GymFactory("Gym", 10, 3);
        IFactory factory3 = new HiFactory("Hi", 20);
        Exercitiu runningExercise = factory.build();
        Exercitiu gymExercise = factory2.build();
        Exercitiu hiExercise = factory3.build();
        runningExercise.caloriesburned();
        gymExercise.caloriesburned();
        hiExercise.caloriesburned();
    }
}
