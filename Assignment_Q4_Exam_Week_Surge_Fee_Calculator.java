public class Assignment_Q4_Exam_Week_Surge_Fee_Calculator {
    static final class SurgeFeeCalculator {
        private final double minimumSurgePercent;

        public SurgeFeeCalculator(double minimumSurgePercent) {
            this.minimumSurgePercent = minimumSurgePercent;
        }

        final double calculateSurgeFee(double orderValue, int delayMinutes) {
            if (orderValue < 0 || delayMinutes < 0)
                throw new IllegalArgumentException("Negative values are not allowed");

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

    public static void main(String[] args) {
        SurgeFeeCalculator calc = new SurgeFeeCalculator(1.0);

        System.out.println("Rs " + calc.calculateSurgeFee(500, 0));
        System.out.println("Rs " + calc.calculateSurgeFee(500, 1));
        System.out.println("Rs " + calc.calculateSurgeFee(500, 16));
    }
}
