import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class BookManager {

    public BookManager() {    	
    }
    
    public boolean isTripFull(Trip selectedTrip, int passengers) {
    	int n = selectedTrip.getCapacity();
    	if (passengers <= n) {
    		return true;
    	}
    	return false;
    }    
    
    public Booking createBooking(Trip selectedTrip, int passengers, String name) {
    	Booking booking = new Booking(name, selectedTrip.getDate(), selectedTrip, passengers);
    	int n = selectedTrip.getCapacity();
    	selectedTrip.setCapacity(n-passengers);
    	return booking;
    }
    public void cancelBooking(Booking cancel) {
    	//int n = selectedTrip.getCapacity();
    	//int pass = cancel.
    	//selectedTrip.setCapacity(n-);
    	
    }
    

}
