import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Integer> grades = new ArrayList<>();
    String choice;

    System.out.println("Welcome to the Student Grade Tracker!");

    do {
      System.out.println("Enter a student's grade (0-100):");
      int grade = scanner.nextInt();
      if (grade >= 0 && grade <= 100) {
        grades.add(grade);
      } else {
        System.out.println("Invalid grade. Please enter a grade between 0 and 100.");
      }

      System.out.println("Do you want to add another grade? (yes/no):");
      choice = scanner.next();
    } while (choice.equalsIgnoreCase("yes"));

    if (!grades.isEmpty()) {
      int total = 0, highest = Integer.MIN_VALUE, lowest = Integer.MAX_VALUE;
      for (int grade : grades) {
        total += grade;
        if (grade > highest)
          highest = grade;
        if (grade < lowest)
          lowest = grade;
      }

      double average = total / (double) grades.size();
      System.out.println("\n--- Grade Statistics ---");
      System.out.println("Average Grade: " + average);
      System.out.println("Highest Grade: " + highest);
      System.out.println("Lowest Grade: " + lowest);
    } else {
      System.out.println("No grades were entered.");
    }

    scanner.close();
  }
}
