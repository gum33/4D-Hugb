import java.util.ArrayList;
import java.util.List;
public class TripContainer {
    private ArrayList<Trip> alltrips= new ArrayList<Trip>();
    private int tripnumber=0;

    public TripContainer() {
        ArrayList<Trip> alltrips = new ArrayList<Trip>();
        tripnumber = 0;
    }
    public void addTrip(Trip t) {
        alltrips.ensureCapacity(tripnumber+1);
        alltrips.add(tripnumber,t);
        tripnumber++;
    }

    public ArrayList<Trip> getTrips() {
        return alltrips;
    
    }
    public void generateTrips() {
     
    }
}