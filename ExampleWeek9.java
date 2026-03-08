public class ExampleWeek9 {

    abstract class Car {

        abstract void define(String brand, String model, int year);

        abstract void run();

        public void move() {
            System.out.println("Car moving");
        }

        public void turnLeft() {
            System.out.println("Car turning left");
        }

        public void turnRight() {
            System.out.println("Car turning right");
        }
    }

    class MaseratiCar extends Car {

        String brand = "Maserati";
        String model = "YOLO";
        int year = 2020;



        @Override
        void define(String brand, String model, int year) {
            System.out.println("This is " + brand + " " + model + " " + year);
        }

        @Override
        void run() {
            System.out.println("Car running");
        }


    }


    public static void main(String[] args) {
        ExampleWeek9 week9 = new ExampleWeek9();
        MaseratiCar obj = week9.new MaseratiCar();

        obj.run();
    }
}
