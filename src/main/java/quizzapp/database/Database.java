package quizzapp.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import quizzapp.models.Question;
import quizzapp.models.Quiz;
import quizzapp.models.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a JSON database for managing Questions, Quizzes, and Users.
 */
public class Database {

    private static final String QUESTIONS_FILE = "src/main/resources/questions.json";
    private static final String QUIZZES_FILE = "src/main/resources/quizzes.json";
    private static final String USERS_FILE = "src/main/resources/users.json";

    private final Gson gson;

    public Database() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Question Operations

    public List<Question> loadQuestions() {
        try (FileReader reader = new FileReader(QUESTIONS_FILE)) {
            Type questionListType = new TypeToken<List<Question>>() {}.getType();
            List<Question> questions = gson.fromJson(reader, questionListType);
            return questions != null ? questions : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveQuestions(List<Question> questions) {
        try (FileWriter writer = new FileWriter(QUESTIONS_FILE)) {
            gson.toJson(questions, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addQuestion(Question question) {
        List<Question> questions = loadQuestions();
        boolean exists = questions.stream().anyMatch(existingQuestion ->
                existingQuestion.get_id().equals(question.get_id()) ||
                        (existingQuestion.get_text().equals(question.get_text()) && existingQuestion.get_options().equals(question.get_options())));
        if (exists) {
            System.out.println("Question with ID \"" + question.get_id() + "\" or equivalent text/options already exists.");
            return;
        }
        questions.add(question);
        saveQuestions(questions);
    }

    public void deleteQuestion(String questionId) {
        List<Question> questions = loadQuestions();
        questions.removeIf(question -> question.get_id().equals(questionId));
        saveQuestions(questions);
    }

    public void addQuestionToQuiz(String quizId, Question question) {
        List<Quiz> quizzes = loadQuizzes();
        Quiz quiz = quizzes.stream().filter(q -> q.get_id().equals(quizId)).findFirst().orElse(null);
        if (quiz == null) {
            System.out.println("Quiz with ID \"" + quizId + "\" not found.");
            return;
        }

        if (quiz.get_questions().contains(question.get_id())) {
            System.out.println("Question with ID \"" + question.get_id() + "\" already exists in the quiz.");
            return;
        }

        quiz.get_questions().add(question.get_id());
        saveQuizzes(quizzes);
        System.out.println("Question added to quiz successfully.");
    }

    public void deleteQuestionFromQuiz(String quizId, String questionId) {
        List<Quiz> quizzes = loadQuizzes();
        Quiz quiz = quizzes.stream().filter(q -> q.get_id().equals(quizId)).findFirst().orElse(null);
        if (quiz == null) {
            System.out.println("Quiz with ID \"" + quizId + "\" not found.");
            return;
        }

        if (!quiz.get_questions().contains(questionId)) {
            System.out.println("Question with ID \"" + questionId + "\" not found in the quiz.");
            return;
        }

        quiz.get_questions().remove(questionId);
        saveQuizzes(quizzes);
        System.out.println("Question removed from quiz successfully.");
    }


    // Quiz Operations

    public List<Quiz> loadQuizzes() {
        try (FileReader reader = new FileReader(QUIZZES_FILE)) {
            Type quizListType = new TypeToken<List<Quiz>>() {}.getType();
            List<Quiz> quizzes = gson.fromJson(reader, quizListType);
            return quizzes != null ? quizzes : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveQuizzes(List<Quiz> quizzes) {
        try (FileWriter writer = new FileWriter(QUIZZES_FILE)) {
            gson.toJson(quizzes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addQuiz(Quiz quiz) {
        List<Quiz> quizzes = loadQuizzes();
        boolean exists = quizzes.stream().anyMatch(existingQuiz ->
                existingQuiz.get_id().equals(quiz.get_id()) ||
                        (existingQuiz.get_title().equals(quiz.get_title()) && existingQuiz.get_description().equals(quiz.get_description())));
        if (exists) {
            System.out.println("Quiz with ID \"" + quiz.get_id() + "\" or equivalent title/description already exists.");
            return;
        }
        quizzes.add(quiz);
        saveQuizzes(quizzes);
    }

    public void deleteQuiz(String quizId) {
        List<Quiz> quizzes = loadQuizzes();
        quizzes.removeIf(quiz -> quiz.get_id().equals(quizId));
        saveQuizzes(quizzes);
    }

    public void updateQuiz(Quiz updatedQuiz) {
        List<Quiz> quizzes = loadQuizzes(); // Load all quizzes
        for (int i = 0; i < quizzes.size(); i++) {
            if (quizzes.get(i).get_id().equals(updatedQuiz.get_id())) {
                quizzes.set(i, updatedQuiz); // Replace the old quiz with the updated one
                break;
            }
        }
        saveQuizzes(quizzes); // Save the updated list back to the JSON file
    }

    // User Operations

    public List<User> loadUsers() {
        try (FileReader reader = new FileReader(USERS_FILE)) {
            Type userListType = new TypeToken<List<User>>() {}.getType();
            List<User> users = gson.fromJson(reader, userListType);
            return users != null ? users : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }




    public void saveUsers(List<User> users) {
        try (FileWriter writer = new FileWriter(USERS_FILE)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        List<User> users = loadUsers();
        boolean exists = users.stream().anyMatch(existingUser -> existingUser.get_username().equals(user.get_username()));
        if (exists) {
            System.out.println("User with username " + user.get_username() + " already exists.");
            return;
        }
        users.add(user);
        saveUsers(users);
    }

    public void deleteUser(String username) {
        List<User> users = loadUsers();
        users.removeIf(user -> user.get_username().equals(username));
        saveUsers(users);
    }
}
