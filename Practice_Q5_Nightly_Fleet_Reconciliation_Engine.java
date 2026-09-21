public class Practice_Q5_Nightly_Fleet_Reconciliation_Engine {
    static class BusTicketAccount {
        static double minimumPenaltyPercent;

        static {
            minimumPenaltyPercent = 1.0;
        }

        protected String bookingId;
        protected double ticketFare;

        public BusTicketAccount(String bookingId, double ticketFare) {
            this.bookingId = bookingId;
            this.ticketFare = ticketFare;
        }

        public BusTicketAccount(String bookingId) {
            this(bookingId, 0.0);
        }

        final double calculatePenalty(int minutesLate) {
            if (ticketFare < 0 || minutesLate < 0)
                throw new IllegalArgumentException("Invalid values");

            if (minutesLate == 0)
                return 0.0;

            int first = Math.min(minutesLate, 5);
            int second = Math.min(Math.max(minutesLate - 5, 0), 10);
            int third = Math.max(minutesLate - 15, 0);

            double tiered = ticketFare * (first * 0.005 +
                                          second * 0.01 +
                                          third * 0.02);

            double floor = ticketFare * minimumPenaltyPercent / 100.0;
            return Math.max(tiered, floor);
        }
    }

    static class Sleeper extends BusTicketAccount {
        public Sleeper(String bookingId, double ticketFare) {
            super(bookingId, ticketFare);
        }

        public Sleeper(String bookingId) {
            super(bookingId);
        }

        double sleeperPenalty(int minutesLate) {
            return super.calculatePenalty(minutesLate) * 0.5;
        }
    }

    static void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        account.ticketFare = amount;
        double penalty;

        if (account instanceof Sleeper)
            penalty = ((Sleeper) account).sleeperPenalty(minutesLate);
        else
            penalty = account.calculatePenalty(minutesLate);

        System.out.println(account.bookingId + " penalty = " + penalty);
    }

    static void processBatch(BusTicketAccount[] accounts, double[] amounts,
                             int[] minutesLateArray) {
        int n = Math.min(accounts.length, Math.min(amounts.length, minutesLateArray.length));
        int processed = 0, nullSkipped = 0, sleeper = 0, regular = 0;
        double total = 0;

        for (int i = 0; i < n; i++) {
            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            accounts[i].ticketFare = amounts[i];
            double penalty;

            if (accounts[i] instanceof Sleeper) {
                penalty = ((Sleeper) accounts[i]).sleeperPenalty(minutesLateArray[i]);
                sleeper++;
            } else {
                penalty = accounts[i].calculatePenalty(minutesLateArray[i]);
                regular++;
            }

            total += penalty;
            processed++;
        }

        System.out.println(processed + " processed | " + nullSkipped +
                           " null skipped | " + sleeper + " sleeper | " +
                           regular + " regular | grand total penalties = " + total);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new Sleeper("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };
        double[] amounts = {1200, 900, 700};
        int[] delays = {10, 5, 0};

        processBatch(accounts, amounts, delays);
    }
}
