import java.util.Arrays;

public class Practice6 {

    public static void workingWithArrays(int[] arr) {
         for (int i = 10; i >=0; i--){
             Arrays.stream(arr).sorted().forEach(num -> System.out.println( num + " "));
           //  System.out.println(Arrays.toString(arr));
         }
    }

    public static void doublingEachElement(int[] arr2) {
        for (int i = 0; i < arr2.length; i++){
            arr2[i] = arr2[i] * 2;
            Arrays.sort(arr2);
            System.out.println(arr2[i]);
        }
    }

    public static void streamFunction(int[] arr3) {
        Arrays.sort(arr3);

        Arrays.stream(arr3).forEach(System.out::println);
    }



    public static void main(String[] args) {
        int[] numbers = {12, 42, 32, 1, 3, 9, 10, 3, 2, 7};
        doublingEachElement(numbers);
    }

    public static double max(int num1, double num2) {
        System.out.println("max(int, double) is invoked");

        if (num1 > num2)
            return num1;
        else
            return num2;
    }

    public static double max(double num1, int num2) {
        System.out.println("max(double, int) is invoked");

        if (num1 > num2)
            return num1;
        else
            return num2;
    }
}
