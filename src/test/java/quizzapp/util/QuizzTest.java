package quizzapp.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Unit tests for the Quizz class.
 */
public class QuizzTest {

    @Test
    public void testConstructor_ValidInputs() {
        Quizz quiz = new Quizz(1, "Sample Quiz", "A test quiz");

        assertEquals(1, quiz.getId());
        assertEquals("Sample Quiz", quiz.getTitle());
        assertEquals("A test quiz", quiz.getDescription());
        assertTrue(quiz.getQuestions().isEmpty());
    }

    @Test
    public void testSetTitleAndDescription() {
        Quizz quiz = new Quizz(1, "Sample Quiz", "A test quiz");

        quiz.setTitle("Updated Title");
        quiz.setDescription("Updated Description");

        assertEquals("Updated Title", quiz.getTitle());
        assertEquals("Updated Description", quiz.getDescription());
    }

    @Test
    public void testAddQuestion() {
        Quizz quiz = new Quizz(1, "Sample Quiz", "A test quiz");

        quiz.addQuestion("What is the capital of France?");
        quiz.addQuestion("What is 2 + 2?");

        List<String> questions = quiz.getQuestions();
        assertEquals(2, questions.size());
        assertTrue(questions.contains("What is the capital of France?"));
        assertTrue(questions.contains("What is 2 + 2?"));
    }

    @Test
    public void testRemoveQuestion() {
        Quizz quiz = new Quizz(1, "Sample Quiz", "A test quiz");

        quiz.addQuestion("What is the capital of France?");
        quiz.addQuestion("What is 2 + 2?");

        assertTrue(quiz.removeQuestion("What is 2 + 2?"));
        assertFalse(quiz.getQuestions().contains("What is 2 + 2?"));
        assertEquals(1, quiz.getQuestions().size());
    }

    @Test
    public void testToString() {
        Quizz quiz = new Quizz(1, "Sample Quiz", "A test quiz");
        quiz.addQuestion("What is the capital of France?");

        String result = quiz.toString();

        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("title='Sample Quiz'"));
        assertTrue(result.contains("description='A test quiz'"));
        assertTrue(result.contains("questions=[What is the capital of France?]"));
    }
}
