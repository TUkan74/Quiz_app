package quizzapp;
/*
import quizzapp.models.Quiz;
import quizzapp.models.Question;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Quiz quiz = null;

        while (true) {
            System.out.println("\n=== Quiz Application ===");
            System.out.println("1. Create a new quiz");
            System.out.println("2. Add a question to the quiz");
            System.out.println("3. View quiz details");
            System.out.println("4. Take the quiz");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter quiz title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the quizz description: ");
                    String desc = scanner.nextLine();
                    quiz = new Quiz(title,desc);
                    System.out.println("Quiz created successfully.");
                }
                case 2 -> {
                    if (quiz == null) {
                        System.out.println("You need to create a quiz first.");
                        continue;
                    }
                    System.out.print("Enter question text: ");
                    String questionText = scanner.nextLine();
                    System.out.print("Enter correct answer: ");
                    String correctAnswer = scanner.nextLine();
                    quiz.addQuestion(new Question(questionText, correctAnswer));
                    System.out.println("Question added successfully.");
                }
                case 3 -> {
                    if (quiz == null) {
                        System.out.println("No quiz available. Create one first.");
                        continue;
                    }
                    System.out.println("\nQuiz Title: " + quiz.getTitle());
                    System.out.println("Questions:");
                    quiz.getQuestions().forEach(q -> System.out.println("- " + q.getText()));
                }
                case 4 -> {
                    if (quiz == null) {
                        System.out.println("No quiz available. Create one first.");
                        continue;
                    }
                    int score = 0;
                    for (Question question : quiz.getQuestions()) {
                        System.out.println("\n" + question.getText());
                        System.out.print("Your answer: ");
                        String userAnswer = scanner.nextLine();
                        if (userAnswer.equalsIgnoreCase(question.getCorrectAnswer())) {
                            System.out.println("Correct!");
                            score++;
                        } else {
                            System.out.println("Wrong. The correct answer was: " + question.getCorrectAnswer());
                        }
                    }
                    System.out.println("\nYou scored " + score + " out of " + quiz.getQuestions().size() + ".");
                }
                case 5 -> {
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
*/