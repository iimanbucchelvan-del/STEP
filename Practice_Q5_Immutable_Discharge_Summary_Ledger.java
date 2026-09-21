public class Practice_Q5_Immutable_Discharge_Summary_Ledger {
    static class DischargeSummary {
        private static String ledgerLabel;

        static {
            ledgerLabel = "NIGHTLY-DISCHARGE";
        }

        protected final String patientId;
        protected final String[] medicationCodes;

        public DischargeSummary(String patientId, String[] medicationCodes) {
            if (medicationCodes == null)
                throw new IllegalArgumentException("construction rejected");

            String[] copy = medicationCodes.clone();

            for (String code : copy) {
                if (!isValidMedicationCode(code))
                    throw new IllegalArgumentException("construction rejected");
            }

            this.patientId = patientId;
            this.medicationCodes = copy;
        }

        private static boolean isValidMedicationCode(String code) {
            return code != null &&
                   code.matches("MED-[A-Z]");
        }

        public String[] getMedicationCodes() {
            return medicationCodes.clone();
        }

        public DischargeSummary withCorrectedMedication(int index, String newCode) {
            if (index < 0 || index >= medicationCodes.length ||
                !isValidMedicationCode(newCode))
                throw new IllegalArgumentException("Invalid medication code");

            String[] copy = medicationCodes.clone();
            copy[index] = newCode;

            return new DischargeSummary(patientId, copy);
        }
    }

    static class CriticalCareDischargeSummary extends DischargeSummary {
        private final int icuDays;

        public CriticalCareDischargeSummary(String patientId,
                                            String[] medicationCodes,
                                            int icuDays) {
            super(patientId, medicationCodes);
            this.icuDays = icuDays;
        }
    }

    static String processNightlyBatch(DischargeSummary[] summaries) {
        int processed = 0, nullSkipped = 0, critical = 0, routine = 0;

        if (summaries != null) {
            for (DischargeSummary summary : summaries) {
                if (summary == null) {
                    nullSkipped++;
                } else {
                    processed++;

                    if (summary instanceof CriticalCareDischargeSummary)
                        critical++;
                    else
                        routine++;
                }
            }
        }

        return processed + " processed | " + nullSkipped +
               " null skipped | " + critical +
               " critical-care | " + routine + " routine";
    }

    public static void main(String[] args) {
        DischargeSummary[] batch = {
            new CriticalCareDischargeSummary(
                    "MT001", new String[]{"MED-X"}, 4),
            null,
            new DischargeSummary("MT002", new String[]{"MED-Y"})
        };

        System.out.println(processNightlyBatch(batch));
    }
}
