public class Assignment_Q5_Immutable_Loan_Receipt_Ledger {
    static class LoanReceipt {
        private static String systemLabel;

        static {
            systemLabel = "NIGHTLY-CIRCULATION";
        }

        protected final String memberId;
        protected final String[] bookIds;

        public LoanReceipt(String memberId, String[] bookIds) {
            if (bookIds == null)
                throw new IllegalArgumentException("construction rejected");

            String[] copy = bookIds.clone();
            for (String id : copy) {
                if (!isValidBookId(id))
                    throw new IllegalArgumentException("construction rejected");
            }

            this.memberId = memberId;
            this.bookIds = copy;
        }

        private static boolean isValidBookId(String id) {
            if (id == null || id.length() != 6 ||
                !id.startsWith("BK-"))
                return false;

            return Character.isDigit(id.charAt(3)) &&
                   Character.isDigit(id.charAt(4)) &&
                   Character.isDigit(id.charAt(5));
        }

        public String[] getBookIds() {
            return bookIds.clone();
        }

        public LoanReceipt withCorrectedBookId(int index, String newId) {
            if (index < 0 || index >= bookIds.length || !isValidBookId(newId))
                throw new IllegalArgumentException("Invalid book ID");

            String[] copy = bookIds.clone();
            copy[index] = newId;
            return new LoanReceipt(memberId, copy);
        }
    }

    static class ReferenceOnlyLoanReceipt extends LoanReceipt {
        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds,
                                        String roomNumber) {
            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }
    }

    static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0, nullSkipped = 0, referenceOnly = 0, regular = 0;

        if (receipts != null) {
            for (LoanReceipt receipt : receipts) {
                if (receipt == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (receipt instanceof ReferenceOnlyLoanReceipt)
                        referenceOnly++;
                    else
                        regular++;
                }
            }
        }

        return processed + " processed | " + nullSkipped +
               " null skipped | " + referenceOnly +
               " reference-only | " + regular + " regular";
    }

    public static void main(String[] args) {
        LoanReceipt[] batch = {
            new ReferenceOnlyLoanReceipt("LIB-001",
                    new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };

        System.out.println(processNightlyCirculation(batch));
    }
}
