package quizzapp.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import quizzapp.models.Question;
import quizzapp.models.Quizz;
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

    private static final String QUESTIONS_FILE = "questions.json";
    private static final String QUIZZES_FILE = "quizzes.json";
    private static final String USERS_FILE = "users.json";

    private final Gson gson;

    public Database() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Question Operations

    public List<Question> loadQuestions() {
        try (FileReader reader = new FileReader(QUESTIONS_FILE)) {
            Type questionListType = new TypeToken<List<Question>>() {}.getType();
            return gson.fromJson(reader, questionListType);
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

    // Quizz Operations

    public List<Quizz> loadQuizzes() {
        try (FileReader reader = new FileReader(QUIZZES_FILE)) {
            Type quizListType = new TypeToken<List<Quizz>>() {}.getType();
            return gson.fromJson(reader, quizListType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveQuizzes(List<Quizz> quizzes) {
        try (FileWriter writer = new FileWriter(QUIZZES_FILE)) {
            gson.toJson(quizzes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // User Operations

    public List<User> loadUsers() {
        try (FileReader reader = new FileReader(USERS_FILE)) {
            Type userListType = new TypeToken<List<User>>() {}.getType();
            return gson.fromJson(reader, userListType);
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
}
