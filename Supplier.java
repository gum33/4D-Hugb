public class Supplier{
    private String name;
    private Location[] location;
    private int telephone;

    public Supplier(String n, Location[] loc, int t) {
        name = n;
        location = loc;
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
        String print = name + "\nIs located at: ";
        for (int i =0;i<location.length;i++) {
            print = print + location[i] + ", ";
        }
        print = print + "\nTelephone: " + telephone;
        return print;
    }
    public static void main(String[] args) {
     Location kef = new Location("Keflavik", "Reykjanes");
     Location isaf = new Location("Isafjordur", "Vestfyrdir");

     Location[] l = {kef, isaf};
     Supplier filippo = new Supplier("Filippo", l, 6665555);   
     System.out.println(filippo);
     filippo.setTelephone(55512313);
     System.out.println(filippo); 
        }
}