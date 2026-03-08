public class LinearSearch {

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                System.out.println("Found the target " + target + " at index: " + i);
                return i;
            }
        }
        System.out.println("Nothing found");
        return -1;
    }

    public static void main(String[] args) {
         int[] array = { 9, 324, 21, 203, 45, 96, 2, 803, 1, 1000 };
         int x = 1;

         linearSearch(array, x);

    }
}
