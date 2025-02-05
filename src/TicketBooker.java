import java.util.*;


public class TicketBooker {
    static int availableUpperBerth = 1;
    static int availableMiddleBerth = 1;
    static int availableLowerBerth = 1;
    static int availableRAC = 1;
    static int availableWaitList = 1;

    static Queue<Integer> RACList = new LinkedList<>();
    static Queue<Integer> waitingList = new LinkedList<>();
    static ArrayList<Integer> bookedList = new ArrayList<>();
    static Map<Integer, Passenger> passengers = new HashMap<>();

    ArrayList<Integer> lowerBerthPositions = new ArrayList<>(Arrays.asList(1));
    ArrayList<Integer> middleBerthPositions = new ArrayList<>(Arrays.asList(1));
    ArrayList<Integer> upperBerthPositions = new ArrayList<>(Arrays.asList(1));
    ArrayList<Integer> RACPositions = new ArrayList<>(Arrays.asList(1));
    ArrayList<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(1));

    public void book(Passenger p, int seatNumber, String allocatedBerth){
        TicketBooker booker = new TicketBooker();
        p.seatNumber = seatNumber;
        p.allocatedBerth = allocatedBerth;
        booker.bookedList.add(p.passengerId);
        booker.passengers.put(p.passengerId, p);
        System.out.println("Ticket Booked Successfully!!!");
    }
    public void bookINRAC(Passenger p, int seatNumber, String allocatedBerth){
        TicketBooker booker = new TicketBooker();
        p.seatNumber = seatNumber;
        p.allocatedBerth = allocatedBerth;
        booker.RACList.add(p.passengerId);
        booker.passengers.put(p.passengerId, p);
        System.out.println("RAC Ticket Booked Successfully!!!");
    }
    public void bookINWaitList(Passenger p, int seatNumber, String allocatedBerth){
        TicketBooker booker = new TicketBooker();
        p.seatNumber = seatNumber;
        p.allocatedBerth = allocatedBerth;
        booker.waitingList.add(p.passengerId);
        booker.passengers.put(p.passengerId, p);
        System.out.println("Waiting List reservation Successful!!!");
    }
    public void cancelTicket(int id){
        Passenger p = passengers.get(id);
        passengers.remove(id);
        bookedList.remove(bookedList.indexOf(id));
        int allocatedSeatNumber = p.seatNumber;
        String allocatedBerth = p.allocatedBerth;
        System.out.println("cancelled successfully!!!");

        if(allocatedBerth.equals("L")){
            lowerBerthPositions.add(allocatedSeatNumber);
            availableLowerBerth++;
        }else if(allocatedBerth.equals("M")){
            middleBerthPositions.add(allocatedSeatNumber);
            availableMiddleBerth++;
        }else if(allocatedBerth.equals("U")){
            upperBerthPositions.add(allocatedSeatNumber);
            availableUpperBerth++;
        }
        if(availableRAC == 0){
            availableRAC++;
            int RACPassengerId = RACList.poll();
            Passenger RACPassenger = passengers.get(RACPassengerId);
            int RACSeatNumber = RACPassenger.seatNumber;
            RACPositions.add(RACSeatNumber);
            Main.bookTicket(RACPassenger);
            if(availableWaitList == 0){
                availableWaitList++;
                int WLPassengerId = waitingList.poll();
                Passenger WLPassenger = passengers.get(WLPassengerId);
                int WLSeatNumber = WLPassenger.seatNumber;
                waitingListPositions.add(WLSeatNumber);
                Main.bookTicket(WLPassenger);
            }
        }
    }
    public void viewBookedTickets(){
        if(passengers.size() == 0){
            System.out.println("No Booked Tickets");
            return;
        }
        TicketBooker booker = new TicketBooker();
        for(Passenger p: booker.passengers.values()){
            System.out.println("---------------------------------");
            System.out.println("passenger ID: " + p.passengerId);
            System.out.println("name: " + p.name);
            System.out.println("age: " + p.age);
            System.out.println("Allocated seat: " + p.seatNumber + p.allocatedBerth);
        }
    }
    public void viewAvailableTickets(){
        System.out.println("-------------------------------------------");
        System.out.println("Available Lower berth: " + availableLowerBerth);
        System.out.println("Available Middle berth: " + availableMiddleBerth);
        System.out.println("Available Upper berth: " + availableUpperBerth);
        System.out.println("Available RAC: " + availableRAC);
        System.out.println("Available Wait List: " + availableWaitList);
        System.out.println("-------------------------------------------");
    }
}
