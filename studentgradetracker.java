import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GradeTracker {
    private Map<String, Double> grades;
    private Scanner scanner;

    public GradeTracker() {
        grades = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void addGrade() {
        System.out.print("Enter subject name: ");
        String subject = scanner.nextLine();
        System.out.print("Enter grade for " + subject + ": ");
        double grade = scanner.nextDouble();
        scanner.nextLine(); // Consume the remaining newline
        grades.put(subject, grade);
        System.out.println("Grade added for " + subject + ".");
    }

    public void editGrade() {
        System.out.print("Enter subject name to edit: ");
        String subject = scanner.nextLine();
        if (grades.containsKey(subject)) {
            System.out.print("Enter new grade for " + subject + ": ");
            double grade = scanner.nextDouble();
            scanner.nextLine(); // Consume the remaining newline
            grades.put(subject, grade);
            System.out.println("Grade updated for " + subject + ".");
        } else {
            System.out.println("Subject not found.");
        }
    }

    public void deleteGrade() {
        System.out.print("Enter subject name to delete: ");
        String subject = scanner.nextLine();
        if (grades.remove(subject) != null) {
            System.out.println("Grade deleted for " + subject + ".");
        } else {
            System.out.println("Subject not found.");
        }
    }

    public void displayGrades() {
        System.out.println("Grades:");
        grades.forEach((subject, grade) -> System.out.println(subject + ": " + grade));
        System.out.println("Average Grade: " + calculateAverage());
        System.out.println("Letter Grade: " + getLetterGrade(calculateAverage()));
    }

    private double calculateAverage() {
        return grades.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    private String getLetterGrade(double average) {
        if (average >= 90) return "A";
        else if (average >= 80) return "B";
        else if (average >= 70) return "C";
        else if (average >= 60) return "D";
        else return "F";
    }

    public static void main(String[] args) {
        GradeTracker tracker = new GradeTracker();
        Scanner scanner = new Scanner(System.in);
        String option;

        do {
            System.out.println("\nOptions: add, edit, delete, display, exit");
            System.out.print("Choose an option: ");
            option = scanner.nextLine();

            switch (option.toLowerCase()) {
                case "add":
                    tracker.addGrade();
                    break;
                case "edit":
                    tracker.editGrade();
                    break;
                case "delete":
                    tracker.deleteGrade();
                    break;
                case "display":
                    tracker.displayGrades();
                    break;
                case "exit":
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        } while (!option.equalsIgnoreCase("exit"));

        scanner.close();
    }
}