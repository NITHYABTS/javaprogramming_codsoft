import java.util.Random;
import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int min = 1, max = 100, max_attempts = 10, rounds = 0, score = 0;
        boolean play = true;

        while (play) {
            int target = random.nextInt(max - min + 1) + min;
            int attempts = 0;
            boolean guess = false;

            System.out.println("Number Guessing Game");
            while (attempts < max_attempts && !guess) {
                System.out.print("Enter your guess: ");
                int user_guess = scanner.nextInt();
                attempts++;

                if (user_guess == target) {
                    System.out.println("Congratulations! your guess is correct");
                    guess = true;
                    score += 100;
                } else if (user_guess < target) {
                    System.out.println("guess higher!");
                } else {
                    System.out.println("guess lower!");
                }
            }
                if (!guess) {
                    System.out.println("GAME OVER!");
                }
                rounds++;

                System.out.print("Do you want to try again? ");
                String playAgainResponse = scanner.next().toLowerCase();
                play = playAgainResponse.equals("yes");

            }
            System.out.println("Thank you for playing");
            System.out.println("Rounds: " + rounds +"\n"+ "Total Score: " + score);
            scanner.close();

    }
}


