import java.util.ArrayList;
import java.util.List;

public class SearchManager {
    private List<Trip> trips;
    private List<Trip> result;
    private TripContainer alltrips;

    public SearchManager() {
       TripContainer alltrips = new TripContainer();
    }

    public int[] search(Category[] searchedCategory) {
        trips = alltrips.getTrips();
        int n = searchedCategory.length;
        int c = trips.size();
        int i=0, j=0,k=0;
        int[] B = new int[c];
        for(i=0;i<c;i++) {
            boolean check = True;
            Trip look = trips.get(i);
            Category[] here= look.getCategories();
            for(k=0;k<n;k++) {
                for(int z=0;z<here.length;z++) {
                    if(here[z]==searchedCategory[k]) {
                        check = True;
                        break;
                    }
                    check = False;
                }
            }
            if(check) {
                B[j] = i;
                j++
            }
        }
        return B;



    }

    public void sortDate() {

    }

    public void sortPrice(); {

    }

    public void sortDuration() {

    }

    public void sortLanguage() {

    }

    public void addTrip(Trip) {
        
    }
    public static void main(String[] args ) {
        
    }
}