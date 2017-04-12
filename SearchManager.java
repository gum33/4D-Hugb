import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class SearchManager {
    private ArrayList<Trip> trips= new ArrayList<>();
    private List<Trip> result;
    private TripContainer alltrips = new TripContainer();

    public SearchManager() {
       TripContainer alltrips = new TripContainer();
       
    }

    public ArrayList<Trip> search(Category[] searchedCategory, TripContainer wh) {
        alltrips = wh;
        trips = alltrips.getTrips();
        int n = searchedCategory.length;
        int c = trips.size();
        int i,  k;
        ArrayList<Trip> B = new ArrayList<>();
        for(i=0;i<c;i++) {
            boolean check = true;
            Trip look = trips.get(i);
            Category[] here= look.getCategories();
            for(k=0;k<n;k++) {
                for(int z=0;z<here.length;z++) {
                    if(here[z]==searchedCategory[k]) {
                        check = true;
                        break;
                    }
                    check = false;
                }
                if(!check) break;
            }
            if(check) {
                B.add(look);
            }
        }
        return B;
        

    }

    public ArrayList<Trip> sortDate(ArrayList<Trip> arr) {
    	int n = arr.size(); 
    	for (int i = 1; i < n; i++) {
    		int j = i;
    		boolean check = true;
    		while(j > 0 && check) {
    			check = false;
    			Trip trip1 = arr.get(j);
    			Trip trip2 = arr.get(j-1);
    			// year
    			String s1 = trip1.getDate().substring(6,10);
    			String s2 = trip2.getDate().substring(6,10);    			
    			int y1 = Integer.parseInt(s1);
    			int y2 = Integer.parseInt(s2);  
    			// month
    			String s3 = trip1.getDate().substring(3,5);
    			String s4 = trip2.getDate().substring(3,5);    			
    			int m1 = Integer.parseInt(s3);
    			int m2 = Integer.parseInt(s4); 
    			// day
    			String s5 = trip1.getDate().substring(0,2);
    			String s6 = trip2.getDate().substring(0,2);    			
    			int d1 = Integer.parseInt(s5);
    			int d2 = Integer.parseInt(s6);    			
    			    			
    			if (y1 < y2) {
    				Trip temp = arr.get(j);
    				arr.set(j, arr.get(j-1));    				
    				arr.set(j-1, temp);    	
    				check = true;
    			} else {
    				if (y1 == y2) {    			
	    				if (m1 < m2) {
	        				Trip temp = arr.get(j);
	        				arr.set(j, arr.get(j-1));    				
	        				arr.set(j-1, temp);    	
	        				check = true;    					
	    				} else {
	    					if (m1 == m2) {
	    						if (d1 < d2) {
	    	        				Trip temp = arr.get(j);
	    	        				arr.set(j, arr.get(j-1));    				
	    	        				arr.set(j-1, temp);    	
	    	        				check = true;    						    							
	    						}
	    					}
	    				}
    				}
    			}
    			j--;
    		}      		
    	}
    	return arr;
    }
    
    public ArrayList<Trip> sortPrice(ArrayList<Trip> arr) {
        int l = arr.size();
        for (int i=1;i<l;i++) {
            int x = arr.get(i).getPrice();
            int j = i-1;
            while (0<=j && x<arr.get(j).getPrice()) {
                Trip tmp = arr.get(j+1);
                arr.set(j+1,arr.get(j));
                arr.set(j,tmp);
                j--;
            }

        }
        return arr;
    }

    public ArrayList<Trip> sortDuration(ArrayList<Trip> arr) {
        Collections.sort(arr, new Comparator<Trip>() {
            @Override
            public int compare(Trip o1, Trip o2) {
            return (int)o1.getDuration() - (int)o2.getDuration();
            }
        });
        return arr;
    }

    public ArrayList<Trip> sortLanguage(ArrayList<Trip> arr) {
        Collections.sort(arr, new Comparator<Trip>() {
            @Override
            public int compare(Trip o1, Trip o2) {
            return o1.getLanguages()[0].compareTo(o2.getLanguages()[0]);
            }
        });
        return arr;
    }

    public void addTrip(Trip t) {
        
    }
    public static void main(String[] args ) {
        
    }
}