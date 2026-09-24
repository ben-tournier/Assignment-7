## 1
I learned from the article how the instanceof operator can be used when working with polymorphism. The example showed that when there are multiple vehicle subtypes, the program can check whether an object is a Bike, cast it to a temporary Bike variable, and print it. If it is not a Bike, it skips it and checks the next type.

## 2
I added an abstract getTotalMiles() method to TrainingSessions, so every type of session has to report its own mileage. Each subclass (LongRun, NormalPractice, and Workout) now overrides getTotalMiles() and toString() so it can print its own details. I also added two overloaded getPace() methods to LongRun: one takes the total minutes and the other takes hours and minutes. Finally, I wrote a Driver class to test everything.

## 3
The overrides are used in the Driver, where all the sessions are stored in one TrainingSessions array. A loop prints each one and adds up the total miles, and each object uses its own version of the methods without the loop needing to know what type it is. NormalPractice also includes the double run in its mileage. The overloaded getPace() methods are called on the long run in the Driver, once with 90 minutes and once with 1 hour and 30 minutes, and both give the same pace of 7.50 min/mile.
## 4
    The main challenge that I feel like I enocuntered was figuring out the direction to go. When I got the code it was a pretty small four file
    folder with simple objects and methods. The biggest challenge was figuring out the simplest way to go about adding the assignment needs without overcomplicating the program.
## 5
    Claude code was used to help proofread everything at the end of the assignment for simple errors, be a sounding board for ideas to add, create a simple driver class that I could use to test everything in the code, as well as make a "skeleton" that I filled in the way I wanted for the methods, and to make sure all of my comments documenting my work showed I actually understood what was built together. 