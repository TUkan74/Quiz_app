package quizzapp.models;

import quizzapp.database.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a Quiz with an ID, title, description, and a list of questions.
 */
public class Quiz {

    private String _id;
    private String _title;
    private String _description;
    private List<String> _questions;

    /**
     * Constructs a new Quizz instance.
     *
     * @param _title the title of the quiz
     * @param _description a brief description of the quiz
     */
    public Quiz(String _title, String _description) {
        this._id = UUID.randomUUID().toString();
        this._title = _title;
        this._description = _description;
        this._questions = new ArrayList<>();
    }

    /**
     * Gets the ID of the quiz.
     *
     * @return the quiz ID
     */
    public String get_id() {
        return _id;
    }

    /**
     * Gets the title of the quiz.
     *
     * @return the quiz title
     */
    public String get_title() {
        return _title;
    }

    /**
     * Sets the title of the quiz.
     *
     * @param _title the new title of the quiz
     */
    public void set_title(String _title) {
        this._title = _title;
    }

    /**
     * Gets the description of the quiz.
     *
     * @return the quiz description
     */
    public String get_description() {
        return _description;
    }

    /**
     * Sets the description of the quiz.
     *
     * @param _description the new description of the quiz
     */
    public void set_description(String _description) {
        this._description = _description;
    }

    /**
     * Adds a question to the quiz and updates the database.
     *
     * @param question the question to add
     * @param database the Database instance
     */
    public void addQuestion(String question, Database database) {
        this._questions.add(question); // Add question locally
        database.updateQuiz(this);    // Persist the updated quiz to the database
    }

    /**
     * Removes a question from the quiz and updates the database.
     *
     * @param question the question to remove
     * @param database the Database instance
     * @return true if the question was removed, false otherwise
     */
    public boolean removeQuestion(String question, Database database) {
        boolean removed = this._questions.remove(question);
        if (removed) {
            database.updateQuiz(this); // Persist the updated quiz to the database
        }
        return removed;
    }

    /**
     * Gets the list of questions in the quiz.
     *
     * @return the list of questions
     */
    public List<String> get_questions() {
        return new ArrayList<>(this._questions);
    }

    /**
     * Returns a string representation of the quiz.
     *
     * @return a string with the quiz details
     */
    @Override
    public String toString() {
        return "Quizz{" +
                "id=" + _id +
                ", title='" + _title + '\'' +
                ", description='" + _description + '\'' +
                ", questions=" + _questions +
                '}';
    }
}
