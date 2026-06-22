import java.util.Arrays;
import java.util.Comparator;

public class SearchTest {
    public static void main(String[] args) {

        
        Product[] unsortedProducts = {
            new Product(105, "Wireless Mouse", "Electronics"),
            new Product(102, "Yoga Mat", "Fitness"),
            new Product(110, "Bluetooth Speaker", "Electronics"),
            new Product(101, "Running Shoes", "Footwear"),
            new Product(107, "Office Chair", "Furniture")
        };

        Product[] sortedProducts = Arrays.copyOf(unsortedProducts, unsortedProducts.length);
        Arrays.sort(sortedProducts, Comparator.comparingInt(Product::getProductId));

        System.out.println("---- Linear Search (unsorted array) ----");
        Product result1 = new SearchService().linearSearch(unsortedProducts, 107);
        System.out.println("Result: " + result1);

        System.out.println("\n---- Binary Search (sorted array) ----");
        Product result2 = new SearchService().binarySearch(sortedProducts, 107);
        System.out.println("Result: " + result2);

        System.out.println("\n---- Searching for a non-existent product (999) ----");
        new SearchService().linearSearch(unsortedProducts, 999);
        new SearchService().binarySearch(sortedProducts, 999);
    }
}
