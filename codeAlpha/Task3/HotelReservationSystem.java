import java.util.*;

class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookRoom() {
        isAvailable = false;
    }

    public void releaseRoom() {
        isAvailable = true;
    }
}

class Reservation {
    private int reservationId;
    private String guestName;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(int reservationId, String guestName, Room room, Date checkInDate, Date checkOutDate) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }
}

class Feedback {
    private int feedbackId;
    private String guestName;
    private String message;
    private int rating;
    private Date timestamp;

    public Feedback(int feedbackId, String guestName, String message, int rating, Date timestamp) {
        this.feedbackId = feedbackId;
        this.guestName = guestName;
        this.message = message;
        this.rating = rating;
        this.timestamp = timestamp;
    }

    // Add getters and setters
}

class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private List<Feedback> feedbacks;
    private int reservationIdCounter;
    private int feedbackIdCounter;

    public List<Room> getRooms() {
        return rooms;
    }

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        feedbacks = new ArrayList<>();
        // Initialize some rooms
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        reservationIdCounter = 1;
        feedbackIdCounter = 1;
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Room Number: " + room.getRoomNumber() + ", Category: " + room.getCategory());
            }
        }
    }

    public void makeReservation(String guestName, Room room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(reservationIdCounter++, guestName, room, checkInDate, checkOutDate);
        room.bookRoom();
        reservations.add(reservation);
        System.out.println("Reservation successful. Your reservation ID is: " + reservation.getReservationId());
    }

    public void displayBookingDetails(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                System.out.println("Reservation ID: " + reservation.getReservationId());
                System.out.println("Guest Name: " + reservation.getGuestName());
                System.out.println("Room Number: " + reservation.getRoom().getRoomNumber());
                System.out.println("Category: " + reservation.getRoom().getCategory());
                System.out.println("Check-In Date: " + reservation.getCheckInDate());
                System.out.println("Check-Out Date: " + reservation.getCheckOutDate());
                return;
            }
        }
        System.out.println("Reservation not found with ID: " + reservationId);
    }

    public void collectFeedback(String guestName, String message, int rating) {
        Feedback feedback = new Feedback(feedbackIdCounter++, guestName, message, rating, new Date());
        feedbacks.add(feedback);
        System.out.println("Thank you for your feedback!");
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Provide feedback");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotel.displayAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter guest name: ");
                    scanner.nextLine(); // Consume newline
                    String guestName = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter check-in date (YYYY-MM-DD): ");
                    String checkInDateString = scanner.nextLine();
                    System.out.print("Enter check-out date (YYYY-MM-DD): ");
                    String checkOutDateString = scanner.nextLine();
                    // Parse date strings to Date objects
                    Date checkInDate = java.sql.Date.valueOf(checkInDateString);
                    Date checkOutDate = java.sql.Date.valueOf(checkOutDateString);
                    for (Room room : hotel.getRooms()) {
                        if (room.getRoomNumber() == roomNumber) {
                            if (room.isAvailable()) {
                                hotel.makeReservation(guestName, room, checkInDate, checkOutDate);
                            } else {
                                System.out.println("Room is not available.");
                            }
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter reservation ID: ");
                    int reservationId = scanner.nextInt();
                    hotel.displayBookingDetails(reservationId);
                    break;
                case 4:
                    System.out.print("Enter your name: ");
                    scanner.nextLine(); // Consume newline
                    String feedbackName = scanner.nextLine();
                    System.out.print("Rate your experience (1-5 stars): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Leave a message (optional): ");
                    String message = scanner.nextLine();
                    hotel.collectFeedback(feedbackName, message, rating);
                    break;
                case 5:
                    System.out.println("Thank you for using the system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
