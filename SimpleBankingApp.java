import java.util.Scanner;

public class SimpleBankingApp {
  private static double balance = 0.0;

  public static void main(String[] args) {
    Scanner scannner = new Scanner(System.in);
    boolean exit = false;

    System.out.println("welcome to the Simple Banking Application!");

    while (!exit) {
      displayMenu();

      System.out.println("entar your choice (1-4): ");
      int choice = scannner.nextInt();

      switch (choice) {
        case 1:
          checkBlance();
          break;
        case 2:
          depositMoney(scannner);
          break;
        case 3:
          withdrawMoney(scannner);
          break;
        case 4:
          System.out.println("Thanking for using the simple banking Application. Goodbye!");
          exit = true;
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
    scannner.close();
  }

  private static void displayMenu() {
    System.out.println("\n--- Mein Menu ---");
    System.out.println("1. Check Balance");
    System.out.println("2. Deposite Money");
    System.out.println("3. Withdraw Money");
    System.out.println("4. Exit");
  }

  private static void checkBlance() {
    System.out.println("Your current balance is : $" + balance);
  }

  private static void depositMoney(Scanner scanner) {
    System.out.println("Enter the amount to deposit : ");
    double amount = scanner.nextDouble();
    if (amount > 0) {
      balance += amount;

      System.out.println("Successfully deposited $" + amount);
    } else {
      System.out.println("Invalid amount. Please enter a positive value.");
    }
  }

  private static void withdrawMoney(Scanner scanner) {
    System.out.println("Enter the amount to withdraw:");
    double amount = scanner.nextDouble();
    if (amount > 0 && amount <= balance) {
      balance -= amount;
      System.out.println("Successfully withdraw $" + amount);
    } else if (amount > balance) {
      System.out.println("Insufficient balance!");
    } else {
      System.out.println("Invalid amount.Please enter a positive value.");
    }
  }
}
