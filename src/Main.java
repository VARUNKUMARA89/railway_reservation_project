import java.util.*;

public class Main {
    public static void bookTicket(Passenger p){
        TicketBooker booker = new TicketBooker();
        if(booker.availableWaitList == 0 && booker.availableLowerBerth == 0 && booker.availableUpperBerth == 0
                && booker.availableMiddleBerth == 0 && booker.availableRAC == 0){
            System.out.println("No Available Tickets");
            return;
        }
        if(p.berthPreference.equals("L") && booker.availableLowerBerth > 0 ||
                p.berthPreference.equals("M") && booker.availableMiddleBerth > 0 ||
                p.berthPreference.equals("U") && booker.availableUpperBerth > 0){
            System.out.println("Preferred Berth Available!!!");
            if(p.berthPreference.equals("L")){
                System.out.println("Lower Berth Given");
                booker.book(p, booker.lowerBerthPositions.get(0), "L");
                booker.lowerBerthPositions.remove(0);
                booker.availableLowerBerth--;
            }else if(p.berthPreference.equals("M")){
                System.out.println("Middle Berth Given");
                booker.book(p, booker.middleBerthPositions.get(0), "M");
                booker.middleBerthPositions.remove(0);
                booker.availableMiddleBerth--;
            }else if(p.berthPreference.equals("U")){
                System.out.println("Upper Berth Given");
                booker.book(p, booker.upperBerthPositions.get(0), "U");
                booker.upperBerthPositions.remove(0);
                booker.availableUpperBerth--;
            }
        }else if(booker.availableLowerBerth > 0){
            System.out.println("Preferred Berth not Available\nGiven Lower Berth");
            booker.book(p, booker.lowerBerthPositions.get(0), "L");
            booker.lowerBerthPositions.remove(0);
            booker.availableLowerBerth--;
        }else if(booker.availableMiddleBerth > 0){
            System.out.println("Preferred Berth not Available\nGiven Middle Berth");
            booker.book(p, booker.middleBerthPositions.get(0), "M");
            booker.middleBerthPositions.remove(0);
            booker.availableMiddleBerth--;
        }else if(booker.availableUpperBerth > 0){
            System.out.println("Preferred Berth not Available\nGiven Upper Berth");
            booker.book(p, booker.upperBerthPositions.get(0), "U");
            booker.upperBerthPositions.remove(0);
            booker.availableUpperBerth--;
        }else if(booker.availableRAC > 0){
            System.out.println("Preferred Berth not Available\nGiven RAC");
            booker.bookINRAC(p, booker.RACPositions.get(0), "RAC");
            booker.RACPositions.remove(0);
            booker.availableRAC--;
        }else if(booker.availableWaitList > 0){
            System.out.println("RAC Full\nYou are in WaitList");
            booker.bookINWaitList(p, booker.waitingListPositions.get(0), "WL");
            booker.waitingListPositions.remove(0);
            booker.availableWaitList--;
        }
    }



    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Boolean b = true;

        while(b){
            System.out.println("1. Book Ticket\n2. Cancel ticket\n3. Available Tickets\n4. Booked Tickets\n5. Exit");
            int choice = scn.nextInt();
            switch(choice){
                case 1:
                {
                    System.out.println("Enter name, age, preferred berth(L, M, U)");
                    String name = scn.next();
                    int age = scn.nextInt();
                    String berthPrefernce = scn.next();
                    Passenger p = new Passenger(name, age, berthPrefernce);
                    bookTicket(p);
                }
                break;
                case 2:
                {
                    System.out.println("Enter the passenger ID");
                    int id = scn.nextInt();
                    TicketBooker booker = new TicketBooker();
                    booker.cancelTicket(id);
                }
                break;
                case 3:
                {
                    TicketBooker booker = new TicketBooker();
                    booker.viewAvailableTickets();
                }
                break;
                case 4:
                {
                    TicketBooker booker = new TicketBooker();
                    booker.viewBookedTickets();
                }
                break;
                case 5:
                {
                    System.out.println("Thank you !!!");
                    b = false;
                }
                break;
            }
        }
    }
}