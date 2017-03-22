public class Supplier{
    private String name;
    private Location[] location;
    private int telephone;

    public Supplier(String n, Location[] loc, int t) {
        name = n;
        location = Location[];
        telephone =t;
    }

    public String getName() {
        return name;
    }

    public Location[] getLocation() {
        return location;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setName(String n) {
        name =n;
    }

    public void setLocation(Location[] l) {
        location = l;
    }

    public void setTelephone(int t) {
        telephone = t;
    }

    public String toString() {
        String print = name "\nIs located at: ";
        for (int i =0;i<location.length;i++) {
            print + Location[i] + ", ";
        }
        print =+ "\nTelephone: " + telephone;
        return print;
    }
    public static void main(String[] args) {
            
        }
}