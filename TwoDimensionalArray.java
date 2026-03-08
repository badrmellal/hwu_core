public class TwoDimensionalArray {


    public static void main(String[] args) {
        // Declaration and Initialization of a 2D array
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // Accessing elements in the 2D array
        System.out.println("Element at position (1, 2): " + matrix[1][2]); // Outputs: 6

        // Iterating over the 2D array using nested loops
        System.out.println("All elements in the 2D array:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); // Move to the next line after each row
        }
    }
}
