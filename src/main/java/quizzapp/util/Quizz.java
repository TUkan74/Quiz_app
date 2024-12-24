package quizzapp.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Quiz with an ID, title, description, and a list of questions.
 */
public class Quizz {

    private int id;
    private String title;
    private String description;
    private List<String> questions;

    /**
     * Constructs a new Quizz instance.
     *
     * @param id the unique identifier of the quiz
     * @param title the title of the quiz
     * @param description a brief description of the quiz
     */
    public Quizz(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = new ArrayList<>();
    }

    /**
     * Gets the ID of the quiz.
     *
     * @return the quiz ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the title of the quiz.
     *
     * @return the quiz title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the quiz.
     *
     * @param title the new title of the quiz
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the quiz.
     *
     * @return the quiz description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the quiz.
     *
     * @param description the new description of the quiz
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Adds a question to the quiz.
     *
     * @param question the question to add
     */
    public void addQuestion(String question) {
        this.questions.add(question);
    }

    /**
     * Removes a question from the quiz.
     *
     * @param question the question to remove
     * @return true if the question was removed, false otherwise
     */
    public boolean removeQuestion(String question) {
        return this.questions.remove(question);
    }

    /**
     * Gets the list of questions in the quiz.
     *
     * @return the list of questions
     */
    public List<String> getQuestions() {
        return new ArrayList<>(this.questions);
    }

    /**
     * Returns a string representation of the quiz.
     *
     * @return a string with the quiz details
     */
    @Override
    public String toString() {
        return "Quizz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", questions=" + questions +
                '}';
    }
}
