
public class Booking {
	private String name;
	private String date;
	private Trip trip;
	private int passengers;
	
	public Booking(String name, String date, Trip trip, int passengers) {
		this.name = name;
		this.date = date;
		this.trip = trip;
		this.passengers = passengers;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Trip getTrip() {
		return trip;		
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	public int getPassengers() {
		return passengers;
	}
	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}
	public String toString() {
		String ananas = "Your trip, " + trip.getDescription() + " is booked for " + getPassengers();
		return ananas;
	}
}
