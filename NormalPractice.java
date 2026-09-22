// Carter Beasley | Assignment 5 | NormalPractice.java

public class NormalPractice extends TrainingSessions 
{
    // Attributes
    protected double miles;
    protected boolean hasDoubleRun;
    protected double doubleMileage;

    // Constructor without a double run
    public NormalPractice(String startTime, String date, double miles) 
    {
        super(startTime, date);
        this.miles = miles;
        this.hasDoubleRun = false;
        this.doubleMileage = 0.0;
    }

    // Constructor with double run option
    public NormalPractice(String startTime, String date, double miles, boolean hasDoubleRun, double doubleMileage) 
    {
        super(startTime, date);
        this.miles = miles;
        this.hasDoubleRun = hasDoubleRun;
        if (hasDoubleRun) 
        {
            this.doubleMileage = doubleMileage;
        } 
        else 
        {
            this.doubleMileage = 0.0;
        }
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

    public boolean hasDoubleRun() 
    {
        return hasDoubleRun;
    }

    public void setHasDoubleRun(boolean hasDoubleRun) 
    {
        this.hasDoubleRun = hasDoubleRun;
        if (!hasDoubleRun) 
        {
            this.doubleMileage = 0.0;
        }
    }

    public double getDoubleMileage() 
    {
        return doubleMileage;
    }

    public void setDoubleMileage(double doubleMileage) 
    {
        this.doubleMileage = doubleMileage;
    }

    @Override
    public String toString() 
    {
        if (hasDoubleRun) 
        {
            return super.toString() + ", Type: Normal Practice, Miles: " + miles + ", Double Run: Yes (" + doubleMileage + " miles, Total: " + (miles + doubleMileage) + " miles)";
        } 
        else 
        {
            return super.toString() + ", Type: Normal Practice, Miles: " + miles + ", Double Run: No";
        }
    }
}
