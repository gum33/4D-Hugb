import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class TestClass {
    private Trip[] sorttest;
    private Trip trip1,trip2,trip3,trip4; 
    private ArrayList<Trip> result = new ArrayList<>();
    private Trip[] exp = {trip1, trip4}; //Expected trips from search in price order
    private Category[] wp, gl, gh;
    private Category wf , puffin, geiser, horse, glacier;
    private TripContainer TANK = new TripContainer();
    private Location rvk, kef, vik, ak;

   
    @Before
    public  void setUp() {
        /*Creating the trips and putting */
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
        trip1 = new Trip(wp,5,"25.05.2017",5000, lang,  "Fun day trip",10);
        trip2 = new Trip(gh,2, "02.06.2017", 10000, lang,  "Boring trip",10);
        trip3 = new Trip(gl,3, "08.08.2017", 12000, lang,  "Superb trip",10);
        trip4 = new Trip(wp,5.5, "01.08.2017", 8000, lang,  "Colio trip",5); 
        TripContainer TANK = new TripContainer();
        SearchManager find = new SearchManager();
        TANK.addTrip(trip1);
        TANK.addTrip(trip2);
        TANK.addTrip(trip3);
        TANK.addTrip(trip4);
        result = find.search(wp, TANK);
        sorttest = find.sortPrice(result);
       
    }

    @Test
    public  void testSearch() {
        //assertEquals(sorttest.length,2);
        assertEquals(trip1, result.get(0));
    }


    @Test
    public void testPriceSort() {
        //Expected result after sortping price
        assertEquals(trip4,sorttest[0]);
        assertEquals(trip1, sorttest[1]);
    }
    /*
    @Test
    public void testDateSort() {

    }*/

    @After
    public void tearDown() {
        Category wf = null;
        Category puffin = null;
        Category geiser = null;
        Category horse = null;
        Category glacier =null;
        Category[] wp =null;
        Category[] gh = null;
        gl = null;
        Location rvk = null;
        Location vik = null;
        Location kef = null;
        Location ak = null;
        Location[] allloc = null;
        Location isf = null;
        String[] lang = null;
        trip1 = null;
        trip2 = null;
        trip3 = null;
        trip4 = null; 
        TripContainer TANK = null;
    }
    

    public static void main(String[] args) {
       // setUp();
           
    }
}