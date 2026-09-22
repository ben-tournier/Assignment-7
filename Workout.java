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
    public String toString() 
    {
        return super.toString() + ", Type: Workout, Workout: " + workout;
    }
}

