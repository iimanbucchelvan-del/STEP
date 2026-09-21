public class Assignment_Q4_LibraryMember_JavaBean_WriteOnce {
    static class LibraryMember {
        private String membershipId;
        private String name;
        private boolean premiumMember;
        private String branchCode;
        private double finesOwed;
        private String securityAnswerHash;

        public LibraryMember() {
            this(null, null);
        }

        public LibraryMember(String name) {
            this(null, name);
        }

        public LibraryMember(String membershipId, String name) {
            this.membershipId = membershipId;
            this.name = name;
        }

        public String getMembershipId() {
            return membershipId;
        }

        public void setMembershipId(String id) {
            if (membershipId == null)
                membershipId = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isPremiumMember() {
            return premiumMember;
        }

        public void setPremiumMember(boolean premium) {
            this.premiumMember = premium;
        }

        public String getBranchCode() {
            return branchCode;
        }

        public void setBranchCode(String branchCode) {
            this.branchCode = branchCode;
        }

        public double getFinesOwed() {
            return finesOwed;
        }

        public void setFinesOwed(double finesOwed) {
            this.finesOwed = finesOwed;
        }

        public void setSecurityAnswer(String answer) {
            if (answer == null) {
                securityAnswerHash = null;
                return;
            }
            securityAnswerHash = Integer.toHexString(answer.hashCode());
        }
    }

    public static void main(String[] args) {
        LibraryMember m = new LibraryMember();
        m.setMembershipId("LIB-8841");
        m.setMembershipId("FAKE-0000");
        System.out.println(m.getMembershipId());
    }
}
