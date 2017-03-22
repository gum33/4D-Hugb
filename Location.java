public class Location {
    
    private String placename;
    private String area;
    
    public Location(String n, String a) {     
        placename = n;
        area = a;
    }

    public String getPlacename() {
        return placename;
    }

    public String getArea() {
        return area;
    }

    public void setPlacename(String p) {
        placename = p;
    }

    public void setArea(String a) {
        area = a;
    }

    public String toString() {
        return placename;
    }
    public static void main(String[] args) {
            
        }
}