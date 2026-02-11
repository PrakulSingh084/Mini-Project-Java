import java.util.Scanner;

class student {
    String name;
    int rollno;
    int[] marks;

    public student(String name, int rollno, int[] marks) {
        this.name = name;
        this.rollno = rollno;
        this.marks = marks;
    }

    public double avgMarks() {
        int total = 0;
        for (int m : marks) {
            total += m;
        }
        return (double) total / marks.length;
    }

    public char grade(double avg) {
        if (avg >= 90) {
            return 'A';
        } else if (avg >= 80) {
            return 'B';
        } else if (avg >= 70) {
            return 'C';
        } else if (avg >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public void display() {
        double avg = avgMarks();
        char x = grade(avg);

        System.out.println("\nStudent Details:");
        System.out.println("Name: " + name);
        System.out.println("Roll no: " + rollno);
        System.out.println("Average Marks: " + avg);
        System.out.println("Grade: " + x);
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int numb = sc.nextInt();
        sc.nextLine();  // clear buffer

        student[] students = new student[numb];

        for (int i = 0; i < numb; i++) {

            System.out.println("\nDetails of student " + (i + 1));

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Roll No: ");
            int roll = sc.nextInt();

            int[] marks = new int[5];
            System.out.println("Enter marks for 5 subjects:");
            for (int j = 0; j < 5; j++) {
                marks[j] = sc.nextInt();
            }
            sc.nextLine(); // clear buffer

            students[i] = new student(name, roll, marks);
        }

        // Display details of all students
        for (int i = 0; i < numb; i++) {
            students[i].display();
        }

        sc.close();
    }
}