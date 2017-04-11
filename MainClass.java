import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class MainClass {
    private static TripContainer tripcontainer;
    private static Category[] allcategories;
    private static SearchManager searchmanager;
    private static BookManager bookmanager;
    private static Booking booked;
    private static int sortindex=0;
    
    //Setup ran at launch of the program
    public static void setup() {
        tripcontainer = new TripContainer();
        tripcontainer.generateTrips();
        allcategories = tripcontainer.getallCategories();
        searchmanager = new SearchManager();
        bookmanager = new BookManager();
    }

    //iniate search over all available search
    public static void firstSearch() {
        System.out.println("Select categories from the list");
        for(int i = 0;i<allcategories.length;i++) {
            System.out.print("("+i + ") " + allcategories[i]+ ". ");
            if (i%3==0 &&0<i) System.out.println();
        }
        System.out.println();
        ArrayList<Trip> foundTrips = findCategories();
        while(foundTrips.isEmpty()) {
            System.out.println("No trips found try another combination of categories");
            foundTrips = findCategories();
        }
        sortInterface(foundTrips);
        //Display results
        printResult(foundTrips);

    }

    public static ArrayList<Trip> findCategories() {
        //function to interact with while searching
        //Contacts SearchManager to search TripContainer
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] numbers = input.split(" ");
        Category[] selectedcategories = new Category[numbers.length];
        for(int i =0;i<numbers.length;i++) {
            try { 
                int categorynr = Integer.parseInt(numbers[i]);
                if(categorynr>=allcategories.length && categorynr<0) {
                System.out.println("Try selecting numbers beetween 0-26");
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
            sortindex = scanner.nextInt();
            while(sortindex<0 && 4<sortindex) {
                System.out.println("Select 0, 1 , 2, 3 or 4");
                scanner = new Scanner(System.in);
                sortindex=scanner.nextInt();
            }
            if(sortindex==1) return searchmanager.sortDate(sortthis);
            if(sortindex==2) return searchmanager.sortPrice(sortthis);
            if(sortindex==3) return searchmanager.sortDuration(sortthis);
            if(sortindex==4) return searchmanager.sortLanguage(sortthis);
            if(sortindex==0) return sortthis;
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
        String print="";
        if(result.size()<10) {
            for(int i=0;i<result.size();i++) {
                print = "";
                Trip tr = result.get(i);
                print+="("+(i+1)+") "+tr+" - Price: "+tr.getPrice()+" - Date: "+tr.getDate();
                print+=" - Duration: "+tr.getDuration()+"hours - Langauges: ";
                for(String lang: tr.getLanguages()) {
                    print+=lang+", ";
                }
                System.out.println(print);
            }
        }
        else {
            for(int i=0;i<10;i++) {
                print = "";
                Trip tr = result.get(i);
                print+="("+(i+1)+") "+tr+" - Price: "+tr.getPrice()+" - Date: "+tr.getDate();
                print+=" - Duration: "+tr.getDuration()+"hours - Langauges: ";
                for(String lang: tr.getLanguages()) {
                    print+=lang+", ";
                }
                System.out.println(print);
            }
            System.out.println("...(0) Display all "+result.size()+" results");
        }
        System.out.println("\nChoose a trip number to get more information or book the trip");
        System.out.println("(b) Go back and search again\n(s) Choose a different sorting method");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.equals("0")) {
            for(int i=10;i<result.size();i++) {
                print = "";
                Trip tr = result.get(i);
                print+="("+(i+1)+") "+tr+" - Price: "+tr.getPrice()+" - Date: "+tr.getDate();
                print+=" - Duration: "+tr.getDuration()+"hours - Langauges: ";
                for(String lang: tr.getLanguages()) {
                    print+=lang+", ";
                }
                System.out.println(print);
            }
            System.out.println("Choose a trip number to get more information or book the trip");
            System.out.println("(b) Go back and search again\n(s) Choose a different sorting method");
            scanner = new Scanner(System.in);
            input = scanner.nextLine();
        }
        if(input.equals("b") || input.equals("B")) {
                firstSearch();
        }
        if(input.equals("s") || input.equals("S")) {
            System.out.println();
            printResult(sortInterface(result));
        }
        else {
            try {
            int tripnr = Integer.parseInt(input);
            if(result.size()<tripnr) {
                System.out.println("\nChoose a number displayed infront of a trip!!");
                System.out.println();
                printResult(result);
            }
            Trip selected = result.get(tripnr-1);
            tripinfPrint(selected);
        }
            catch(NumberFormatException e) {
                throw new IllegalArgumentException("Select a valid a valid tripnumber next time");
            }
        }
    }

    /* 
    Function to get more info from trip and calls BookManager
    to book the trip.
     */
    public static void tripinfPrint(Trip selectedtrip) {
        System.out.println("\nYou have selected: " +selectedtrip.getDescription());
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
        System.out.println();
        tripWorker(selectedtrip);
    }
    public static void tripWorker(Trip selectedtrip) {
        
        System.out.println("\n(0) Book trip\n(1) Go back and search again\n(2) Add review\n(3) See existing reviews");
        try {
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            while(input!=0 && input!=1 && input!=2 && input!=3) {
                System.out.println("Please select (0) or (1)");
                scanner = new Scanner(System.in);
                input = scanner.nextInt();
            }
            while(input==0) {
                System.out.print("Please write in your name: ");
                scanner = new Scanner(System.in);
                String name = scanner.nextLine();
                System.out.print("\nPlease write number of people: ");
                scanner = new Scanner(System.in);
                int nrofpeople = scanner.nextInt();
                if(bookmanager.isTripFull(selectedtrip, nrofpeople)) {
                    booked=bookmanager.createBooking(selectedtrip, nrofpeople, name);
                    System.out.println(booked);
                    input = -1;
                }
                else { 
                    System.out.println("Not enough space in this trip");
                    int cap = selectedtrip.getCapacity();
                    System.out.println("This trip has space for: "+cap+" persons");
                    System.out.print("(0) Book for fewer people\n(1) Go back and search for another trip");
                    scanner = new Scanner(System.in);
                    input = scanner.nextInt();
                    while(input!=0 && input!=1) {
                        System.out.println("Please select (0) or (1)");
                        scanner = new Scanner(System.in);
                        input = scanner.nextInt();
                    }
                }
            }
            if(input==1) {
                firstSearch();
            }
            if(input==2) {
                System.out.println("Write your name: ");
                scanner = new Scanner(System.in);
                String author = scanner.next();
                System.out.println("Write in your review:");
                scanner = new Scanner(System.in);
                String rev = scanner.nextLine();
                System.out.println("Rate the trip from 1-5");
                scanner = new Scanner(System.in);
                int stars = scanner.nextInt();
                Review newrev= new Review(author,rev,stars);
                selectedtrip.setReview(newrev);
            }
            if(input==3) {
                double avg = selectedtrip.getAverage();
                System.out.printf("\nRating: %.1f",avg);
                System.out.println();
                for(Review re: selectedtrip.getReview()) {
                    System.out.println(re);
                }
            }
            System.out.println();
            tripWorker(selectedtrip);
            
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("\nPlease select a number");
        }


    }

    /*
    Interact with bookmanger for current booking.
     */
    public static void bminteract() {
        System.out.println("(0) View booking\n(1) Change booking\n(2) Cancel Booking");
        try {
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            while(input!=0 && input!=1 && input!=2) {
                System.out.println("Please choose 0, 1 or 2");
                scanner = new Scanner(System.in);
                input=scanner.nextInt();
            }
            if(input == 0) {
                System.out.println(booked);
                endofProgram();
            }
            if(input==1) {
                System.out.println("What do you want to change");
                System.out.println("(1) Name, (2) Number of people, (3) Both");
                scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                while(choice!=1 && choice!=2 && choice!=3) {
                    System.out.println("Please choose 1, 2 or 3");
                    scanner = new Scanner(System.in);
                    input=scanner.nextInt();
                }   
                int bookchange=0;
                if(choice==1) {
                    System.out.print("Insert new name: ");
                    scanner = new Scanner(System.in);
                    String newname = scanner.nextLine();
                    bookchange = bookmanager.changeBooking(booked, choice, newname, 0);
                }
                else if(choice==2) {
                    System.out.print("Insert number of people: ");
                    try {
                        scanner = new Scanner(System.in);
                        int newnr = scanner.nextInt();
                        bookchange = bookmanager.changeBooking(booked, choice, null, newnr);
                     }
                    catch(InputMismatchException e) {
                        System.out.println("Put in a number");
                        bminteract();
                    }
                }
                else if(choice==3) {
                    System.out.print("Insert new name: ");
                    scanner = new Scanner(System.in);
                    String newname = scanner.nextLine();
                    System.out.print("Insert number of people: ");
                    scanner = new Scanner(System.in);
                    int newnr = scanner.nextInt();
                    bookchange = bookmanager.changeBooking(booked, choice, newname, newnr);
                    endofProgram();
                }
                System.out.println(booked);
                if(bookchange!=0) {
                    System.out.println("BOOKING FAIL\nYou can only add: "+bookchange+" persons");
                    System.out.println("Please try another option");
                    bminteract();
                }
            }
            if(input==2) {
                bookmanager.cancelBooking(booked);
                System.out.println("Booking has been cancelled");
                endofProgram();
            }
        }
        catch(InputMismatchException e){ 
            System.out.println("Select a valid number");
            bminteract();
        }
    }

    /*
    Last step of program
    can restart or go to bminteract to change current booking
    */
    public static void endofProgram() {
        System.out.println("Choose your next step");
        System.out.println("(0) Start over and search again");
        System.out.println("(1) View or change your booking");
        System.out.println("(2) Exit");
        try {
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            if(input==0) {
                firstSearch();
            }
            if(input==1)  {
                bminteract();
            }
            if(input==2) {
                System.out.println("Thank you for using this program!");
            }
        }
        catch(InputMismatchException e) {
            System.out.println("Select 0, 1 or 2");
            endofProgram();
        }

    }
    public static void main(String[] args) {
        //Call setup to generate trips and iniate program
        setup();
        //Start search
        firstSearch();        
    }
}