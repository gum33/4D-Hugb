import java.util.ArrayList;
import java.util.List;

public class TestClass {

    public static void setUp() {
        Category wf = new Category("Waterfall");
        Category puffin = new Category("Puffin");
        Category geiser = new Category("Geiser");
        Category horse = new Category("horse");
        Category glacier = new Category("Glacier");
        Category[] wp ={wf, puffin};
        Category[] gh = {horse, geiser};
        Category[] gl = {glacier};
        Location rvk = new Location("Reykjavík", "Reykjanes");
        Location vik = new Location("Vík", "Suðurland");
        Location kef = new Location ("Keflavik", "Reykjanes");
        Location ak = new Location("Akureyri", "Norðurland");
        Location[] allloc = {rvk, vik, kef, ak};
        Location isf = new Location("Isafjörður", "Vestfyrdir");
        String[] lang = {"English", "Spanish", "French"};

        Trip trip1 = new Trip(wp,5,"25.05.2017",5000, lang,  "Fun day trip",10);
        Trip trip2 = new Trip(gh,2, "02.06.2017", 10000, lang,  "Boring trip",10);
        Trip trip3 = new Trip(gl,3, "08.08.2017", 12000, lang,  "Superb trip",10);
        Trip trip4 = new Trip(wp,5.5, "01.08.2017", 8000, lang,  "Colio trip",5); 
        TripContainer TANK = new TripContainer();
        TANK.addTrip(trip1);
        TANK.addTrip(trip2);
        TANK.addTrip(trip3);
        TANK.addTrip(trip4);
        
        SearchManager find = new SearchManager();
        int[] result =new int[1];
        result = find.search(wp, TANK);
        ArrayList<Trip> funtrips= new ArrayList<Trip>();
        funtrips = TANK.getTrips();
        int count =0;
        for (int i=0;i<result.length;i++) {
            if(result[i]!=0)
            count++;
        }

        Trip[] sorttest = new Trip[count];
        for(int i =0;i<sorttest.length;i++) {
                Trip bob = funtrips.get(result[i]-1);
                sorttest[i] = bob;
        }

        
        find.sortPrice(sorttest);
        for (Trip i : sorttest) {
            System.out.println(i);
        }

    }

    public void testSearch() {
       
    }
    

    public static void main(String[] args) {
        setUp();
        
        
    }
}