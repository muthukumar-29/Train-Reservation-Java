public class Passenger {
    private static int idProvider = 0;
    private int id,age;
    private String name;
    private char preference;
    private String ticketType;
    private int seatNumber;

    public Passenger(String name, int age, char preference){
        this.id = ++idProvider;
        this.name = name;
        this.age = age;
        this.preference = preference;
    }

    public void setId(int id){
        Passenger.idProvider = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setPreference(char preference){
        this.preference = preference;
    }

    public void setTicketType(String ticketType){
        this.ticketType = ticketType;
    }

    public void setSeatNumber(int seatNumber){
        this.seatNumber = seatNumber;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public char getPreference(){
        return preference;
    }

    public String getTicketType(){
        return ticketType;
    }

    public int getSeatNumber(){
        return seatNumber;
    }

    public String toString(){
        return "Passenger Ticket ID: "+id+"\nPassenger Name: "+name+"\nPassenger Age: "+age+"\nPassenger Seat No.: "+seatNumber+"\nPassenger Preference:"+preference;
    }
}
