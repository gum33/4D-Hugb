import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class TestClass {
    private Trip trip1,trip2,trip3,trip4; //Mock trips
    private ArrayList<Trip> result1 = new ArrayList<>(); //Search for one object
    private ArrayList<Trip> result2 = new ArrayList<>(); //Another search for two objects
    private ArrayList<Trip> sorttest = new ArrayList<>(); //List returned from sorting
    private ArrayList<Trip> alltrips = new ArrayList<>();//List containing al trips
    private Trip[] exp = {trip1, trip4}; //Expected trips from search in price order
    private Category[] wp, gl, gh;  
    private Category wf , puffin, geiser, horse, glacier; //Mock categories
    private TripContainer TANK = new TripContainer(); //Storage for trips to search in
    private Location rvk, kef, vik, ak; //possible locations of trips
    private SearchManager find = new SearchManager(); //Initalized SearchManager
    private String[] lang1, lang2, lang3;

   
    @Before
    public  void setUp() {
        //Creating the mock trips and putting
        wf = new Category("Waterfall");
        puffin = new Category("Puffin");
        geiser = new Category("Geiser");
        horse = new Category("horse");
        glacier = new Category("Glacier");
        Category[] wp ={wf, puffin};
        Category[] gh = {horse, geiser};
        Category[] gl = {glacier};
        Location rvk = new Location("Reykjavík", "Reykjanes");
        Location vik = new Location("Vík", "Suðurland");
        Location kef = new Location ("Keflavik", "Reykjanes");
        Location ak = new Location("Akureyri", "Norðurland");
        Location[] allloc = {rvk, vik, kef, ak};
        String[] lang1 = {"English", "Spanish", "French"};
        String[] lang2 = {"English"};
        String[] lang3 ={"English", "German"};
        trip1 = new Trip(wp,5,"25.08.2017",8000, lang1,  "Fun day trip",7);
        trip2 = new Trip(gh,2, "02.06.2017", 10000, lang3,  "Boring trip",10);
        trip3 = new Trip(gl,3, "08.08.2017", 12000, lang2,  "Superb trip",10);
        trip4 = new Trip(wp,5.5, "01.07.2017", 5000, lang1,  "Colio trip",5);
        TANK.addTrip(trip1);
        TANK.addTrip(trip2);
        TANK.addTrip(trip3);
        TANK.addTrip(trip4);
        //Searching for trip containing category glacier
        result1 = find.search(gh, TANK);
        //Searching for trip containing, waterfall and puffin
        result2 = find.search(wp, TANK);  
    }

    @Test
    public  void testSearch() {
        //expect our result to contain trip2
        assertEquals(trip2, result1.get(0));
    }

    @Test
    public void testSearch2() {
        //Testing search that returns more than one trip
        assertTrue(result2.size()==2);
        assertEquals(trip1, result2.get(0));
        assertEquals(trip4, result2.get(1));
    }

    @Test
    public void testSearch3() {
        //Testing search that should have no results
        Category sunshine = new Category("Sunshine");
        Category[] sun ={sunshine};
        ArrayList<Trip> empty = find.search(sun, TANK);
        assertTrue(empty.isEmpty());
    }

    @Test
    public void testSearch4() {
        /*Searching for categories included in some trips 
        but no trips includes all*/
        Category[] wg = {wf, glacier};
        ArrayList<Trip> empty2 = find.search(wg, TANK);
        assertTrue(empty2.isEmpty());
    }


    @Test
    public void testPriceSort() {
        alltrips=TANK.getTrips();
        //Testing price and comparing them to the expected order
        sorttest = find.sortPrice(alltrips);
        assertEquals(trip4, sorttest.get(0));
        assertEquals(trip1, sorttest.get(1));
        assertEquals(trip2, sorttest.get(2));
        assertEquals(trip3, sorttest.get(3));
    }
    
    @Test
    public void testDateSort() {
        alltrips=TANK.getTrips();
        //Testing to sorting by date
        sorttest = find.sortDate(alltrips);
        assertEquals(trip2, sorttest.get(0));
        assertEquals(trip4, sorttest.get(1));
        assertEquals(trip3, sorttest.get(2));
        assertEquals(trip1, sorttest.get(3)); 
    }


    @Test
    public void testDurationSort() {

        alltrips=TANK.getTrips();
        sorttest = find.sortDuration(alltrips);
        //Comparing expected result to real result
        assertEquals(trip2, sorttest.get(0));
        assertEquals(trip3, sorttest.get(1));
        assertEquals(trip1, sorttest.get(2));
        assertEquals(trip4, sorttest.get(3)); 
    }


    public void testLanguageSort() {
        alltrips=TANK.getTrips();
        sorttest = find.sortLanguage(alltrips);

        assertEquals(trip1, sorttest.get(0));
        assertEquals(trip4, sorttest.get(1));
        assertEquals(trip3, sorttest.get(2));
        assertEquals(trip2, sorttest.get(3)); 
    }
    
    @After
    public void tearDown() {
        wf = null;
        puffin = null;
        geiser = null;
        horse = null;
        glacier =null;
        wp =null;
        gh = null;
        gl = null;
        rvk = null;
        vik = null;
        kef = null;
        ak = null;
        lang1 = null;
        lang2 = null;
        lang3 = null;
        trip1 = null;
        trip2 = null;
        trip3 = null;
        trip4 = null; 
        TANK = null;
        result1 = null;
        result2 = null;
        sorttest = null;
    }
    

    public static void main(String[] args) {     
    }
}