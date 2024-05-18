public class Room {
	private boolean booked;
	private int roomNumber;
	private String roomType;
	
	public Room(int number, String type) {
		booked = false;
		roomNumber = number;
		roomType = type;
		
	}
	
	public void setBooked(boolean newValue) {
		booked = newValue;
	}
	
	public boolean isAvailable() {
		return !booked;
	}
	
	public int getNumber() {
		return roomNumber;
	}
	
	public String getType() {
		return roomType;
	}
	
	public void printInfo() {
		System.out.println("Room Number: " + roomNumber + ", Room Type: " + roomType + ", " + (booked ? "Unavailable" : "Available"));
	}
}

