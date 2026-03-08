import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    private void scannerFunction(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your age: ");
        int x = sc.nextInt();
        int y = ++x;
        System.out.println("Next year you will be: " + y);
        if (y > 18) {
            String name;
            System.out.println("What's your name?");
            name = sc.next();
            System.out.println("Hello " + name +" are eligible for life!");
        } else {
            System.out.println("You are not eligible!");
        }

        sc.close();
    }

    private void equalsFunction(String a, String b){
        if(a.equalsIgnoreCase(b)){
            System.out.println("a and b are the same!");
        } else {
            System.out.println("Not the same!");
        }
    }

    private void equalityFunction(String a, String b){
        if(a == b){
            System.out.println("a and b are the same!");
        } else {
            System.out.println("Not the same!");
        }
    }

    private void sortingNumbers(int [] x){

        Arrays.sort(x);
        System.out.println("Sorted Array: " + Arrays.toString(x));
    }

    private void sortingStrings(String [] y){

        Arrays.sort(y, Collections.reverseOrder());
        System.out.println("Sorted Array: " + Arrays.toString(y));
    }

    public static void main(String[] args) {

        String[] charac = {"hello world", "hello", "world", "Hello", "mother", "father", "aero"};
        Main main = new Main();
        main.sortingStrings(charac);

    }
}