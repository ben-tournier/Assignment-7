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
    public String toString() 
    {
        return super.toString() + ", Type: Long Run, Miles: " + miles;
    }
}

