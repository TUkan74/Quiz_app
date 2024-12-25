package quizzapp.database;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import quizzapp.database.Database;
import quizzapp.models.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Question methods in the Database class.
 */
public class QuestionDatabaseTest {

    private static final Database database = new Database();
    private static final List<String> new_question_ids = new ArrayList<>();

    @AfterAll
    public static void clearAllTestQuestions() {
        System.out.println("Cleaning up test questions...");
        List<Question> questions = database.loadQuestions();
        questions.removeIf(question -> new_question_ids.contains(question.get_id()));
        database.saveQuestions(questions);
        System.out.println("Test resources cleared: " + new_question_ids);
    }

    @Test
    public void testAddQuestion() {
        System.out.println("Running testAddQuestion...");
        Question question = new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2);

        database.addQuestion(question);
        new_question_ids.add(question.get_id());

        List<Question> questions = database.loadQuestions();
        assertTrue(questions.stream().anyMatch(q -> q.get_id().equals(question.get_id())), "Question not added successfully!");
        System.out.println("Question added successfully: " + question);
    }

    @Test
    public void testDuplicateQuestion() {
        System.out.println("Running testDuplicateQuestion...");
        Question question = new Question("What is 2 + 2?", new String[]{"3", "4", "5"}, 1);
        database.addQuestion(question);
        new_question_ids.add(question.get_id());

        // Add the same question again
        database.addQuestion(question);

        List<Question> questions = database.loadQuestions();
        long count = questions.stream().filter(q -> q.get_text().equals("What is 2 + 2?")).count();
        assertEquals(1, count, "Duplicate question was added, but it should not be!");
        System.out.println("Duplicate question test passed for: " + question);
    }

    @Test
    public void testEditQuestion() {
        System.out.println("Running testEditQuestion...");
        Question question = new Question("What is the largest planet?", new String[]{"Earth", "Mars", "Jupiter"}, 2);
        database.addQuestion(question);
        new_question_ids.add(question.get_id());

        List<Question> questions = database.loadQuestions();
        Question questionToEdit = questions.stream().filter(q -> q.get_id().equals(question.get_id())).findFirst().orElse(null);
        assertNotNull(questionToEdit, "Question not found for editing!");

        // Edit options (for simplicity, replace the question)
        Question updatedQuestion = new Question("What is the largest planet in the Solar System?", new String[]{"Earth", "Venus", "Jupiter"}, 2);
        new_question_ids.add(updatedQuestion.get_id());

        questions.remove(questionToEdit);
        questions.add(updatedQuestion);
        database.saveQuestions(questions);

        List<Question> updatedQuestions = database.loadQuestions();
        assertTrue(updatedQuestions.stream().anyMatch(q -> q.get_text().equals("What is the largest planet in the Solar System?")), "Question not updated successfully!");
        System.out.println("Question edited successfully: " + updatedQuestion);
    }

    @Test
    public void testDeleteQuestion() {
        System.out.println("Running testDeleteQuestion...");
        Question question = new Question("What is the speed of light?", new String[]{"300,000 km/s", "150,000 km/s", "1,000,000 km/s"}, 0);
        database.addQuestion(question);
        new_question_ids.add(question.get_id());

        List<Question> questions = database.loadQuestions();
        questions.removeIf(q -> q.get_id().equals(question.get_id()));
        database.saveQuestions(questions);

        List<Question> updatedQuestions = database.loadQuestions();
        assertFalse(updatedQuestions.stream().anyMatch(q -> q.get_id().equals(question.get_id())), "Question not deleted successfully!");
        System.out.println("Question deleted successfully: " + question);
    }
}
