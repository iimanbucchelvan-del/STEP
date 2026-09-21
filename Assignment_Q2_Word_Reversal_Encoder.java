import java.util.Scanner;

public class Assignment_Q2_Word_Reversal_Encoder {
    static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            StringBuilder word = new StringBuilder(words[i]);
            result.append(word.reverse());
            if (i < words.length - 1)
                result.append(" ");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(reverseEachWord(sc.nextLine()));
    }
}
