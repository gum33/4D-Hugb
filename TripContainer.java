import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TripContainer {
    private static final ArrayList<Trip> alltrips= new ArrayList<Trip>();
    private int tripnumber=0;
    private static Category[] allcategories;
    private static Location[] allocs;
    private static Supplier[] allsuppliers;

    //Constructs container and pulls information from text files to make trips
    public TripContainer() {
        ArrayList<Trip> alltrips = new ArrayList<Trip>();
        tripnumber = 0;
        //Get categories from text file 
        try {
            Scanner categorylist = new Scanner(new FileReader("categorylist.txt"));
            List<String> temps = new ArrayList<String>();
            String token = "";
            while(categorylist.hasNext()) {
                token = categorylist.nextLine();
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
        try {
            Scanner suploc = new Scanner(new FileReader("locsup.txt"));
            allocs = new Location[8];
            int i =0;
            while(suploc.hasNext()) {
                String temp = suploc.nextLine();
                Location tmp = new Location(temp);
                allocs[i] = tmp;
                i++;
                
            }
            Scanner supper = new Scanner(new FileReader("sup&loc.txt"));
            allsuppliers = new Supplier[9];
            int ph=0;
            i=0;
            while(supper.hasNext()) {
               String sup =supper.nextLine();
               String sup1=sup.substring(0,7);
               String sup2=sup.substring(8,sup.length());
               ph = Integer.parseInt(sup1);
               Supplier tmp = new Supplier(sup2,allocs[0],ph);
               allsuppliers[i] = tmp;
               i++;

            }
        }
        catch(FileNotFoundException e) {
            throw new IllegalArgumentException("sup&loc.txt is missing");
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

    /*
    For our purpose we randomly generate trips
    for the container by calling on this method */
    public static void generateTrips() {
        for(int i = 0;i<2000;i++) {
            double duration = generateDuration();
            int price = generatePrice(duration);
            String date = generateDate();
            int capacity = generateCapacity();
            Category[] category = generateCategory();
            String[] langs=generatelangueages();
            Location loc = generateLocation();
            String desc = generateDescription(category[0],loc);
            Trip tripper = new Trip(category, duration, date, price, langs, desc, capacity, loc);
            tripper.setSupplier(generateSupplier());
            alltrips.add(tripper);
        }
    }

    private static double generateDuration() {
        double dur = Math.random()*9+4;
        return Math.round(dur*2)/2.0;
    }

    //Price generate, longer duration increased chance of high price
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
    private static Location generateLocation() {
        int rnd = (int)Math.round(Math.random()*7);
        return allocs[rnd];
    }
    
    //generates up to 5 categories for the trip
    private static Category[] generateCategory() {    
        int othernumber =(int)Math.round(Math.random()*4 + 1);
        Category[] funk = new Category[othernumber];
        for(int i=0;i<funk.length;i++) {
            int somenumber = (int)Math.round(Math.random()*(allcategories.length-1));
            funk[i] = allcategories[somenumber];
        }
        return funk;
    }

    //trips can be available in multible languages.
    private static String[] generatelangueages() {
        int nr = (int)Math.round(Math.random()*100);
        if(nr<85) {
           String[] wow = {"english"};
           return wow;
        }
        else if(nr<93) {
           String[] wow ={"french", "english"};
           return wow;
        }
        else if(nr<97) {
           String[] wow = {"spanish", "english"};
           return wow;
        }
        else  {
           String[] wow = {"german"};
           return wow;
        }
        
    }

    private static String generateDescription(Category cat, Location loc) {
        String[] adjective ={"Great", "Beautiful", "Wonderful", "Fantastic","Cool","Awesome","Exciting"
        ,"Adventurous","A Different","Bombastic","Wunderbar","Nice"};
        String[] triptype= {"adventure", "trip", "exploration", "tour", "visit"};
        int adj = (int)Math.round(Math.random()*11);
        int tt =(int)Math.round(Math.random()*4);
        String desc = adjective[adj]+ " " +cat + " " +triptype[tt] + " in " +loc+".";
        return desc;
    }

    private static Supplier generateSupplier() {
        int rnd = (int)Math.round(Math.random()*8);
        return allsuppliers[rnd];
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