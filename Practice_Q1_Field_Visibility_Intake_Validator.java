public class Practice_Q1_Field_Visibility_Intake_Validator {
    static String classifyAccess(String fieldModifier, String accessorContext) {
        if ("public".equals(fieldModifier))
            return "ALLOWED";
        if ("private".equals(fieldModifier))
            return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
        if ("default".equals(fieldModifier) || "protected".equals(fieldModifier))
            return ("SAME_CLASS".equals(accessorContext) ||
                    "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";
        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {
        int allowed = 0, denied = 0;

        for (String[] attempt : attempts) {
            if ("ALLOWED".equals(classifyAccess(attempt[0], attempt[1])))
                allowed++;
            else
                denied++;
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    static class PatientRecord {
        private String patientId;
        private String wardCode;
        private double vitalsScore;
        private String facilityName;

        public PatientRecord(String patientId, String wardCode,
                             double vitalsScore, String facilityName) {
            String id = patientId == null ? "" : patientId.trim();

            if (id.isEmpty() || id.length() < 4)
                throw new IllegalArgumentException("construction rejected");

            this.patientId = id;
            this.wardCode = wardCode;
            this.vitalsScore = vitalsScore;
            this.facilityName = facilityName;
        }
    }

    public static void main(String[] args) {
        String[][] attempts = {
            {"protected", "SAME_PACKAGE"},
            {"protected", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeBatch(attempts));
    }
}
