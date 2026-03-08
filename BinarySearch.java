public class BinarySearch {


    public static int binarySearch(int[] arr, int x) {
        int right = arr.length - 1;
        int left = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (x == arr[mid]){
                return mid;
            } else if (x < arr[mid]){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int target = 8;

        int index = binarySearch(arr, target);

        if (index != -1) {
            System.out.println("Element " + target + " found at index " + index);
        } else {
            System.out.println("Element " + target + " not found");
        }

    }
}
