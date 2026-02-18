import java.util.Scanner;

abstract class Emp{
    private int empId;
    private String name;

    protected double baseSalary;
    protected double incrementedSalary;

    public Emp(int empId, String name, double baseSalary){
        this.empId = empId;
        this.name = name;
        this.baseSalary = baseSalary;
        this.incrementedSalary = baseSalary;
    }

    public abstract void calcSalary();

    public void display(){
        System.out.println("\nEmp ID: " + empId);
        System.out.println("Emp Name: " + name);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Incremented Salary: " + incrementedSalary);
    }
}

class FullTime extends Emp{
    private static double hike = 0.50; // 50%

    public FullTime(int employeeId, String name, double baseSalary){
        super(employeeId, name, baseSalary);
    }

    @Override
    public void calcSalary(){
        incrementedSalary = baseSalary * (1+hike);
    }
}


class Intern extends Emp {
    private static double hike = 0.25; // 25%

    public Intern(int employeeId, String name, double baseSalary){
        super(employeeId, name, baseSalary);
    }

    @Override
    public void calcSalary() {
        incrementedSalary = baseSalary * (1 + hike);
    }
}


// Main Class
public class Assignment2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Number of employees: ");
        int n = sc.nextInt();
        Emp[] employees = new Emp[n];

        for(int i = 0; i < n; i++){
            System.out.println("\nDetails of Employee " + (i + 1));

            System.out.print("Employee ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // clear buffer

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Base Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            System.out.print("1 - FullTime\n2 - Intern\nSelect Employee Type: ");
            int empType = sc.nextInt();
            sc.nextLine();

            if(empType == 1){
                employees[i] = new FullTime(id, name, salary);
            } else {
                employees[i] = new Intern(id, name, salary);
            }
        }

        System.out.println("\n--- Employee Details After Salary Increment ---");

        for (Emp emp : employees) {
            emp.calcSalary();
            emp.display();
        }
    }
}

