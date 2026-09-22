// Carter Beasley | Assignment 6 | Driver.java

public class Driver 
{
    public static void main(String[] args) 
    {
        // Create sample objects for each type of training session
        NormalPractice mondayEasy = new NormalPractice("6:30 AM", "09/21/2026", 6.0);
        Workout tuesdayIntervals = new Workout("7:00 AM", "09/22/2026", "6x1000m Repeats @ 3:15 pace");
        NormalPractice wednesdayDouble = new NormalPractice("6:30 AM", "09/23/2026", 7.0, true, 3.5);
        Workout thursdayTempo = new Workout("7:00 AM", "09/24/2026", "4 Mile Tempo Run");
        NormalPractice fridayRecovery = new NormalPractice("6:30 AM", "09/25/2026", 5.0);
        LongRun saturdayLongRun = new LongRun("8:00 AM", "09/26/2026", 14.0);

        // Polymorphic array storing all training session objects
        TrainingSessions[] sessions = {
            mondayEasy,
            tuesdayIntervals,
            wednesdayDouble,
            thursdayTempo,
            fridayRecovery,
            saturdayLongRun
        };

        System.out.println("==================================================");
        System.out.println("           WEEKLY TRAINING SESSIONS LOG           ");
        System.out.println("==================================================");

        // Variables used to process information about the objects
        double totalMileage = 0.0;
        int normalPracticeCount = 0;
        int workoutCount = 0;
        int longRunCount = 0;
        int doubleRunCount = 0;

        // Loop that processes and displays information about the objects
        for (int i = 0; i < sessions.length; i++) 
        {
            TrainingSessions session = sessions[i];

            // 1. Display information about the current object
            System.out.println("Session #" + (i + 1) + ": " + session);

            // 2. Process information about the current object
            if (session instanceof NormalPractice) 
            {
                NormalPractice practice = (NormalPractice) session;
                totalMileage += practice.getMiles();
                normalPracticeCount++;

                if (practice.hasDoubleRun()) 
                {
                    totalMileage += practice.getDoubleMileage();
                    doubleRunCount++;
                }
            } 
            else if (session instanceof Workout) 
            {
                workoutCount++;
            } 
            else if (session instanceof LongRun) 
            {
                LongRun longRun = (LongRun) session;
                totalMileage += longRun.getMiles();
                longRunCount++;
            }
        }

        // Display summary of processed information
        System.out.println("\n==================================================");
        System.out.println("            PROCESSED TRAINING SUMMARY            ");
        System.out.println("==================================================");
        System.out.println("Total Sessions Completed : " + sessions.length);
        System.out.println("Normal Practices         : " + normalPracticeCount);
        System.out.println("  - Double Run Days      : " + doubleRunCount);
        System.out.println("Workouts                 : " + workoutCount);
        System.out.println("Long Runs                : " + longRunCount);
        System.out.printf("Total Mileage Logged     : %.1f miles\n", totalMileage);
        System.out.println("==================================================");
    }
}

