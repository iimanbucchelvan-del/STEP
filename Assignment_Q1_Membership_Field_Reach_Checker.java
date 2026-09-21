public class Assignment_Q1_Membership_Field_Reach_Checker {
    static String classifyAccess(String fieldModifier, String accessorContext) {
        if ("public".equals(fieldModifier))
            return "ALLOWED";
        if ("private".equals(fieldModifier))
            return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
        if ("default".equals(fieldModifier))
            return "SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)
                    ? "ALLOWED" : "DENIED";
        if ("protected".equals(fieldModifier))
            return "SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)
                    ? "ALLOWED" : "DENIED";
        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < modifiers.length; i++) {
            int allowed = 0, denied = 0;

            for (String[] attempt : attempts) {
                if (modifiers[i].equals(attempt[0])) {
                    if ("ALLOWED".equals(classifyAccess(attempt[0], attempt[1])))
                        allowed++;
                    else
                        denied++;
                }
            }

            if (i > 0) result.append(" | ");
            result.append(modifiers[i]).append(": ")
                  .append(allowed).append(" allowed / ")
                  .append(denied).append(" denied");
        }
        return result.toString();
    }

    static class LibraryMember {
        private final String membershipId;
        private final String branchCode;
        private final double finesOwed;
        private final String displayName;

        public LibraryMember(String membershipId, String branchCode,
                             double finesOwed, String displayName) {
            String id = membershipId == null ? "" : membershipId.trim();
            if (id.isEmpty() || id.length() < 4)
                throw new IllegalArgumentException("construction rejected");

            this.membershipId = id;
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
        }
    }

    public static void main(String[] args) {
        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeByModifier(attempts));

        try {
            new LibraryMember("LB9", "BR1", 0, "Priya Nair");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
    }
}
