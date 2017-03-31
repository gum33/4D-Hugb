/* Review Class contains reviews for trips */
public class Review {
    private int stars;
    private String comment;
    private String author;

    public Review(String a, String c, int s) {
        author = a;
        comment =c;
        if(s>5) s=5;
        if(0>s) s=0;
        stars = s;
    }

    public int getStars() {
        return stars;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String c) {
        comment = c;

    }

    public void setStars(int s) {
        stars = s;

    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String a) {
        author = a; 
    }

    public String toString() {
        String print = "" + author + " gave this trip: " +stars+ " stars. \nComment: " + comment;
        return print;
    }


    
    public static void main(String[] args) {
        Review newReview = new  Review("BOB", "Great Trip RECOMend",5);
        System.out.println(newReview);

    }
}
