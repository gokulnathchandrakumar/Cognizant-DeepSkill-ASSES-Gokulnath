public class SearchService {
    public Product linearSearch(Product[] products, int targetId) {
        int comparisons = 0;

        for (int i = 0; i < products.length; i++) {
            comparisons++;
            if (products[i].getProductId() == targetId) {
                System.out.println("Linear Search: found in " + comparisons + " comparisons");
                return products[i];
            }
        }

        System.out.println("Linear Search: not found after " + comparisons + " comparisons");
        return null;
    }
    public Product binarySearch(Product[] sortedProducts, int targetId) {
        int low = 0;
        int high = sortedProducts.length - 1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2; 
            int midId = sortedProducts[mid].getProductId();

            if (midId == targetId) {
                System.out.println("Binary Search: found in " + comparisons + " comparisons");
                return sortedProducts[mid];
            } else if (midId < targetId) {
                low = mid + 1;   
            } else {
                high = mid - 1;  
            }
        }

        System.out.println("Binary Search: not found after " + comparisons + " comparisons");
        return null;
    }
}
