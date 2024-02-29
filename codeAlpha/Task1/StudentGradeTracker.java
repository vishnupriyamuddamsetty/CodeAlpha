import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of students from the user
        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();

        // Array to store student names
        String[] studentNames = new String[numStudents];
        // Array to store student grades
        double[] studentGrades = new double[numStudents];

        // Input loop to get student names and grades
        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter name for student " + (i + 1) + ": ");
            studentNames[i] = scanner.next();

            System.out.print("Enter grade for " + studentNames[i] + ": ");
            studentGrades[i] = scanner.nextDouble();
        }

        // Calculate average grade
        double totalGrade = 0;
        double highestGrade = Double.MIN_VALUE;
        double lowestGrade = Double.MAX_VALUE;

        for (double grade : studentGrades) {
            totalGrade += grade;
            if (grade > highestGrade) {
                highestGrade = grade;
            }
            if (grade < lowestGrade) {
                lowestGrade = grade;
            }
        }

        double averageGrade = totalGrade / numStudents;

        // Output results
        System.out.println("\nStudent Grade Summary:");
        System.out.println("Average Grade: " + averageGrade);
        System.out.println("Highest Grade: " + highestGrade);
        System.out.println("Lowest Grade: " + lowestGrade);

        scanner.close();
    }
}
