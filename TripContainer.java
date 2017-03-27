import java.util.ArrayList;
import java.util.List;


public class TripContainer {
    private List<Trip> alltrips;
    private int tripnumber;

    public TripContainer() {
        List<Trip> alltrips = new ArrayList<Trip>();
        tripnumber = 0;
    }
    public void addTrip(Trip t) {
        alltrips.add(tripnumber,t);
        tripnumber++;
    }

    public List<Trip> getTrips() {
        return alltrips;
    
    }
    public void generateTrips() {
     
    }
}