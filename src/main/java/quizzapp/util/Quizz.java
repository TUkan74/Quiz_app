package quizzapp.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Quiz with an ID, title, description, and a list of questions.
 */
public class Quizz {

    private int _id;
    private String _title;
    private String _description;
    private List<String> _questions;

    /**
     * Constructs a new Quizz instance.
     *
     * @param id the unique identifier of the quiz
     * @param _title the title of the quiz
     * @param _description a brief description of the quiz
     */
    public Quizz(int id, String _title, String _description) {
        this._id = id;
        this._title = _title;
        this._description = _description;
        this._questions = new ArrayList<>();
    }

    /**
     * Gets the ID of the quiz.
     *
     * @return the quiz ID
     */
    public int get_id() {
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
     * Adds a question to the quiz.
     *
     * @param question the question to add
     */
    public void addQuestion(String question) {
        this._questions.add(question);
    }

    /**
     * Removes a question from the quiz.
     *
     * @param question the question to remove
     * @return true if the question was removed, false otherwise
     */
    public boolean removeQuestion(String question) {
        return this._questions.remove(question);
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
