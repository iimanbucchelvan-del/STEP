import java.util.Scanner;

public class Practice_Q1_Vowel_Consonant_Counter {
    static void countVowelsAndConsonants(String text) {
        int vowels = 0, consonants = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = Character.toLowerCase(text.charAt(i));

            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
                vowels++;
            else if (ch >= 'a' && ch <= 'z')
                consonants++;
        }

        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        countVowelsAndConsonants(sc.nextLine());
    }
}
