public class Practice_Q4_PatientProfile_JavaBean_LockerPIN {
    static class PatientProfile {
        private String patientId;
        private String name;
        private boolean discharged;
        private String wardCode;
        private String lockerPinHash;

        public PatientProfile() {
            this(null, null);
        }

        public PatientProfile(String name) {
            this(null, name);
        }

        public PatientProfile(String patientId, String name) {
            this.patientId = patientId;
            this.name = name;
        }

        public String getPatientId() {
            return patientId;
        }

        public void setPatientId(String id) {
            if (patientId == null)
                patientId = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isDischarged() {
            return discharged;
        }

        public void setDischarged(boolean discharged) {
            this.discharged = discharged;
        }

        public String getWardCode() {
            return wardCode;
        }

        public void setWardCode(String wardCode) {
            this.wardCode = wardCode;
        }

        public void setLockerPin(String pin) {
            if (pin == null || !pin.matches("\\d{4,6}"))
                return;

            lockerPinHash = Integer.toHexString(pin.hashCode());
        }
    }

    public static void main(String[] args) {
        PatientProfile p = new PatientProfile();
        p.setPatientId("MT2026-0142");
        p.setPatientId("HACKED-0000");
        System.out.println(p.getPatientId());
    }
}
