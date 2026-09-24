// Carter Beasley | Assignment 5 | LongRun.java

public class LongRun extends TrainingSessions 
{
    // Attributes
    protected double miles;

    // Constructor
    public LongRun(String startTime, String date, double miles) 
    {
        super(startTime, date);
        this.miles = miles;
    }

    // Getters and Setters
    public double getMiles() 
    {
        return miles;
    }

    public void setMiles(double miles) 
    {
        this.miles = miles;
    }

    @Override
    public double getTotalMiles() 
    {
        return miles;
    }

    // Overload 1: pace from total minutes
    public double getPace(int totalMinutes) 
    {
        if (miles == 0) 
        {
            return 0.0;
        }
        return totalMinutes / miles;   // minutes per mile
    }

    // Overload 2: pace from hours and minutes
    public double getPace(int hours, int minutes) 
    {
        return getPace(hours * 60 + minutes);   // reuses overload 1
    }

    @Override
    public String toString() 
    {
        return "Long Run - " + super.toString() + ", Miles: " + miles;
    }
}

