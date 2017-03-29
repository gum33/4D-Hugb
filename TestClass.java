import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class TestClass {
    private Trip[] sorttest;
    private Trip trip1,trip2,trip3,trip4;
    private ArrayList<Trip> result = new ArrayList<>();
    private Trip[] exp = {trip1, trip4};

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
        TripContainer TANK = new TripContainer();
        TANK.addTrip(trip1);
        TANK.addTrip(trip2);
        TANK.addTrip(trip3);
        TANK.addTrip(trip4);
        
        SearchManager find = new SearchManager();
        
        result = find.search(wp, TANK);
        ArrayList<Trip> funtrips= new ArrayList<Trip>();
        sorttest = new Trip[result.size()];
        //find.sortPrice(sorttest);
       
    }

    @Test
    public  void testSearch() {
        //assertEquals(sorttest.length,2);
        assertEquals(trip1, result.get(0));
    }
    /*@Test
    public void testPriceSort() {

    }

    @Test
    public void testDateSort() {

    }*/

    @After
    public void tearDown() {
     
    }
    

    public static void main(String[] args) {
       // setUp();
           
    }
}