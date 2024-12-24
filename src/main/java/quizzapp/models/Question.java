package quizzapp.models;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;


public class Question {

    private final String _id;
    private final String _text;
    private final String[] _options;
    private final int _correctAnswerIndex;


    /**
     * Constructs a new Question instance.
     *
     * @param text the text of the question
     * @param options the answer options
     * @param correctAnswerIndex the index of the correct answer in the options array
     * @throws IllegalArgumentException if the options array is null, empty, or if the correctAnswerIndex is invalid
     */
    public Question(String text, String[] options, int correctAnswerIndex) {
        if (options == null || options.length == 0) {
            throw new IllegalArgumentException("Options array must not be null or empty.");
        }
        if (correctAnswerIndex < 0 || correctAnswerIndex >= options.length) {
            throw new IllegalArgumentException("Correct answer index is out of bounds.");
        }

        this._id = UUID.randomUUID().toString();
        this._text = Objects.requireNonNull(text, "Question text must not be null.");
        this._options = options.clone();
        this._correctAnswerIndex = correctAnswerIndex;
    }

    /**
     * Returns the unique ID of the question.
     *
     * @return the question ID
     */
    public String get_id() {
        return _id;
    }

    /**
     * Returns the text of the question.
     *
     * @return the question text
     */
    public String get_text(){
        return _text;
    }

    /**
     * Returns the options for the answers
     *
     * @return the answer options
     */
    public String[] get_options(){
        return _options.clone();
    }

    /**
     * Returns the index of the correct answer in the _options array
     *
     * @return index of correct answer
     */
    public int get_correctAnswerIndex(){
        return _correctAnswerIndex;
    }

    /**
     * Checks if the answer selected is the correct one
     *
     * @param answer selected
     * @return True if the index is correct, False if it is not
     */
    public boolean is_correct_answer(int answer){
        return answer == _correctAnswerIndex;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Question question = (Question) obj;
        return _correctAnswerIndex == question._correctAnswerIndex &&
                _text.equals(question._text) &&
                Arrays.equals(_options, question._options);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(_text, _correctAnswerIndex);
        result = 31 * result + Arrays.hashCode(_options);
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + _id + '\'' +
                ", text='" + _text + '\'' +
                ", options=" + Arrays.toString(_options) +
                ", correctAnswerIndex=" + _correctAnswerIndex +
                '}';
    }



}
