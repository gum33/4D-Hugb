import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class BookManager {
    public static final ArrayList<Booking> bookings = new ArrayList<Booking>();
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
        bookings.add(booking);
    	int n = selectedTrip.getCapacity();
    	selectedTrip.setCapacity(n-passengers);
    	return booking;
    }
    
    public void cancelBooking(Booking aborted) {
    	Trip trip = aborted.getTrip();
        bookings.remove(aborted);
    	int n = trip.getCapacity();
    	int pass = aborted.getPassengers();
    	trip.setCapacity(n+pass);
    	aborted = null;
        
    }
    
    public int changeBooking(Booking mod, int here, String name, int passengers) {
    	if (here == 1 || here == 3) {
    		mod.setName(name);    		
    	}
    	if (here == 2 || here == 3) {
    		Trip trip = mod.getTrip();
    		int prevpassengers = mod.getPassengers();
    		boolean check = isTripFull(trip, passengers-prevpassengers);
    		if (check) {
    			mod.setPassengers(passengers);
        		int n = trip.getCapacity();    		
            	trip.setCapacity(n+passengers-prevpassengers);        			
    		}
    		else {
    			return trip.getCapacity(); // I want to return the people that are possible to add
    		}
    	}
    	return 0;
    }
    

}
