import java.util.Scanner;

public class Assignment_Q4_Library_ISBN_Normalizer_Validator {
    static String normalizeCode(String raw) {
        raw = raw.trim();
        if (raw.length() < 3)
            return raw;

        return raw.substring(0, 3).toUpperCase() + raw.substring(3);
    }

    static String validateAndFormat(String code) {
        if (code.length() != 13)
            return "Invalid: wrong length";

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i)))
                return "Invalid: publisher code must be 3 letters";
        }

        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i)))
                return "Invalid: body must contain only digits";
        }

        StringBuilder result = new StringBuilder();
        result.append("[")
              .append(code.substring(0, 3))
              .append("] YEAR: ")
              .append(code.substring(3, 7))
              .append(" | CATALOG: ")
              .append(code.substring(7));

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String code = normalizeCode(sc.nextLine());
        System.out.println(validateAndFormat(code));
    }
}
