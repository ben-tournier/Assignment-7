// Carter Beasley | Assignment 5 | Workout.java

public class Workout extends TrainingSessions 
{
    // Attributes
    protected String workout;

    // Constructor
    public Workout(String startTime, String date, String workout) 
    {
        super(startTime, date);
        this.workout = workout;
    }

    // Getters and Setters
    public String getWorkout() 
    {
        return workout;
    }

    public void setWorkout(String workout) 
    {
        this.workout = workout;
    }

    @Override
    public double getTotalMiles() 
    {
        return 0.0;   // no mileage tracked for workouts
    }

    @Override
    public String toString() 
    {
        return "Workout - " + super.toString() + ", Workout: " + workout;
    }
}

