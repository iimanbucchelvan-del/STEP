import java.util.Scanner;

public class Assignment_Q3_Product_Inventory_CSV_Parser {
    static void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Product: " + fields[0] + " | SKU: " + fields[1] +
                           " | Qty: " + fields[2]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        parseInventoryRecord(sc.nextLine());
    }
}
