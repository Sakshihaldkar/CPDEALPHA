import java.util.*;

public class TravelPlanner {
  private static final Scanner scanner = new Scanner(System.in);
  private static final List<Destination> itinerary = new ArrayList<>();
  private static double totalBudget = 0.0;

  public static void main(String[] args) {
    System.out.println("Welcome to the Travel Itinerary Planner!");

    boolean exit = false;
    while (!exit) {
      displayMenu();
      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (choice) {
        case 1 -> addDestination();
        case 2 -> viewItinerary();
        case 3 -> calculateBudget();
        case 4 -> System.out.println("Exiting. Have a great trip!");
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice == 4)
        exit = true;
    }
  }

  private static void displayMenu() {
    System.out.println("\n--- Travel Planner Menu ---");
    System.out.println("1. Add a Destination");
    System.out.println("2. View Itinerary");
    System.out.println("3. Calculate Budget");
    System.out.println("4. Exit");
    System.out.print("Enter your choice: ");
  }

  private static void addDestination() {
    System.out.print("Enter destination name: ");
    String name = scanner.nextLine();

    System.out.print("Enter start date (YYYY-MM-DD): ");
    String startDate = scanner.nextLine();

    System.out.print("Enter end date (YYYY-MM-DD): ");
    String endDate = scanner.nextLine();

    System.out.print("Enter budget for this destination: ");
    double budget = scanner.nextDouble();
    scanner.nextLine(); // Consume newline

    itinerary.add(new Destination(name, startDate, endDate, budget));
    totalBudget += budget;

    System.out.println("Destination added successfully!");
  }

  private static void viewItinerary() {
    if (itinerary.isEmpty()) {
      System.out.println("Your itinerary is currently empty.");
    } else {
      System.out.println("\n--- Your Itinerary ---");
      for (int i = 0; i < itinerary.size(); i++) {
        System.out.println((i + 1) + ". " + itinerary.get(i));
      }
    }
  }

  private static void calculateBudget() {
    System.out.printf("Total estimated budget for your trip: $%.2f%n", totalBudget);
  }

  static class Destination {
    String name;
    String startDate;
    String endDate;
    double budget;

    public Destination(String name, String startDate, String endDate, double budget) {
      this.name = name;
      this.startDate = startDate;
      this.endDate = endDate;
      this.budget = budget;
    }

    @Override
    public String toString() {
      return name + " (From " + startDate + " to " + endDate + ", Budget: $" + budget + ")";
    }
  }
}
