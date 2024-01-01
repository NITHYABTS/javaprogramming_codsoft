import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctOptionIndex;

    public QuizQuestion(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

class Quiz {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int userScore;
    private Timer timer;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.userScore = 0;
        this.timer = new Timer();
    }

    public void startQuiz() {
        displayQuestion();

        // Create a new timer for each question
        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Moving to the next question.");
                processAnswer(-1); // Assume -1 as no answer within time
            }
        }, 15000); // 15 seconds timer for each question
    }


    private void displayQuestion() {
        QuizQuestion currentQuestion = questions.get(currentQuestionIndex);

        System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion());
        List<String> options = currentQuestion.getOptions();

        for (int i = 0; i < options.size(); i++) {
            System.out.println((char) ('A' + i) + ". " + options.get(i));
        }

        System.out.print("Your answer (A/B/C/D): ");
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.nextLine().toUpperCase();

        processAnswer(userAnswer.charAt(0) - 'A');
    }

    private void processAnswer(int userAnswerIndex) {
        // Check if the timer is not null before canceling
        if (timer != null) {
            timer.cancel(); // Cancel the timer for the current question
            timer = null; // Set timer to null after canceling
        }

        QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
        int correctAnswerIndex = currentQuestion.getCorrectOptionIndex();

        System.out.println("Correct answer: " + (char) ('A' + correctAnswerIndex));

        if (userAnswerIndex == correctAnswerIndex) {
            System.out.println("Correct!\n");
            userScore++;
        } else if (userAnswerIndex == -1) {
            System.out.println("Time's up! The correct answer was " + (char) ('A' + correctAnswerIndex) + "\n");
        } else {
            System.out.println("Incorrect. The correct answer was " + (char) ('A' + correctAnswerIndex) + "\n");
        }

        currentQuestionIndex++;

        if (currentQuestionIndex < questions.size()) {
            startQuiz(); // Move to the next question
        } else {
            displayResult(); // Display final score and summary
        }
    }


    private void displayResult() {
        System.out.println("Quiz completed!");
        System.out.println("Your score: " + userScore + " out of " + questions.size());
    }
}

public class task4 {
    public static void main(String[] args) {
        // Create quiz questions
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        quizQuestions.add(new QuizQuestion("Who invented Java Programming?", List.of("Guido van Rossum", "James Gosling", "Dennis Ritchie", "Bjarne Stroustrup"), 1));
        quizQuestions.add(new QuizQuestion("Which component is used to compile, debug and execute the java programs?", List.of("JRE", "JIT", "JDK", "JVM"), 2));
        quizQuestions.add(new QuizQuestion("Which one of the following is not a Java feature?", List.of("Object-oriented", "Use of pointers", "Portable", "Dynamic and Extensible"), 1));
        quizQuestions.add(new QuizQuestion("Which of these cannot be used for a variable name in Java?", List.of("identifier & keyword", "identifier", "keyword", "NONE of them"), 2));
        quizQuestions.add(new QuizQuestion("What is the extension of java code files?", List.of(".js", ".txt", ".class", ".java"), 3));
        // Create a quiz with the questions
        Quiz quiz = new Quiz(quizQuestions);

        // Start the quiz
        quiz.startQuiz();
    }
}
