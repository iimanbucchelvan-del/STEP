public class Assignment_Q3_Canteen_Trust_Score_Ranking {
    static class Canteen {
        private String canteenCode, canteenName;
        private int trustScore;

        public Canteen(String canteenCode, String canteenName, int trustScore) {
            this.canteenCode = canteenCode;
            this.canteenName = canteenName;
            this.trustScore = trustScore;
        }

        public Canteen(String canteenCode, String canteenName) {
            this(canteenCode, canteenName, 3);
        }

        int compareTo(Canteen other) {
            if (this.trustScore != other.trustScore)
                return Integer.compare(other.trustScore, this.trustScore);

            int codeCompare = this.canteenCode.compareToIgnoreCase(other.canteenCode);
            if (codeCompare != 0)
                return codeCompare;

            return Integer.compare(this.canteenName.length(), other.canteenName.length());
        }
    }

    static Canteen[] rankCanteens(Canteen[] canteens) {
        Canteen[] result = canteens.clone();

        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - 1 - i; j++) {
                if (result[j].compareTo(result[j + 1]) > 0) {
                    Canteen temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Canteen[] input = {
            new Canteen("HB3-C", "Spice Junction", 3),
            new Canteen("hb1-c", "Grand Mess", 5),
            new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked = rankCanteens(input);
        for (Canteen c : ranked)
            System.out.println(c.canteenCode);
    }
}
