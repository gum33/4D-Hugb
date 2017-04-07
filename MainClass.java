import java.util.Scanner;
import java.util.ArrayList;

public class MainClass {
    private static TripContainer tripcontainer;
    private static Category[] allcategories;
    private static SearchManager searchmanager;
    public static void setup() {
        tripcontainer = new TripContainer();
        tripcontainer.generateTrips();
        allcategories = tripcontainer.getallCategories();
        searchmanager = new SearchManager();
    }

    public static ArrayList<Trip> findCategories() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] numbers = input.split(" ");
        Category[] selectedcategories = new Category[numbers.length];
        for(int i =0;i<numbers.length;i++) {
            try { 
                int categorynr = Integer.parseInt(numbers[i]);
                if(categorynr>=allcategories.length) {
                System.out.println("SELECT BETTER STUPID");
                return findCategories();
                }
                selectedcategories[i] = allcategories[categorynr];
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("\nSELECT A NUMBER NEXT TIME");
            }
            
        } 
        return searchmanager.search(selectedcategories, tripcontainer);
    }
    public static ArrayList<Trip> sortInterface(ArrayList<Trip> sortthis) {
        System.out.println("Select sorting method");
        System.out.print("(0) No sort\n(1) Date\n(2) Price\n(3) Duration\n(4) Langauges\n");
        Scanner scanner = new Scanner(System.in);
        try {
            int sortindex = scanner.nextInt();
            if(sortindex==1) return searchmanager.sortDate(sortthis);
            if(sortindex==2) return searchmanager.sortPrice(sortthis);
            if(sortindex==3) return searchmanager.sortDuration(sortthis);
            if(sortindex==4) return searchmanager.sortLanguage(sortthis);
            if(sortindex==0) return sortthis;
            System.out.println("Select a valid number you idiot");
            return sortInterface(sortthis);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("\nSELECT A NUMBER NEXT TIME");
        }
    }
    public static void main(String[] args) {
        setup();
        System.out.println("Select categories from the list");
        for(int i = 0;i<allcategories.length;i++) {
            System.out.print("("+i + ") " + allcategories[i]+ ". ");
            if (i%3==0 &&0<i) System.out.println();
        }
        System.out.println();
        ArrayList<Trip> foundTrips = findCategories();
        while(foundTrips.isEmpty()) {
            System.out.println("No trips found try something else you sucker");
            foundTrips = findCategories();
        }
        sortInterface(foundTrips);
        for(int i=0;i<foundTrips.size();i++) {
            System.out.println(foundTrips.get(i));
        }
    }
}