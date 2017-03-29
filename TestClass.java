import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class TestClass {
    private Trip[] sorttest;
    private Trip[] equal = new Trip[2];
    private Trip trip1,trip2,trip3,trip4;
    private int[] result;
    private int[] exp = {1,4,0, 0};

    @Before
    public  void setUp() {
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

        trip1 = new Trip(wp,5,"25.05.2017",8000, lang,  "Fun day trip",10);
        trip2 = new Trip(gh,2, "02.06.2017", 10000, lang,  "Boring trip",10);
        trip3 = new Trip(gl,3, "08.08.2017", 12000, lang,  "Superb trip",10);
        trip4 = new Trip(wp,5.5, "01.08.2017", 5000, lang,  "Colio trip",5); 
        equal[0]=trip1;
        equal[1]=trip4;
        TripContainer TANK = new TripContainer();
        TANK.addTrip(trip1);
        TANK.addTrip(trip2);
        TANK.addTrip(trip3);
        TANK.addTrip(trip4);
        
        SearchManager find = new SearchManager();
        
        result = find.search(wp, TANK);
        ArrayList<Trip> funtrips= new ArrayList<Trip>();
        funtrips = TANK.getTrips();
        int count =0;
        for (int i=0;i<result.length;i++) {
            if(result[i]!=0) count++;
        }

        sorttest = new Trip[count];
        for(int i =0;i<sorttest.length;i++) {
                Trip bob = funtrips.get(result[i]-1);
                sorttest[i] = bob;
        }

        
        find.sortPrice(sorttest);
        for (Trip i : sorttest) {
            System.out.println(i);
        }

    }

    @Test
    public  void testSearch() {
        assertEquals(8000,trip1.getPrice());
        assertArrayEquals(exp, result);
    }
    

    public static void main(String[] args) {
       // setUp();
           
    }
}