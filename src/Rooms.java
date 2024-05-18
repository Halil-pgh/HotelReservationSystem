import ADTs.*;

public class Rooms {
	private StackInterface<Room> bookableRooms;
	private QueueInterface<Reservation> waitingLine;
	private ArrayListInterface<Room> bookedRooms;
	
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
		StackInterface<Room> tempStack = new Stack<Room>();
		while (!bookableRooms.isEmpty()) {
			Room room = bookableRooms.pop();
			tempStack.push(room);
			room.printInfo();
		}
		while(!tempStack.isEmpty()) {
			Room room = tempStack.pop();
			bookableRooms.push(room);
		}
	}
	
	public void printWaitingLine() {
		QueueInterface<Reservation> tempQueue = new Queue<Reservation>();
		while (!waitingLine.isEmpty()) {
			Reservation reservation = waitingLine.dequeue();
			reservation.printInfo();
			tempQueue.enqueue(reservation);
		}
		while (!tempQueue.isEmpty()) {
			Reservation reservation = tempQueue.dequeue();
			waitingLine.enqueue(reservation);
		}
	}
	
	public void printBookedRooms() {
		Room[] roomArr = bookedRooms.toArray();
		int i = 0;
		Room entry = roomArr[i];
		while (entry != null) {
			entry.printInfo();
			i++;
			entry = roomArr[i];
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
	
	public ArrayListInterface<Room> getBookedRooms() {
		return bookedRooms;
	}
	
	public StackInterface<Room> getBookableRooms() {
		return bookableRooms;
	}
	
	public QueueInterface<Reservation> getWaitingLine() {
		return waitingLine;
	}
}
