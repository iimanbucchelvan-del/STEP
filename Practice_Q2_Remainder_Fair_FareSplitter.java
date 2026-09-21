import java.util.Arrays;

public class Practice_Q2_Remainder_Fair_FareSplitter {
    static class FareSplitter {
        private String tripId;
        private double totalFare;
        private int passengerCount;

        public FareSplitter(String tripId, double totalFare, int passengerCount) {
            if (totalFare < 0 || passengerCount <= 0)
                throw new IllegalArgumentException("Invalid fare or passenger count");

            this.tripId = tripId;
            this.totalFare = totalFare;
            this.passengerCount = passengerCount;
        }

        public FareSplitter(String tripId, double totalFare) {
            this(tripId, totalFare, 1);
        }

        public FareSplitter(String tripId) {
            this(tripId, 0.0, 1);
        }

        double[] fareBreakdown() {
            if (passengerCount <= 0)
                return new double[0];

            if (totalFare == 0)
                return new double[passengerCount];

            long totalPaise = Math.round(totalFare * 100);
            long base = totalPaise / passengerCount;
            long remainder = totalPaise % passengerCount;

            double[] result = new double[passengerCount];

            for (int i = 0; i < passengerCount; i++) {
                long share = base;
                if (i == passengerCount - 1)
                    share += remainder;
                result[i] = share / 100.0;
            }
            return result;
        }

        boolean isConfirmationOverdue(int confirmed, int expected) {
            return confirmed < expected;
        }
    }

    public static void main(String[] args) {
        FareSplitter f = new FareSplitter("TRIP001", 100000, 3);
        System.out.println(Arrays.toString(f.fareBreakdown()));

        FareSplitter provisional = new FareSplitter("TRIP003");
        System.out.println(Arrays.toString(provisional.fareBreakdown()));
    }
}
