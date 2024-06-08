import java.util.*;

public class BookingTicket {
    private static int BerthLimit = 2;
    private static int racLimit = 1;
    private static int waitingListLimit = 1;

    private static int upperSeatNumber = 1;
    private static int middleSeatNumber = 2;
    private static int lowerSeatNumber = 3;

    static ArrayList<Passenger> confirmedList = new ArrayList<Passenger>();

    static ArrayList<Passenger> upperList = new ArrayList<Passenger>();
    static ArrayList<Passenger> middleList = new ArrayList<Passenger>();
    static ArrayList<Passenger> lowerList = new ArrayList<Passenger>();

    static Queue<Passenger> racQueue = new LinkedList<Passenger>();
    static Queue<Passenger> waitingQueue = new LinkedList<Passenger>();

    public static void bookTicket(Passenger p) {
        if (checkavailability(p)) {
            System.out.println("Ticket Booked");
        } else {
            if (racQueue.size() < racLimit) {
                racBooking(p);
                System.out.println("Out of ticket move to RAC");
            } else {
                if (waitingQueue.size() < waitingListLimit) {
                    waitingList(p);
                    System.out.println("You are in waiting List");
                } else {
                    System.out.println("Failed!!! Ticket is not available");
                }
            }
        }
    }

    public static boolean checkavailability(Passenger p) {
        if (p.getPreference() == 'U') {
            if (upperList.size() < BerthLimit) {
                upperList.add(p);
                confirmedList.add(p);
                p.setSeatNumber(upperSeatNumber++);
                return true;
            } else {
                return false;
            }
        } else if (p.getPreference() == 'M') {
            if (middleList.size() < BerthLimit) {
                middleList.add(p);
                confirmedList.add(p);
                p.setSeatNumber(middleSeatNumber++);
                return true;
            } else {
                return false;
            }
        } else if (p.getPreference() == 'L') {
            if (lowerList.size() < BerthLimit) {
                lowerList.add(p);
                confirmedList.add(p);
                p.setSeatNumber(lowerSeatNumber++);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static void racBooking(Passenger p) {
        racQueue.add(p);
        p.setSeatNumber(0);
    }

    public static void waitingList(Passenger p) {
        waitingQueue.add(p);
        p.setSeatNumber(0);
    }

    public static void confirmedTicket() {
        System.out.println("-------------------------------------------");
        Iterator it = confirmedList.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            System.out.println("------------------------------------------------");
        }
    }

    public static void racTicket() {
        System.out.println("---------------------------------------------");
        Iterator it = racQueue.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            System.out.println("------------------------------------------------");
        }
    }

    public static void waitingTicket() {
        System.out.println("---------------------------------------------------");
        Iterator it = waitingQueue.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            System.out.println("--------------------------------------------------");
        }
    }

    public static void cancelTicket(int id) {
        HashMap<String, Integer> cancelMem = findCategory(confirmedList, racQueue, waitingQueue, id);
        if (cancelMem.keySet().contains("confirm")) {
            Passenger p = findConfirm(confirmedList, id);
            int seatno = p.getSeatNumber();
            char seatPreference = p.getPreference();
            confirmedList.remove(p);
            if (racQueue != null) {
                Passenger rac = racQueue.poll();
                rac.setSeatNumber(seatno);
                rac.setPreference(seatPreference);
                confirmedList.add(rac);
                racQueue.add(waitingQueue.poll());
            }
        } else if (cancelMem.keySet().contains("rac")) {
            Passenger p = findRac(racQueue, id);
            racQueue.remove(p);
            racQueue.add(waitingQueue.poll());
        } else {
            Passenger p = findWaiting(waitingQueue, id);
            waitingQueue.remove(p);
        }

    }

    public static HashMap<String, Integer> findCategory(ArrayList<Passenger> confirmedList,
            Queue<Passenger> racQueue,
            Queue<Passenger> waitingQueue, int id) {

        HashMap<String, Integer> cancelMem = new HashMap<String, Integer>();

        for (Passenger rac : racQueue) {
            if (rac.getId() == id) {
                cancelMem.put("rac", id);
                return cancelMem;
            }
        }

        for (Passenger waiting : waitingQueue) {
            if (waiting.getId() == id) {
                cancelMem.put("waiting", id);
                return cancelMem;
            }
        }

        for (Passenger confirm : confirmedList) {
            if (confirm.getId() == id) {
                cancelMem.put("confirm", id);
                return cancelMem;
            }
        }
        return null;
    }

    public static Passenger findConfirm(ArrayList<Passenger> confirmList, int id) {
        for (Passenger p : confirmedList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public static Passenger findRac(Queue<Passenger> racList, int id) {
        for (Passenger p : racList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public static Passenger findWaiting(Queue<Passenger> waitingList, int id) {
        for (Passenger p : waitingList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

}