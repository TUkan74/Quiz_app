package quizzapp.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Question class.
 */
public class QuestionTest {

    @Test
    public void testConstructor_ValidInputs() {
        String text = "What is the capital of France?";
        String[] options = {"Berlin", "Madrid", "Paris", "Rome"};
        int correctAnswerIndex = 2;

        Question question = new Question(text, options, correctAnswerIndex);

        assertNotNull(question.getId());
        assertEquals(text, question.get_text());
        assertArrayEquals(options, question.get_options());
        assertEquals(correctAnswerIndex, question.get_correctAnswerIndex());
    }

    @Test
    public void testConstructor_NullText() {
        String[] options = {"Option 1", "Option 2"};
        assertThrows(NullPointerException.class, () -> new Question(null, options, 0));
    }

    @Test
    public void testConstructor_NullOptions() {
        assertThrows(IllegalArgumentException.class, () -> new Question("Question?", null, 0));
    }

    @Test
    public void testConstructor_EmptyOptions() {
        assertThrows(IllegalArgumentException.class, () -> new Question("Question?", new String[]{}, 0));
    }

    @Test
    public void testConstructor_InvalidCorrectAnswerIndex() {
        String[] options = {"Option 1", "Option 2"};
        assertThrows(IllegalArgumentException.class, () -> new Question("Question?", options, -1));
        assertThrows(IllegalArgumentException.class, () -> new Question("Question?", options, 2));
    }

    @Test
    public void testIsCorrectAnswer() {
        String text = "2 + 2 = ?";
        String[] options = {"3", "4", "5"};
        int correctAnswerIndex = 1;

        Question question = new Question(text, options, correctAnswerIndex);

        assertTrue(question.is_correct_answer(1));
        assertFalse(question.is_correct_answer(0));
        assertFalse(question.is_correct_answer(2));
    }

    @Test
    public void testImmutabilityOfOptions() {
        String text = "What is 2 + 2?";
        String[] options = {"3", "4", "5"};
        int correctAnswerIndex = 1;

        Question question = new Question(text, options, correctAnswerIndex);
        options[1] = "Changed";

        assertNotEquals("Changed", question.get_options()[1]);
    }

    @Test
    public void testEqualsAndHashCode() {
        String text = "What is the capital of Italy?";
        String[] options = {"Paris", "Rome", "Berlin"};
        int correctAnswerIndex = 1;

        Question question1 = new Question(text, options, correctAnswerIndex);
        Question question2 = new Question(text, options, correctAnswerIndex);

        assertNotEquals(question1.getId(), question2.getId()); // IDs should be unique
        assertEquals(question1, question1); // Reflexive property
        assertEquals(question1.hashCode(), question1.hashCode());
    }

    @Test
    public void testToString() {
        String text = "What is 3 + 3?";
        String[] options = {"5", "6", "7"};
        int correctAnswerIndex = 1;

        Question question = new Question(text, options, correctAnswerIndex);
        String result = question.toString();

        assertTrue(result.contains("id='"));
        assertTrue(result.contains("text='What is 3 + 3?"));
        assertTrue(result.contains("options=[5, 6, 7]"));
        assertTrue(result.contains("correctAnswerIndex=1"));
    }
}
