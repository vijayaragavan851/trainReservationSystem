package railwayTicketReservationSystem;
import java.util.*;

public class Main {
	
	
	public static void bookTicket(Passenger passenger) {
		
		TicketBooker booker = new TicketBooker();
		// if no Waiting List is available ,then all the seats are filled
		if(TicketBooker.noOfWL == 0) {
			System.out.println("No Ticket Available");
			return;
		}
		
		//check for preference seat 
		
		if((passenger.berthPreference.equals("L") && TicketBooker.noOfLowerBerth>0)||
		   (passenger.berthPreference.equals("M") && TicketBooker.noOfLowerBerth>0)||
		   (passenger.berthPreference.equals("U") && TicketBooker.noOfLowerBerth>0)){
			System.out.println("Prefered berth is Available");
			if(passenger.berthPreference.equals("L")) {
				System.out.println("Lower berth Given");
				booker.bookTicket(passenger,TicketBooker.lowerBerthPosition.get(0),"L");
				
				TicketBooker.lowerBerthPosition.remove(0);
				TicketBooker.noOfLowerBerth--;
				
			}
			
			else if(passenger.berthPreference.equals("M")) {
				System.out.println("Middle berth Given");
				booker.bookTicket(passenger,TicketBooker.lowerBerthPosition.get(0),"M");
				
				TicketBooker.middleBerthPosition.remove(0);
				TicketBooker.noOfMiddleBerth--;
				
			}
			
			else if(passenger.berthPreference.equals("U")) {
				System.out.println("Upper berth Given");
				booker.bookTicket(passenger,TicketBooker.lowerBerthPosition.get(0),"U");
				
				TicketBooker.upperBerthPosition.remove(0);
				TicketBooker.noOfUpperBerth--;
				
			}
			
		}
		
		else if(TicketBooker.noOfLowerBerth>0){
			System.out.println("Lower berth Given");
			booker.bookTicket(passenger,TicketBooker.lowerBerthPosition.get(0),"L");
			
			TicketBooker.lowerBerthPosition.remove(0);
			TicketBooker.noOfLowerBerth--;
		}
		
		
		else if(TicketBooker.noOfMiddleBerth>0) {
			System.out.println("Middle berth Given");
			booker.bookTicket(passenger,TicketBooker.middleBerthPosition.get(0),"M");
			
			TicketBooker.middleBerthPosition.remove(0);
			TicketBooker.noOfMiddleBerth--;
			
		}
		
		else if(TicketBooker.noOfUpperBerth>0) {
			System.out.println("Upper berth Given");
			booker.bookTicket(passenger,TicketBooker.upperBerthPosition.get(0),"U");
			
			TicketBooker.upperBerthPosition.remove(0);
			TicketBooker.noOfUpperBerth--;
			
		}
		
		else if(TicketBooker.noOfRAC>0) {
			System.out.println("RAC Ticket Available");
			booker.bookToRAC(passenger,TicketBooker.RacPosition.get(0),"RAC");
			
			TicketBooker.RacPosition.remove(0);
			TicketBooker.noOfRAC--;
			
		}
		
		else if(TicketBooker.noOfWL>0) {
			System.out.println("Waiting List Ticket Available");
			booker.bookToWL(passenger,TicketBooker.WlPosition.get(0),"WL");
			
			TicketBooker.WlPosition.remove(0);
			TicketBooker.noOfWL--;
			
		}
		
		
		
		
	}
	
	private static void cancelTicket(int id) {
	    
		TicketBooker booker = new TicketBooker();
		
		if(booker.passengers.containsKey(id)) {
			booker.cancelBooking(id);
		}else {
			System.out.println("Invalid Id");
		}
		
	}

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
	    boolean loop = true;
	    
	    while(loop) {
	    	System.out.print("1.Book Ticket\n2.Cancel Ticket\n3.Available Ticket\n4.Booked Ticket\n5.Exit");
	    	System.out.println("\n---------------------------------------");
	    	System.out.print("Enter your choice:");
	    	
	    	//getting choice
	    	int choice = scanner.nextInt();
	    	System.out.println("---------------------------------------");
	    	
	    	switch(choice) {
	    	
	    	  case 1:
	    	  {
	    		  System.out.println("Booking Ticket:");
	    		  
	    		  //getting inputs from user
	    		  System.out.println("Enter the passenger name,age & berth preference(L,M,U):");
	    		  //getting name
	    		  String name = scanner.next();
	    		  //getting age
	    		  int age = scanner.nextInt();
	    		  //getting preference
	    		  String preference = scanner.next().charAt(0)+"";
	    		  
	    		  //creating an object for passenger
	    		  Passenger passenger = new Passenger(name,age,preference);
	    		  bookTicket(passenger);
	    		  System.out.println(passenger);
	    			
	    	  }
	    	  break;
	    	  
	    	  
	    	  case 2:
	    	  {
	    		  System.out.println("Cancelling Ticket:");
	    		  System.out.println("Enter the Passenger ID:");
	    		  int id = scanner.nextInt();
	    		  cancelTicket(id);
	    	  }
	    	  break;
	    	  
	    	  case 3:
	    	  {
	    		  System.out.println("Available Tickets:");
	    		  TicketBooker booker = new TicketBooker();
	    		  booker.printAvailableSeats();
	    	  }
	    	  break;
	    	  
	    	  case 4:
	    	  {
	    		  System.out.println("Booked Tickets:");
	    		  TicketBooker booker = new TicketBooker();
	    		  booker.printPassengers();
	    	  }
	    	  break;
	    	  
	    	  case 5:
	    	  {
	    		  System.out.println("Thanks for Booking");
	    		  loop = false;
	    		  
	    	  }
	    	  break;
	    	  
	    	  default:
	    		  System.out.println("Invalid  Option.");
	    		  break;
	    	  
	    	}
	    	
	    	System.out.println("---------------------------------------");
	    	
	    	
	    }

	}

	

}
