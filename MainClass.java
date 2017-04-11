import java.util.Scanner;
import java.util.ArrayList;

public class MainClass {
    private static TripContainer tripcontainer;
    private static Category[] allcategories;
    private static SearchManager searchmanager;
    private static BookManager bookmanager;
    private static Booking booked;
    public static void setup() {
        tripcontainer = new TripContainer();
        tripcontainer.generateTrips();
        allcategories = tripcontainer.getallCategories();
        searchmanager = new SearchManager();
        bookmanager = new BookManager();
    }

    public static void firstSearch() {
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
        //Display results
        printResult(foundTrips);

    }

    public static ArrayList<Trip> findCategories() {
        //function to interact with while searching
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
    /*
    Interact with this for sorting
    then continue to printResult
     */
    public static ArrayList<Trip> sortInterface(ArrayList<Trip> sortthis) {
        //Function to interact with while sorting
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

    /*
    Working with the result of the search and sort to decide
    the next step, more info booking or start over.
     */
    public static void printResult(ArrayList<Trip> result) {
        if(result.size()<10) {
            for(int i=0;i<result.size();i++) {
                System.out.println("("+(i+1)+") "+result.get(i));
            }
        }
        else {
            for(int i=0;i<10;i++) {
                System.out.println("("+(i+1)+") "+result.get(i));
            }
            System.out.println("(0) Display all "+result.size()+" results");
        }
        System.out.println("Choose a trip number to get more information or book the trip");
        System.out.println("(b) Go back and search again");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.equals("0")) {
            for(int i=10;i<result.size();i++) {
            System.out.println("("+(i+1)+") "+result.get(i));
            }
            System.out.println("Choose a trip number to get more information or book the trip");
            System.out.println("(b) Go back and search again");
            scanner = new Scanner(System.in);
            input = scanner.nextLine();
        }
        if(input.equals("b")) {
                firstSearch();
        }
        else {
            try {
            int tripnr = Integer.parseInt(input);
            Trip selected = result.get(tripnr-1);
            tripWorker(selected);
        }
            catch(NumberFormatException e) {
                throw new IllegalArgumentException("Select a valid a valid tripnumber next time");
            }
        }
    }

    /* 
    Function to get more info from trip and call bookmanager
     */
    public static void tripWorker(Trip selectedtrip) {
        System.out.println("You have selected: " +selectedtrip.getDescription());
        System.out.println("The trip will is schedueled at: "+selectedtrip.getDate());
        System.out.println("The trip will take: "+ selectedtrip.getDuration()+" hours");
        System.out.println("The trip costs: " +selectedtrip.getPrice()+" kronur");
        System.out.print("The trip is available in: ");
        for(int i=0;i<selectedtrip.getLanguages().length;i++) {
            System.out.print(selectedtrip.getLanguages()[i]+", ");
        }
        System.out.print("\nYou will get to experience: ");
        for(int i=0;i<selectedtrip.getCategories().length;i++) {
            System.out.print(selectedtrip.getCategories()[i]+", ");
        }
        System.out.println("\n(0) Book trip\n(1) Go back and search again");
        try {
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            while(input!=0 && input!=1) {
                System.out.println("Please select (0) or (1)");
                scanner = new Scanner(System.in);
                input = scanner.nextInt();
            }
            if(input==0) {
                System.out.print("Please write in your name: ");
                scanner = new Scanner(System.in);
                String name = scanner.nextLine();
                System.out.print("\nPlease write number of people: ");
                scanner = new Scanner(System.in);
                int nrofpeople = scanner.nextInt();
                if(bookmanager.isTripFull(selectedtrip, nrofpeople)) {
                    booked=bookmanager.createBooking(selectedtrip, nrofpeople, name);
                    System.out.println(booked);
                }
                else System.out.println("Not enough space in this trip");

            }
            else if(input==1) {
                firstSearch();
            }
            
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("\nPlease select a number");
        }


    }
    public static void main(String[] args) {
        //Call setup to generate trips and iniate program
        setup();
        //Start search
        firstSearch();
       
        
    }
}