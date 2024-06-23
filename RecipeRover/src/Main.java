import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Element 1");
        list.add("Element 2");
        list.add("Element 3");

        // Adding an element at a specific index
        list.add(1, "New Element");

        // Display the ArrayList elements
        for (String element : list) {
            System.out.println(element);
        }
    }
}