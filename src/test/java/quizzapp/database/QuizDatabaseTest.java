package quizzapp.database;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import quizzapp.database.Database;
import quizzapp.models.Quiz;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Quiz methods in the Database class.
 */
public class QuizDatabaseTest {

    private static final Database database = new Database();
    private static final List<String> new_quiz_ids = new ArrayList<>();

    @AfterAll
    public static void clearAllTestQuizzes() {
        System.out.println("Cleaning up test quizzes...");
        List<Quiz> quizzes = database.loadQuizzes();
        quizzes.removeIf(quiz -> new_quiz_ids.contains(quiz.get_id()));
        database.saveQuizzes(quizzes);
        System.out.println("Test resources cleared: " + new_quiz_ids);
    }

    @Test
    public void testAddQuiz() {
        System.out.println("Running testAddQuiz...");
        Quiz quiz = new Quiz("Java Basics", "A quiz on Java basics");

        database.addQuiz(quiz);
        new_quiz_ids.add(quiz.get_id());

        List<Quiz> quizzes = database.loadQuizzes();
        assertTrue(quizzes.stream().anyMatch(q -> q.get_id().equals(quiz.get_id())), "Quiz not added successfully!");
        System.out.println("Quiz added successfully: " + quiz);
    }

    @Test
    public void testDuplicateQuiz() {
        System.out.println("Running testDuplicateQuiz...");
        Quiz quiz = new Quiz("Java OOP", "A quiz on Java object-oriented programming");
        database.addQuiz(quiz);
        new_quiz_ids.add(quiz.get_id());

        // Attempt to add the same quiz again
        database.addQuiz(quiz);

        List<Quiz> quizzes = database.loadQuizzes();
        long count = quizzes.stream().filter(q -> q.get_title().equals("Java OOP")).count();
        assertEquals(1, count, "Duplicate quiz was added, but it should not be!");
        System.out.println("Duplicate quiz test passed for: " + quiz);
    }

    @Test
    public void testDeleteQuiz() {
        System.out.println("Running testDeleteQuiz...");
        Quiz quiz = new Quiz("Python Basics", "A quiz on Python basics");
        database.addQuiz(quiz);
        new_quiz_ids.add(quiz.get_id());

        database.deleteQuiz(quiz.get_id());

        List<Quiz> quizzes = database.loadQuizzes();
        assertFalse(quizzes.stream().anyMatch(q -> q.get_id().equals(quiz.get_id())), "Quiz not deleted successfully!");
        System.out.println("Quiz deleted successfully: " + quiz);
    }

    @Test
    public void testUpdateQuiz() {
        System.out.println("Running testUpdateQuiz...");
        Quiz quiz = new Quiz("C++ Basics", "A quiz on C++ basics");
        database.addQuiz(quiz);
        new_quiz_ids.add(quiz.get_id());

        // Update quiz description
        quiz.set_description("An updated description for C++ basics");
        database.updateQuiz(quiz);

        List<Quiz> quizzes = database.loadQuizzes();
        Quiz updatedQuiz = quizzes.stream().filter(q -> q.get_id().equals(quiz.get_id())).findFirst().orElse(null);

        assertNotNull(updatedQuiz, "Quiz not found after update!");
        assertEquals("An updated description for C++ basics", updatedQuiz.get_description(), "Quiz description not updated correctly!");
        System.out.println("Quiz updated successfully: " + updatedQuiz);
    }
}
