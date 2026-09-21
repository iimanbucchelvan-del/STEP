public class Practice_Q2_Cross_Package_Inheritance_Reach {
    static String classifyAccess(String fieldModifier, String accessorContext) {
        if ("public".equals(fieldModifier))
            return "ALLOWED";

        if ("private".equals(fieldModifier))
            return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";

        if ("default".equals(fieldModifier))
            return ("SAME_CLASS".equals(accessorContext) ||
                    "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";

        if ("protected".equals(fieldModifier))
            return ("SAME_CLASS".equals(accessorContext) ||
                    "SAME_PACKAGE".equals(accessorContext) ||
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext))
                    ? "ALLOWED" : "DENIED";

        return "DENIED";
    }

    static String describeContext(String accessorContext) {
        String[] words = accessorContext.toLowerCase().split("_");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.isEmpty()) continue;
            if (result.length() > 0) result.append(" ");
            result.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess(
                "protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(classifyAccess(
                "protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
        System.out.println(describeContext(
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}
