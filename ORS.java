import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Reservation {
    private String name;
    private String date;
    private int roomNumber;

    public Reservation(String name, String date, int roomNumber) {
        this.name = name;
        this.date = date;
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    @Override
    public String toString() {
        return "Reservation [name=" + name + ", date=" + date + ", roomNumber=" + roomNumber + "]";
    }
}

class ReservationSystem {
    private List<Reservation> reservations;
    private int totalRooms;

    public ReservationSystem(int totalRooms) {
        this.totalRooms = totalRooms;
        reservations = new ArrayList<>();
    }

    public void addReservation(String name, String date, int roomNumber) {
        reservations.add(new Reservation(name, date, roomNumber));
    }

    public void listReservations() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public boolean isDateAvailable(String date, int roomNumber) {
        for (Reservation reservation : reservations) {
            if (reservation.getDate().equals(date) && reservation.getRoomNumber() == roomNumber) {
                return false;
            }
        }
        return true;
    }

    public void showRoomDetails() {
        System.out.println("Room Details:");
        for (int i = 1; i <= totalRooms; i++) {
            boolean isRoomAvailable = true;
            for (Reservation reservation : reservations) {
                if (reservation.getRoomNumber() == i) {
                    System.out.println("Room " + i + ": Reserved by " + reservation.getName() + " on " + reservation.getDate());
                    isRoomAvailable = false;
                    break;
                }
            }
            if (isRoomAvailable) {
                System.out.println("Room " + i + ": Available");
            }
        }
    }
}

public class ORS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("FLAZETECH - TASK1");
        System.out.println("Welcome to Hotel Reservation");
        System.out.print("Enter the total number of rooms: ");
        int totalRooms = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        ReservationSystem reservationSystem = new ReservationSystem(totalRooms);

        while (true) {
            System.out.println("1. Make a reservation");
            System.out.println("2. List reservations");
            System.out.println("3. Show room details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (roomNumber >= 1 && roomNumber <= totalRooms) {
                        if (reservationSystem.isDateAvailable(date, roomNumber)) {
                            reservationSystem.addReservation(name, date, roomNumber);
                            System.out.println("Reservation made successfully.");
                        } else {
                            System.out.println("Sorry, the room is already reserved for that date.");
                        }
                    } else {
                        System.out.println("Invalid room number. Please try again.");
                    }
                    break;
                case 2:
                    reservationSystem.listReservations();
                    break;
                case 3:
                    reservationSystem.showRoomDetails();
                    break;
                case 4:
                    System.out.println("Exiting the reservation system.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}