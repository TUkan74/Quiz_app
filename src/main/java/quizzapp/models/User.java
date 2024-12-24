package quizzapp.models;

import java.util.Objects;

/**
 * Represents a user in the quiz application.
 */
public class User {

    private final String _username;
    private int _actualPoints;
    private int _maxPoints;
    private int _totalAttempts;

    /**
     * Constructs a new User instance.
     *
     * @param username the username of the user
     * @throws NullPointerException if the username is null
     */
    public User(String username) {
        this._username = Objects.requireNonNull(username, "Username must not be null.");
        this._actualPoints = 0;
        this._maxPoints = 0;
        this._totalAttempts = 0;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username
     */
    public String get_username() {
        return _username;
    }

    /**
     * Returns the total points scored by the user.
     *
     * @return the total actual points
     */
    public int get_actualPoints() {
        return _actualPoints;
    }

    /**
     * Returns the maximum possible points.
     *
     * @return the maximum points
     */
    public int get_maxPoints() {
        return _maxPoints;
    }

    /**
     * Returns the total number of quiz attempts by the user.
     *
     * @return the total attempts
     */
    public int get_totalAttempts() {
        return _totalAttempts;
    }

    /**
     * Updates the user's score after a quiz.
     *
     * @param scoredPoints the points scored in the quiz
     * @param quizMaxPoints the maximum points possible in the quiz
     * @throws IllegalArgumentException if scoredPoints or quizMaxPoints is negative
     */
    public void update_score(int scoredPoints, int quizMaxPoints) {
        if (scoredPoints < 0 || quizMaxPoints < 0) {
            throw new IllegalArgumentException("Scored points and maximum points must not be negative.");
        }

        this._actualPoints += scoredPoints;
        this._maxPoints += quizMaxPoints;
        this._totalAttempts++;
    }

    /**
     * Returns the total score as a string in the format "x / y".
     *
     * @return the total score string
     */
    public String get_totalScore() {
        return _actualPoints + " / " + _maxPoints;
    }

    /**
     * Calculates the success percentage of the user.
     *
     * @return the success percentage
     */
    public double get_successPercentage() {
        if (_maxPoints == 0) {
            return 0.0;
        }
        return (_actualPoints / (double) _maxPoints) * 100;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return _username.equals(user._username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + _username + '\'' +
                ", totalScore='" + get_totalScore() + '\'' +
                ", successPercentage=" + String.format("%.2f", get_successPercentage()) + "%" +
                ", totalAttempts=" + _totalAttempts +
                '}';
    }
}
