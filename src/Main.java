import java.io.FileNotFoundException;

import ADTs.*;

public class Main {
    private static String[] catogeries = {"Single", "Double", "Suite", "Deluxe"};
    private static Rooms[] catogerizedRooms = {new Rooms(), new Rooms(), new Rooms(), new Rooms()};

    private static ListInterface<Room> unavailableRooms = new ArrayList<>();
    private static ListInterface<Room> availableRooms = new ArrayList<>();

    public static void main(String[] args) {
        initializeRooms();
        for (String category : catogeries) {
        	System.out.println("################## " + category + " ##################");
        	printStatsOfCategory(category);
        	System.out.println("############################################");
        }
        processReservation();
        System.out.println("Reservations are read and processed !!!");
        printWaitingLine();
        makeOddNumberRoomsAvailable();
        System.out.println("Odd numbered rooms are now available !!!");
        for (String category : catogeries) {
        	System.out.println("################## " + category + " ##################");
        	printStatsOfCategory(category);
        	System.out.println("############################################");
        }
        processWaitingLines();
        System.out.println("Waiting lines are now processed !!!");
        printWaitingLine();
        printAvailableRooms();
        printUnavailableRooms();
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
            ListInterface<Room> bookedRooms = rooms.getBookedRooms();
            for (int i = bookedRooms.getEntryCount() - 1; i >= 0; i--) {
                Room room = bookedRooms.getEntry(i);
                if (room.getNumber() % 2 != 0) {
                    room.setBooked(false);
                    rooms.getBookableRooms().push(room);
                    rooms.getBookedRooms().remove(i);
                }
            }
        }
    }
    
    private static void printStatsOfCategory(String category) {
    	Rooms rooms = findCategoryRooms(category);
    	System.out.println("Available Rooms for " + category + " Rooms (size: " + rooms.getBookableRooms().size() + ")");
    	rooms.printPile();
    	System.out.println();
    	System.out.println("Unavailable Rooms for " + category + " Rooms (size: " + rooms.getBookedRooms().getEntryCount() + ")");
    	rooms.printBookedRooms();
    }

    private static void printAvailableRooms() {
        System.out.println("Available Rooms");
        for (int i = 0; i < catogerizedRooms.length; i++) {
            System.out.println(catogeries[i] + " Rooms (size: " + catogerizedRooms[i].getBookableRooms().size() + ")");
            catogerizedRooms[i].printPile();
            System.out.println();
        }
        System.out.println("============================================");
    }
    
    private static void printUnavailableRooms() {
    	System.out.println("Unavailable Rooms");
    	for (int i = 0; i < catogerizedRooms.length; i++) {
    		System.out.println(catogeries[i] + " Rooms (size: " + catogerizedRooms[i].getBookedRooms().getEntryCount() + ")");
    		catogerizedRooms[i].printBookedRooms();
    		System.out.println();
    	}
    	System.out.println("============================================");
    }

    private static void printWaitingLine() {
        System.out.println("Waiting Line");
        for (int i = 0; i < catogerizedRooms.length; i++) {
            System.out.println(catogeries[i] + " Rooms (size: " + catogerizedRooms[i].getWaitingLine().size() + ")");
            catogerizedRooms[i].printWaitingLine();
            System.out.println();
        }
        System.out.println("============================================");
    }
}
