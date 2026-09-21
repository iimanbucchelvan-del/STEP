public class Assignment_Q5_Nightly_Multi_Kitchen_Reconciliation {
    static class DeliveryAccount {
        static double minimumSurgePercent;

        static {
            minimumSurgePercent = 1.0;
        }

        protected String studentId;
        protected double orderValue;

        public DeliveryAccount(String studentId, double orderValue) {
            this.studentId = studentId;
            this.orderValue = orderValue;
        }

        public DeliveryAccount(String studentId) {
            this(studentId, 0.0);
        }

        final double calculateSurgeFee(int delayMinutes) {
            if (delayMinutes < 0 || orderValue < 0)
                throw new IllegalArgumentException("Invalid values");

            if (delayMinutes == 0)
                return 0.0;

            int first = Math.min(delayMinutes, 5);
            int second = Math.min(Math.max(delayMinutes - 5, 0), 10);
            int third = Math.max(delayMinutes - 15, 0);

            double tiered = orderValue * (first * 0.005 +
                                          second * 0.01 +
                                          third * 0.02);

            double floor = orderValue * minimumSurgePercent / 100.0;
            return Math.max(tiered, floor);
        }
    }

    static class Premium extends DeliveryAccount {
        public Premium(String studentId, double orderValue) {
            super(studentId, orderValue);
        }

        public Premium(String studentId) {
            super(studentId);
        }

        @Override
        final double calculateSurgeFee(int delayMinutes) {
            return super.calculateSurgeFee(delayMinutes) * 0.5;
        }
    }

    static void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        account.orderValue = amount;
        double fee = account.calculateSurgeFee(delayMinutes);
        System.out.println(account.studentId + " surge fee = " + fee);
    }

    static void processBatch(DeliveryAccount[] accounts, double[] amounts,
                             int[] delayMinutesArray) {
        int n = Math.min(accounts.length, Math.min(amounts.length, delayMinutesArray.length));
        int processed = 0, nullSkipped = 0, premium = 0, regular = 0;
        double total = 0;

        for (int i = 0; i < n; i++) {
            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            accounts[i].orderValue = amounts[i];
            double fee = accounts[i].calculateSurgeFee(delayMinutesArray[i]);
            total += fee;
            processed++;

            if (accounts[i] instanceof Premium)
                premium++;
            else
                regular++;
        }

        System.out.println(processed + " processed | " + nullSkipped +
                           " null skipped | " + premium + " premium | " +
                           regular + " regular | grand total surge fees = " + total);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new Premium("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };
        double[] amounts = {500, 400, 300};
        int[] delays = {10, 5, 0};

        processBatch(accounts, amounts, delays);
    }
}
