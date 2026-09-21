import java.util.Scanner;

public class Practice_Q4_Masked_Phone_Number_Formatter {
    static String maskPhoneNumber(String phone) {
        if (phone.length() != 10)
            return "Invalid phone number";

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i)))
                return "Invalid phone number";
        }

        StringBuilder result = new StringBuilder("XXXXXX");
        result.append("-").append(phone.substring(6));

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(maskPhoneNumber(sc.nextLine()));
    }
}
