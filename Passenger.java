package railwayTicketReservationSystem;

public class Passenger {
	
	static int id = 1;
	
	String name ;
	int age;
	int passengerId;
	String berthPreference;
	String alloted;
	int berthPosition;
	
	Passenger(String name,int age,String berthPreference){
		this.name = name;
		this.age = age;
		this.passengerId = id++;
		this.berthPreference = berthPreference;
		this.berthPosition = -1; // seat position is not alloted
		this.alloted = null; // seat not alloted
	}
	
	@Override
	public String toString() {
		return "|ID:"+this.passengerId+"|Name:"+ this.name+"|Age:"+this.age +"|BerthPreference:"+this.berthPreference+"|Alloted:"+this.alloted+"|BerthPosition:"+this.berthPosition+"|";
	}
	

}
