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
        System.out.println("Incremented Salary" + incrementedSalary);
    }
}

class FullTime extends Emp{
    private static double hike = 0.50; // 50%

    public FullTime(int employeeId, String name, double baseSalary){
        super(employeeId, name, baseSalary);
    }

    @Override
    public void calcSalary(){
        incrementedSalary = baseSalary + (baseSalary * hike);
    }
}


// Derived Class 2
class Intern extends Emp {
    private static double hike = 0.25; // 25%

    public Intern(int employeeId, String name, double baseSalary){
        super(employeeId, name, baseSalary);
    }

    @Override
    public void calcSalary() {
        incrementedSalary = baseSalary + (baseSalary * hike);
    }
}


// Main Class
public class Assignment2{
    public static void main(String[] args){
        Emp[] employees = new Emp[4];

        employees[0] = new FullTime(101, "Prakul", 50000);
        employees[1] = new Intern(102, "Aditya", 20000);
        employees[2] = new FullTime(103, "Dana", 60000);
        employees[3] = new Intern(104, "Mona", 25000);

        for (Emp emp : employees) {
            emp.calcSalary();  // Runtime Polymorphism
            emp.display();
        }
    }
}

