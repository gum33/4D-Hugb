import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TripContainer {
    private ArrayList<Trip> alltrips= new ArrayList<Trip>();
    private int tripnumber=0;
    private Category[] allcategories;

    public TripContainer() {
        ArrayList<Trip> alltrips = new ArrayList<Trip>();
        tripnumber = 0;
        try {
            Scanner categorylist = new Scanner(new FileReader("categorylist.txt"));
            List<String> temps = new ArrayList<String>();
            String token = "";
            while(categorylist.hasNext()) {
                token = categorylist.next();
                temps.add(token);
            }
            categorylist.close();
            allcategories = new Category[temps.size()];
            for(int i =0;i<temps.size();i++) {
                Category cat = new Category(temps.get(i));;
                allcategories[i] = cat;
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("categorylist file is missing");
        }
        

    }
    public void addTrip(Trip t) {
        alltrips.ensureCapacity(tripnumber+1);
        alltrips.add(tripnumber,t);
        tripnumber++;
    }

    public ArrayList<Trip> getTrips() {
        return alltrips;
    
    }
    
    public Category[] getallCategories() {
        return allcategories;
    }
    public static void generateTrips() {
     double duration = generateDuration();
     int price = generatePrice(duration);
     String date = generateDate();
     int capacity = generateCapacity();

    }

    private static double generateDuration() {
        double dur = Math.random()*9+4;
        return Math.round(dur*2)/2.0;
    }

    private static int generatePrice(double d) {
        double p = 10000*d;
        double low = Math.random()*(d-1)+1;
        return (int)(((p/low)+99)/100)*100;
    }

    private static String generateDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        double random = Math.random()*183;
        long increment =Math.round(random);
        LocalDate localDate = LocalDate.now().plusDays(increment);
        return dtf.format(localDate); 
    }

    private static int generateCapacity() {
        return (int)Math.round(Math.random()*70+10);
    }

    private static Category[] generateCategory() {
        return null;
    }

    private static String[] generatelangueages() {
        return null;
    }

    private static String generateDescription() {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(generatePrice(5));
        System.out.println(generateDate());
        System.out.println(generateCapacity());
        TripContainer tester = new TripContainer();
        Category[] wowser = tester.getallCategories();
        System.out.println(wowser.length);
        
    }
}