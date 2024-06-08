import java.util.*;

class Main {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("-----------------------------------------------");
            System.out.println(
                    "Train Ticket Reservation:\n1. Book Ticket\n2. Cancel Ticket\n3. View Confirmed Ticket\n4. View RAC Ticket\n5. View Waiting List Ticket\n6. Exit");
            int option = s.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("---------------------------------------");
                    System.out.println("Enter Your Name: ");
                    String name = s.next();
                    System.out.println("Enter Your Age: ");
                    int age = s.nextInt();
                    System.out.println("Enter Your Preference: (Upper - U, Lower - L, Middle - M)");
                    char preference = s.next().charAt(0);
                    if (preference == 'U' || preference == 'L' || preference == 'M') {
                        BookingTicket.bookTicket(new Passenger(name, age, preference));
                    } else {
                        System.out.println("Invalid Preference");
                    }
                    break;
                }
                case 2: {
                    System.out.println("-------------------------------------------");
                    System.out.println("Enter Ticket ID:");
                    int id = s.nextInt();
                    BookingTicket.cancelTicket(id);
                    System.out.println("Ticket Cancelled Successfully");
                    break;
                }
                case 3: {
                    BookingTicket.confirmedTicket();
                    break;
                }
                case 4: {
                    BookingTicket.racTicket();
                    break;
                }
                case 5: {
                    BookingTicket.waitingTicket();
                    break;
                }
                case 6: {
                    System.out.println("Thankyou...");
                    System.exit(0);
                }
                default: {
                    System.out.println("Invalid Option");
                    break;
                }
            }

        }
    }
}