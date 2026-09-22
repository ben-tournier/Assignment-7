// WorkoutLog.java
// Holds all logged TrainingSessions in memory, persists them to a file,
// and can report personal records (PRs).
//
// File format (one session per line, comma-separated):
//   Workout,<startTime>,<date>,<workout description>
//   LongRun,<startTime>,<date>,<miles>
//   NormalPractice,<startTime>,<date>,<miles>,<hasDoubleRun>,<doubleMileage>
// The first field is a "type tag" so loadFromFile() knows which subclass
// to rebuild when it reads a line back in.

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkoutLog
{
    private static final String FILE_NAME = "workouts.txt";

    private ArrayList<TrainingSessions> sessions;

    public WorkoutLog()
    {
        sessions = new ArrayList<TrainingSessions>();
        loadFromFile(); // populate `sessions` from any previous run
    }

    // Add a session to memory AND persist it to the file, so both stay in sync.
    public void addSession(TrainingSessions session)
    {
        sessions.add(session);
        appendToFile(session);
    }

    // Read workouts.txt (if it exists) and rebuild TrainingSessions objects
    // into `sessions`. Called once from the constructor.
    private void loadFromFile()
    {
        File file = new File(FILE_NAME);

        // First run: no file yet, so there's nothing to load.
        if (!file.exists())
        {
            return;
        }

        try (Scanner fileScanner = new Scanner(file))
        {
            while (fileScanner.hasNextLine())
            {
                String line = fileScanner.nextLine();
                if (line.isBlank())
                {
                    continue; // skip stray blank lines
                }

                String[] fields = line.split(",");
                String type = fields[0];

                // fields[1] = startTime, fields[2] = date for every type,
                // since they all come from TrainingSessions.
                String startTime = fields[1];
                String date = fields[2];

                if (type.equals("Workout"))
                {
                    String workoutDescription = fields[3];
                    sessions.add(new Workout(startTime, date, workoutDescription));
                }
                else if (type.equals("LongRun"))
                {
                    double miles = Double.parseDouble(fields[3]);
                    sessions.add(new LongRun(startTime, date, miles));
                }
                else if (type.equals("NormalPractice"))
                {
                    double miles = Double.parseDouble(fields[3]);
                    boolean hasDoubleRun = Boolean.parseBoolean(fields[4]);
                    double doubleMileage = Double.parseDouble(fields[5]);
                    sessions.add(new NormalPractice(startTime, date, miles, hasDoubleRun, doubleMileage));
                }
                // else: unrecognized type tag, silently skip the line
            }
        }
        catch (FileNotFoundException e)
        {
            // Can't actually happen since we just checked file.exists(),
            // but Scanner's File constructor forces us to handle it.
            System.out.println("Could not open " + FILE_NAME + ".");
        }
    }

    // Append a single session to the file, in the same format loadFromFile() expects.
    private void appendToFile(TrainingSessions session)
    {
        // `true` = append mode, so we don't overwrite previous lines.
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true)))
        {
            String startTime = session.getStartTime();
            String date = session.getDate();

            if (session instanceof Workout)
            {
                Workout workout = (Workout) session;
                writer.println("Workout," + startTime + "," + date + "," + workout.getWorkout());
            }
            else if (session instanceof LongRun)
            {
                LongRun longRun = (LongRun) session;
                writer.println("LongRun," + startTime + "," + date + "," + longRun.getMiles());
            }
            else if (session instanceof NormalPractice)
            {
                NormalPractice practice = (NormalPractice) session;
                writer.println("NormalPractice," + startTime + "," + date + "," +
                        practice.getMiles() + "," + practice.hasDoubleRun() + "," + practice.getDoubleMileage());
            }
        }
        catch (IOException e)
        {
            System.out.println("Could not write to " + FILE_NAME + ".");
        }
    }

    // Print every logged session so far, with its type-specific details.
    public void printHistory()
    {
        if (sessions.isEmpty())
        {
            System.out.println("No workouts logged yet.");
            return;
        }

        for (TrainingSessions session : sessions)
        {
            if (session instanceof Workout)
            {
                Workout workout = (Workout) session;
                System.out.println(workout + ", Workout: " + workout.getWorkout());
            }
            else if (session instanceof LongRun)
            {
                LongRun longRun = (LongRun) session;
                System.out.println(longRun + ", Miles: " + longRun.getMiles());
            }
            else if (session instanceof NormalPractice)
            {
                NormalPractice practice = (NormalPractice) session;
                System.out.println(practice + ", Miles: " + practice.getMiles() +
                        ", Double Run: " + practice.hasDoubleRun() +
                        ", Double Mileage: " + practice.getDoubleMileage());
            }
        }
    }

    // Return the LongRun with the most miles ever logged, or null if none exist.
    public LongRun getLongestRun()
    {
        LongRun longest = null;

        for (TrainingSessions session : sessions)
        {
            if (session instanceof LongRun)
            {
                LongRun longRun = (LongRun) session;
                if (longest == null || longRun.getMiles() > longest.getMiles())
                {
                    longest = longRun;
                }
            }
        }

        return longest;
    }

    // Return the NormalPractice with the most total mileage (regular + double run)
    // ever logged, or null if none exist.
    public NormalPractice getBestNormalPractice()
    {
        NormalPractice best = null;
        double bestTotal = -1;

        for (TrainingSessions session : sessions)
        {
            if (session instanceof NormalPractice)
            {
                NormalPractice practice = (NormalPractice) session;
                double total = practice.getMiles() + practice.getDoubleMileage();
                if (best == null || total > bestTotal)
                {
                    best = practice;
                    bestTotal = total;
                }
            }
        }

        return best;
    }
}
