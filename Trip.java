

public class Trip implements Comparable<Trip>{
    private Category[] category;
    private double duration; //Duration of Trip
    private String date; //Date of Trip
    private int price; 
    private Review[] review;
    private String[] languages;
    private Location[] location;
    private String description;
    private int capacity;

    public Trip(Category[] category, double duration, String date, int price, String[] languages, String description, int capacity) {
    	this.category = category;
    	this.duration = duration;
        this.date = date;
    	this.price = price;
    	this.languages = languages;
    	this.description = description;
        this.capacity = capacity;
    }
    
    public int compareTo(Trip t) {
        boolean check = true;
        while(check) {
            if(!(this.price==t.getPrice())) break;
            if(!this.date.equals(t.getDate())) break;
            if(!this.description.equals(t.getDescription())) break;
            if(!this.languages.equals(t.getLanguages())) break;
            if(!(this.capacity==t.getCapacity())) break;
            if(!this.category.equals(t.getCategories())) break;
            return 0;
        }
        return 1;
    }

    public Category[] getCategories() {
        return category;
    }

    public void setCategories(Category c){
    	

    }

    public String[] getLanguages() {
    	return this.languages;
    }

    public void setLanguages(String[] languages) {
    	this.languages = languages;
    }

    public int getPrice() {
    	return this.price;
    }

    public void setPrice(int price) {
    	this.price = price;
    }

    public Review[] getReview() {
    	return this.review;
    }

    public void setReview(Review[] review) {
    	this.review = review;
    }

    public String getDate() {
    	return this.date;
    }

    public void setDate(String date) {
    	this.date = date;
    }

    public String getDescription() {
    	return this.description;
    }

    public double getDuration() {
        return this.duration;
    }
       
    public int getCapacity() {
    	return this.capacity;
    }

    public void setCapacity(int capacity) {
    	this.capacity = capacity;
    }

    public String toString() {
        String play = "Enjoy some "+category[0];
        for(int i=1;i<category.length;i++) {
            play = play + " and " + category[i];
        }
        play = play + " at " + date;
        play = play+ ", for " + duration+ "hours";
        play = play+", for only: " +price;
        play = play+"Tour available in" ;
        for(int i=0;i<languages.length;i++) {
            play = play+ ", " +languages[i];
        }
        play= play+"\n";
        
        return play;
    }
    
    public static void main(String[] args) {

    }
}
