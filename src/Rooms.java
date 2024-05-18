import ADTs.*;

public class Rooms {
	private StackInterface<Room> bookableRooms;
	private QueueInterface<Reservation> waitingLine;
	private ListInterface<Room> bookedRooms;
	
	public Rooms() {
		bookableRooms = new Stack<Room>();
		waitingLine = new Queue<Reservation>();
		bookedRooms = new ArrayList<Room>();
	}
	
	public boolean bookRoom() {
		Room room = bookableRooms.pop();
		if (room == null) {return false;}
		
		room.setBooked(true);
		bookedRooms.add(room);
		return true;
	}
	
	public void addToPile(Room newRoom) {
		bookableRooms.push(newRoom);
	}
	
	public void addToWaitingLine(Reservation reservation) {
		waitingLine.enqueue(reservation);
	}
	
	public void printPile() {
        Object[] rooms = bookableRooms.toArray();
        for (int i = 0; i < rooms.length; i++) {
            Room room = (Room) rooms[i];
            room.printInfo();
        }
	}
	
	public void printWaitingLine() {
        Object[] reservations = waitingLine.toArray();
        for (int i = 0; i < reservations.length; i++) {
            Reservation reservation = (Reservation) reservations[i];
            reservation.printInfo();
        }
	}
	
	public void printBookedRooms() {
		for (int i = 0; i < bookedRooms.getEntryCount(); i++) {
			bookedRooms.getEntry(i).printInfo();
        }
	}
	
	public void processWaitingLine() {
		while (!bookableRooms.isEmpty() && !waitingLine.isEmpty()) {
			Reservation reservation = waitingLine.dequeue();
			Room room = bookableRooms.pop();
			room.setBooked(true);
			bookedRooms.add(room);
		}
	}
	
	public ListInterface<Room> getBookedRooms() {
		return bookedRooms;
	}
	
	public StackInterface<Room> getBookableRooms() {
		return bookableRooms;
	}
	
	public QueueInterface<Reservation> getWaitingLine() {
		return waitingLine;
	}
}
