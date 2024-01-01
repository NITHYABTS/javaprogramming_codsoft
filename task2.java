import java.util.Scanner;
public class task2 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Total number of subjects: ");
        int num_sub = scanner.nextInt();
        int total_mark=0;
        int mark_per_sub = 100;
        for(int i=1;i<=num_sub;i++){
            System.out.print("Enter mark "+i+": ");
            int marks = scanner.nextInt();

            marks = Math.min(Math.max(marks, 0), mark_per_sub);

            total_mark += marks;
        }
        double averagePercentage = (float) total_mark / (num_sub * mark_per_sub) * 100;

        System.out.println("\nResults:");
        System.out.println("Total Marks: " + total_mark);
        System.out.println("Average Percentage: " + averagePercentage + "%");

        // Grade Calculation
        char grade = calculateGrade(averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }

    private static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}