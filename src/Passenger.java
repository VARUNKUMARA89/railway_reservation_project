public class Passenger {
    static int id = 1;
    int passengerId;
    String name;
    int age;
    String berthPreference;
    int seatNumber;
    String allocatedBerth;
    public Passenger(String name, int age, String berthPreference){
        this.name = name;
        this.age = age;
        this.berthPreference = berthPreference;
        passengerId = id++;
        seatNumber = -1;
        allocatedBerth = "";
    }
}
