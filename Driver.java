// Ben Tournier | Assignment 6 | Driver.java

public class Driver
{
    public static void main(String[] args)
    {
        LongRun longRun = new LongRun("7:00 AM", "09/20", 12.0);

        TrainingSessions[] sessions = {
            longRun,
            new NormalPractice("3:30 PM", "09/21", 6.0),
            new NormalPractice("3:30 PM", "09/22", 7.0, true, 3.0),
            new Workout("3:30 PM", "09/23", "6 x 800m")
        };

        // Overriding: each object uses its own toString() and getTotalMiles()
        double totalMiles = 0.0;
        for (TrainingSessions s : sessions)
        {
            System.out.println(s);
            totalMiles += s.getTotalMiles();
        }
        System.out.println("Total miles: " + totalMiles);

        // Overloading: same method name, different parameters
        System.out.printf("Pace (90 min): %.2f min/mile%n", longRun.getPace(90));
        System.out.printf("Pace (1 hr 30 min): %.2f min/mile%n", longRun.getPace(1, 30));
    }
}
