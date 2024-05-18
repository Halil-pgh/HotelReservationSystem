import ADTs.QueueInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileIO {
    File myFile;
    public FileIO(String path) {
        myFile = new File(path);

    }

    public void readFile(QueueInterface<Reservation> reservations) throws FileNotFoundException {
        Scanner reader = new Scanner(myFile);
        int lineIndex = 0;
        while(reader.hasNextLine()) {
            String line = reader.nextLine();
            if(lineIndex == 0) {
                lineIndex++;
                continue;
            }

            String[] lineData = line.split(",");
            int reservationID = Integer.parseInt(lineData[0]);
            String customerName = lineData[1];
            String roomType = lineData[2];

            Reservation newReservation = new Reservation(reservationID, customerName, roomType);
            reservations.enqueue(newReservation);
        }
    }
}