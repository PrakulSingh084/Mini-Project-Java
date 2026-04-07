import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

abstract class Shape {
    abstract double area();
}


class Circle extends Shape {
    double rad;
    Circle(double r){
        rad = r;
    }

    double area() {
        return 3.14 * rad * rad;
    }
}


class Rectangle extends Shape {
    double length, breadth;
    Rectangle(double len, double brd) {
        length = len;
        breadth = brd;
    }
    double area() {
        return length * breadth;
    }
}

class Square extends Shape {
    double side;
    Square(double s) {
        side = s;
    }

    double area() {
        return side * side;
    }
}

class Triangle extends Shape {
    double base, height;
    Triangle(double b, double h) {
        base = b;
        height = h;
    }

    double area() {
        return 0.5 * base * height;
    }
}

public class Assignment3a {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char ch;

        try {
            FileWriter fw = new FileWriter("shapes_output.txt", true);

            do {
                System.out.println("\n--x-- AREA CALCULATOR --x--\n");
                System.out.println("1. Circle\n2. Rectangle\n3. Square\n4. Triangle");
                System.out.print("Enter your choice: ");

                int option = sc.nextInt();
                Shape obj;
                String result = "";

                switch (option) {
                    case 1:
                        System.out.print("Enter circle radius: ");
                        double rad = sc.nextDouble();
                        obj = new Circle(rad);

                        result = "Circle(" + rad + ") = " + obj.area();
                        break;

                    case 2:
                        System.out.print("Enter length: ");
                        double len = sc.nextDouble();

                        System.out.print("Enter breadth: ");
                        double brd = sc.nextDouble();

                        obj = new Rectangle(len, brd);

                        result = "Rectangle(" + len + "," + brd + ") = " + obj.area();
                        break;

                    case 3:
                        System.out.print("Enter side: ");
                        double side = sc.nextDouble();
                        obj = new Square(side);

                        result = "Square(" + side + ") = " + obj.area();
                        break;

                    case 4:
                        System.out.print("Enter base: ");
                        double base = sc.nextDouble();

                        System.out.print("Enter height: ");
                        double hgt = sc.nextDouble();
                        obj = new Triangle(base, hgt);

                        result = "Triangle(" + base + "," + hgt + ") = " + obj.area();
                        break;

                    default:
                        result = "Invalid choice. Select Again";
                }

                System.out.println(result);
                // writing to file
                fw.write(result + "\n");

                System.out.print("\nDo you want to calculate area of another shape (Y/N): ");
                ch= sc.next().charAt(0);

            }
            while (ch == 'y' || ch == 'Y');

            fw.close();
            System.out.println("\n--x-- Results Saved --x--\n");

        }
        catch (IOException e){
            System.out.println("Error writing to file");
        }

        sc.close();
    }
}