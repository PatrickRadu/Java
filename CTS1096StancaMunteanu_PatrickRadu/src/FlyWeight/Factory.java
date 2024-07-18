package FlyWeight;

import java.util.HashMap;
import java.util.Map;

public class Factory {
    private static Map<Integer,Workout> workoutMap=new HashMap<>();
    public static IWorkout getWorkout(int cod)
    {
        Workout workout=workoutMap.get(cod);
        if(workout==null)
        {
            workout=new Workout("Patrick",cod,"Incalzire normala");
            workoutMap.put(cod,workout);
        }
        return workout;
    }
}
