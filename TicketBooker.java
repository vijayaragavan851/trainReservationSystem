package railwayTicketReservationSystem;

import java.util.*;

public class TicketBooker {
	
	static  List<Integer> list = new ArrayList<>();
	static {
		System.out.println("the list is initialize");
		for(int i=1;i<=23;i++) {
			list.add(i); 
		}
		
	}
	
	//no of seats 
	static int  noOfLowerBerth = 1;
	static int  noOfMiddleBerth = 1;
	static int  noOfUpperBerth = 1;
	static int  noOfRAC = 1;
	static int  noOfWL = 1;
	
	
	static List<Integer> bookedTicketList =  new ArrayList<>();//list of bookedticket passengers
	static Queue<Integer> bookedRacList = new LinkedList<>();
	static Queue<Integer> bookedWlList = new LinkedList<>();
	
	// no of position of lower berth
	static List<Integer> lowerBerthPosition = new ArrayList<>(list);
	static List<Integer> middleBerthPosition = new ArrayList<>(list);
	static List<Integer> upperBerthPosition = new ArrayList<>(list);
	static List<Integer> RacPosition = new ArrayList<>(Arrays.asList(1));
	static List<Integer> WlPosition = new ArrayList<>(Arrays.asList(1));
	
	static Map<Integer, Passenger> passengers = new HashMap<>();//map of passenger ids to passengers

	public void bookTicket(Passenger passenger, int berthPos, String alloted) {
		
		passenger.berthPosition = berthPos;
		passenger.alloted= alloted;
		
		passengers.put(passenger.passengerId, passenger);
		
		bookedTicketList.add(passenger.passengerId);
		System.out.println("--------------------------\nBooked Successfully\n--------------------------");
		
	}

	public void bookToRAC(Passenger passenger, int RacPos, String alloted ) {
		
		passenger.berthPosition = RacPos;
		passenger.alloted= alloted ;
		
		passengers.put(passenger.passengerId, passenger);
		
		bookedRacList.add(passenger.passengerId);
		System.out.println("--------------------------\nRAC Booked Successfully\n--------------------------");
		
	}
	
    public void bookToWL(Passenger passenger, int WlPos, String alloted ) {
		
		passenger.berthPosition = WlPos;
		passenger.alloted= alloted ;
		
		passengers.put(passenger.passengerId, passenger);
		
		bookedWlList.add(passenger.passengerId);
		System.out.println("--------------------------\nWaiting List booked Successfully\n--------------------------");
		
	}

	public void cancelBooking(int id) {
		
		Passenger passenger = passengers.get(id);
		passengers.remove(Integer.valueOf(id)); // passing the Object as ID
		bookedTicketList.remove(Integer.valueOf(id));
		
		int bookedPos = passenger.berthPosition;
		
		System.out.println("--------------------------\nCancelled Successfully\n--------------------------");
		
		if(passenger.alloted.equals("L")) {
			TicketBooker.noOfLowerBerth++;
			TicketBooker.lowerBerthPosition.add(bookedPos);
		}
		
		else if(passenger.alloted.equals("M")) {
			TicketBooker.noOfMiddleBerth++;
			TicketBooker.middleBerthPosition.add(bookedPos);
		}
		
		else if(passenger.alloted.equals("U")) {
			TicketBooker.noOfUpperBerth++;
			TicketBooker.upperBerthPosition.add(bookedPos);
		}
		
		
		if(bookedRacList.size()>0) {
			
			Passenger passengerFromRac = passengers.get(bookedRacList.poll());
			int racPos = passengerFromRac.berthPosition;
			
			RacPosition.add(racPos);
			noOfRAC++;
			
			if(TicketBooker.bookedWlList.size()>0) {
				
				Passenger passengerFromWaitingList = passengers.get(bookedWlList.poll());
				int waitListPos = passengerFromWaitingList.berthPosition;
				WlPosition.add(waitListPos);
				
				passengerFromWaitingList.berthPosition = RacPosition.get(0);
				passengerFromWaitingList.alloted = "RAC";
				
				RacPosition.remove(0);
				bookedRacList.add(passengerFromWaitingList.passengerId);
				
				noOfWL++;
				noOfRAC--;
				
			}
			
			Main.bookTicket(passengerFromRac);
			
		}
		
		
	}
	
	public void printAvailableSeats() {
		System.out.println("No of Lower Berth:"+ TicketBooker.noOfLowerBerth);
		System.out.println("No of Middle Berth:"+ TicketBooker.noOfMiddleBerth);
		System.out.println("No of Upper Berth:"+ TicketBooker.noOfUpperBerth);
		System.out.println("No of RAC :"+ TicketBooker.noOfRAC);
		System.out.println("No of Waiting List:"+ TicketBooker.noOfWL);
	}
	
	
	public void printPassengers() {
		
		if(passengers.size() == 0) {
			System.out.println("No Passengers details available");
		}
		
		for(Passenger passenger:passengers.values()) {
			System.out.println("|ID:"+passenger.passengerId+"|Name:"+ passenger.name+"|Age:"+passenger.age +"|BerthPreference:"+passenger.berthPreference+"|Alloted:"+passenger.alloted+"|BerthPosition:"+passenger.berthPosition+"|");
		}
		
	}
	
	
	
	


}
