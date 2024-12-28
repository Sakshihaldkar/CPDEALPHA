import java.util.*;

public class Hotel_Reservation {
  private static final Scanner scanner = new Scanner(System.in);
  private static final List<Room> rooms = new ArrayList<>();
  private static final Map<Integer, Booking> bookings = new HashMap<>();
  private static int bookingIdCounter = 1;

  public static void main(String[] args) {
    initializeRooms();

    System.out.println("Welcome to the Hotel Reservation System!");

    boolean exit = false;
    while (!exit) {
      displayMenu();
      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (choice) {
        case 1 -> searchRooms();
        case 2 -> makeReservation();
        case 3 -> viewBookingDetails();
        case 4 -> System.out.println("Thank you for using the Hotel Reservation System. Goodbye!");
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice == 4)
        exit = true;
    }
  }

  private static void initializeRooms() {
    rooms.add(new Room(101, "Single", 50, true));
    rooms.add(new Room(102, "Double", 75, true));
    rooms.add(new Room(103, "Suite", 120, true));
    rooms.add(new Room(104, "Single", 50, true));
    rooms.add(new Room(105, "Double", 75, true));
  }

  private static void displayMenu() {
    System.out.println("\n--- Hotel Reservation Menu ---");
    System.out.println("1. Search Available Rooms");
    System.out.println("2. Make a Reservation");
    System.out.println("3. View Booking Details");
    System.out.println("4. Exit");
    System.out.print("Enter your choice: ");
  }

  private static void searchRooms() {
    System.out.println("Available Rooms:");
    for (Room room : rooms) {
      if (room.isAvailable()) {
        System.out.println(room);
      }
    }
  }

  private static void makeReservation() {
    System.out.print("Enter the room number to book: ");
    int roomNumber = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    Room roomToBook = findRoomByNumber(roomNumber);
    if (roomToBook == null || !roomToBook.isAvailable()) {
      System.out.println("Room is not available or does not exist.");
      return;
    }

    System.out.print("Enter your name: ");
    String customerName = scanner.nextLine();

    System.out.print("Enter number of nights: ");
    int nights = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    double totalCost = roomToBook.getPricePerNight() * nights;
    System.out.printf("The total cost is $%.2f. Proceed to payment? (yes/no): ", totalCost);
    String confirm = scanner.nextLine();

    if (confirm.equalsIgnoreCase("yes")) {
      roomToBook.setAvailable(false);
      Booking booking = new Booking(bookingIdCounter++, customerName, roomToBook, nights, totalCost);
      bookings.put(booking.getBookingId(), booking);
      System.out.println("Booking successful! Your booking ID is: " + booking.getBookingId());
    } else {
      System.out.println("Booking canceled.");
    }
  }

  private static void viewBookingDetails() {
    System.out.print("Enter your booking ID: ");
    int bookingId = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    Booking booking = bookings.get(bookingId);
    if (booking != null) {
      System.out.println("\n--- Booking Details ---");
      System.out.println(booking);
    } else {
      System.out.println("No booking found with the provided ID.");
    }
  }

  private static Room findRoomByNumber(int roomNumber) {
    for (Room room : rooms) {
      if (room.getRoomNumber() == roomNumber) {
        return room;
      }
    }
    return null;
  }

  static class Room {
    private final int roomNumber;
    private final String type;
    private final double pricePerNight;
    private boolean available;

    public Room(int roomNumber, String type, double pricePerNight, boolean available) {
      this.roomNumber = roomNumber;
      this.type = type;
      this.pricePerNight = pricePerNight;
      this.available = available;
    }

    public int getRoomNumber() {
      return roomNumber;
    }

    public String getType() {
      return type;
    }

    public double getPricePerNight() {
      return pricePerNight;
    }

    public boolean isAvailable() {
      return available;
    }

    public void setAvailable(boolean available) {
      this.available = available;
    }

    @Override
    public String toString() {
      return "Room " + roomNumber + " (" + type + ", $" + pricePerNight + "/night)";
    }
  }

  static class Booking {
    private final int bookingId;
    private final String customerName;
    private final Room room;
    private final int nights;
    private final double totalCost;

    public Booking(int bookingId, String customerName, Room room, int nights, double totalCost) {
      this.bookingId = bookingId;
      this.customerName = customerName;
      this.room = room;
      this.nights = nights;
      this.totalCost = totalCost;
    }

    public int getBookingId() {
      return bookingId;
    }

    @Override
    public String toString() {
      return "Booking ID: " + bookingId +
          "\nCustomer Name: " + customerName +
          "\nRoom: " + room +
          "\nNights: " + nights +
          "\nTotal Cost: $" + totalCost;
    }
  }
}
