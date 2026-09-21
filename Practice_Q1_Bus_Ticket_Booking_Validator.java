import java.util.*;

public class Practice_Q1_Bus_Ticket_Booking_Validator {
    static class BusTicket {
        private final String passengerName, destination;
        private boolean checkedIn;

        public BusTicket(String passengerName, String destination) {
            if (!isValidName(passengerName))
                throw new IllegalArgumentException("Invalid passenger name");
            if (!isValidDestination(destination))
                throw new IllegalArgumentException("Invalid destination");

            this.passengerName = passengerName.trim();
            this.destination = destination.trim();
        }

        private boolean isValidName(String s) {
            if (s == null || s.trim().isEmpty())
                return false;
            for (char c : s.trim().toCharArray())
                if (!Character.isLetter(c) && c != ' ')
                    return false;
            return true;
        }

        private boolean isValidDestination(String s) {
            return s != null && !s.trim().isEmpty();
        }

        void markCheckedIn() {
            if (!checkedIn) {
                checkedIn = true;
                System.out.println("Checked in.");
            } else {
                System.out.println("Passenger already checked in.");
            }
        }
    }

    static void processBatch(String[][] rawBookings) {
        Set<String> accepted = new HashSet<>();
        int valid = 0, rejected = 0, duplicates = 0;

        for (String[] b : rawBookings) {
            try {
                BusTicket ticket = new BusTicket(b[0], b[1]);
                String key = ticket.passengerName.toLowerCase() + "|" +
                             ticket.destination.toLowerCase();

                if (!accepted.add(key)) {
                    duplicates++;
                } else {
                    valid++;
                }
            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid +
                           " | Rejected: " + rejected +
                           " | Duplicates skipped: " + duplicates);
    }

    public static void main(String[] args) {
        String[][] bookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };
        processBatch(bookings);
    }
}
