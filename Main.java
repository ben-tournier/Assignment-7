// Main.java
// Menu-driven entry point for logging workouts and viewing PRs.

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        WorkoutLog log = new WorkoutLog();

        boolean running = true;
        while (running)
        {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice)
            {
                case "1":
                    logWorkout(scanner, log);
                    break;
                case "2":
                    logLongRun(scanner, log);
                    break;
                case "3":
                    logNormalPractice(scanner, log);
                    break;
                case "4":
                    log.printHistory();
                    break;
                case "5":
                    printPRs(log);
                    break;
                case "6":
                    running = false;
                    break;
                default:
                    // Anything that isn't "1"-"6" lands here, so bad menu
                    // input just reprints the menu instead of crashing.
                    System.out.println("Not a valid option.");
                    break;
            }
        }

        scanner.close();
    }

    private static void printMenu()
    {
        System.out.println();
        System.out.println("1. Log a Workout");
        System.out.println("2. Log a Long Run");
        System.out.println("3. Log a Normal Practice");
        System.out.println("4. View History");
        System.out.println("5. View Personal Records");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private static void logWorkout(Scanner scanner, WorkoutLog log)
    {
        System.out.print("Start time: ");
        String startTime = scanner.nextLine();
        System.out.print("Date: ");
        String date = scanner.nextLine();
        System.out.print("Workout description: ");
        String description = scanner.nextLine();

        log.addSession(new Workout(startTime, date, description));
        System.out.println("Workout logged.");
    }

    private static void logLongRun(Scanner scanner, WorkoutLog log)
    {
        System.out.print("Start time: ");
        String startTime = scanner.nextLine();
        System.out.print("Date: ");
        String date = scanner.nextLine();
        double miles = readNonNegativeDouble(scanner, "Miles: ");

        log.addSession(new LongRun(startTime, date, miles));
        System.out.println("Long run logged.");
    }

    private static void logNormalPractice(Scanner scanner, WorkoutLog log)
    {
        System.out.print("Start time: ");
        String startTime = scanner.nextLine();
        System.out.print("Date: ");
        String date = scanner.nextLine();
        double miles = readNonNegativeDouble(scanner, "Miles: ");
        boolean hasDoubleRun = readYesNo(scanner, "Was there a double run? (y/n): ");

        double doubleMileage = 0.0;
        if (hasDoubleRun)
        {
            doubleMileage = readNonNegativeDouble(scanner, "Double run miles: ");
        }

        log.addSession(new NormalPractice(startTime, date, miles, hasDoubleRun, doubleMileage));
        System.out.println("Normal practice logged.");
    }

    private static void printPRs(WorkoutLog log)
    {
        LongRun longestRun = log.getLongestRun();
        NormalPractice bestPractice = log.getBestNormalPractice();

        if (longestRun == null)
        {
            System.out.println("No long runs logged yet.");
        }
        else
        {
            System.out.println("Longest run: " + longestRun.getMiles() + " miles on " + longestRun.getDate());
        }

        if (bestPractice == null)
        {
            System.out.println("No normal practices logged yet.");
        }
        else
        {
            double total = bestPractice.getMiles() + bestPractice.getDoubleMileage();
            System.out.println("Best practice total: " + total + " miles on " + bestPractice.getDate());
        }
    }

    // Keeps asking until the user types a number that parses as a double
    // and isn't negative. Re-prompts on bad input instead of crashing.
    private static double readNonNegativeDouble(Scanner scanner, String prompt)
    {
        while (true)
        {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try
            {
                double value = Double.parseDouble(input);
                if (value < 0)
                {
                    System.out.println("Miles can't be negative, try again.");
                    continue;
                }
                return value;
            }
            catch (NumberFormatException e)
            {
                System.out.println("That's not a number, try again.");
            }
        }
    }

    // Keeps asking until the user types y/yes or n/no (case-insensitive).
    private static boolean readYesNo(Scanner scanner, String prompt)
    {
        while (true)
        {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes"))
            {
                return true;
            }
            if (input.equals("n") || input.equals("no"))
            {
                return false;
            }
            System.out.println("Please answer y or n.");
        }
    }
}
