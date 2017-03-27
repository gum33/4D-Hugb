import java.util.ArrayList;
import java.util.List;

public class SearchManager {
    private ArrayList<Trip> trips= new ArrayList<>();
    private List<Trip> result;
    private TripContainer alltrips = new TripContainer();

    public SearchManager() {
       TripContainer alltrips = new TripContainer();
       
    }

    public int[] search(Category[] searchedCategory, TripContainer wh) {
        alltrips = wh;
        trips.ensureCapacity(100);
        trips = alltrips.getTrips();
        int n = searchedCategory.length;
        int c = trips.size();
    
        int i, j=0,k;
        int[] B = new int[c];
        for(i=0;i<c;i++) {
            boolean check = true;
            Trip look = trips.get(i);
            Category[] here= look.getCategories();
            for(k=0;k<n;k++) {
                for(int z=0;z<here.length;z++) {
                    if(here[z]==searchedCategory[k]) {
                        check = true;
                        break;
                    }
                    check = false;
                }
            }
            if(check) {
                B[j] = i+1;
                j++;
            }
        }
        return B;

    }

    public void sortDate() {

    }

    public void sortPrice() {

    }

    public void sortDuration() {

    }

    public void sortLanguage() {

    }

    public void addTrip(Trip t) {
        
    }
    public static void main(String[] args ) {
        
    }
}