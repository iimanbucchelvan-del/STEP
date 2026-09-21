import java.util.*;

public class Assignment_Q5_Stop_Word_Filtered_Word_Frequency {
    static void printFilteredWordFrequency(String feedback) {
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};
        HashSet<String> stop = new HashSet<>(Arrays.asList(stopWords));

        String cleaned = feedback.toLowerCase().replace(",", "").replace(".", "");
        String[] words = cleaned.split("\\s+");

        HashMap<String, Integer> freq = new HashMap<>();

        for (String word : words) {
            if (!stop.contains(word))
                freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> entries =
                new ArrayList<>(freq.entrySet());

        entries.sort((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : entries)
            System.out.println(entry.getKey() + ": " + entry.getValue());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printFilteredWordFrequency(sc.nextLine());
    }
}
