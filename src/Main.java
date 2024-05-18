import java.io.FileNotFoundException;

import ADTs.*;

public class Main {
    private static String[] catogeries = {"Single", "Double", "Suite", "Deluxe"};
    private static Rooms[] catogerizedRooms = {new Rooms(), new Rooms(), new Rooms(), new Rooms()};

    private static ArrayListInterface<Room> unavailableRooms = new ArrayList<>(); // ?
    private static ArrayListInterface<Room> availableRooms = new ArrayList<>(); // ?

    public static void main(String[] args) {
        initializeRooms();
        printAvailableRooms();
        processReservation();
        printAvailableRooms();
        printWaitingLine();
        makeOddNumberRoomsAvailable();
        printAvailableRooms();
        processWaitingLines();
        printWaitingLine();
        printAvailableRooms();
    }

    private static void initializeRooms() {
        final int size = 5;
        for (int i = 0; i < catogeries.length; i++) {
            for (int j = 0; j < size; j++) {
                Room room = new Room(size * (i + 1) - j, catogeries[i]);
                catogerizedRooms[i].addToPile(room);
            }
        }
    }

    private static void processReservation() {
        QueueInterface<Reservation> reservations = new Queue<>();
        FileIO fileIO = new FileIO("reservations.txt");
        try {
			fileIO.readFile(reservations);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        while (!reservations.isEmpty()) {
            Reservation reservation = reservations.dequeue();
            Rooms rooms = findCategoryRooms(reservation.getRoomType());
            if (rooms != null) {
                if (!rooms.getBookableRooms().isEmpty()) {
                    rooms.bookRoom();
                } else {
                    rooms.addToWaitingLine(reservation);
                }
            }
        }
    }

    private static Rooms findCategoryRooms(String category) {
        for (int i = 0; i < catogeries.length; i++) {
            if (catogeries[i].equals(category)) {
                return catogerizedRooms[i];
            }
        }
        return null;
    }

    private static void processWaitingLines() {
        for (Rooms rooms : catogerizedRooms) {
            rooms.processWaitingLine();
        }
    }

    private static void makeOddNumberRoomsAvailable() {
        for (Rooms rooms : catogerizedRooms) {
            ArrayListInterface<Room> bookedRooms = rooms.getBookedRooms();
            for (int i = 0; i < bookedRooms.size(); i++) {
                Room room = bookedRooms.get(i);
                if (room.getNumber() % 2 != 0) {
                    room.setBooked(true);
                    rooms.getBookableRooms().push(room);
                    rooms.getBookedRooms().remove(i);
                    i--;
                }
            }
        }
    }

    private static void printAvailableRooms() {
        System.out.println("Available Rooms");
        for (int i = 0; i < catogerizedRooms.length; i++) {
            System.out.println(catogeries[i] + " Rooms (size: " + catogerizedRooms[i].getBookableRooms().size() + ")");
            catogerizedRooms[i].printPile();
            System.out.println();
        }
        System.out.println("=====================================");
    }

    private static void printWaitingLine() {
        System.out.println("Waiting Line");
        for (int i = 0; i < catogerizedRooms.length; i++) {
            System.out.println(catogeries[i] + " Rooms (size: " + catogerizedRooms[i].getWaitingLine().size() + ")");
            catogerizedRooms[i].printWaitingLine();
            System.out.println();
        }
        System.out.println("=====================================");
    }
}
