package Assignment;

/**
 * Represents a True or False Quiz entity
 */
public class QuizTrueFalse extends Quiz {
    /**
     * Class constructor
     * @param question the question of the quiz
     * @param answer the correct answer to the quiz
     */
    public QuizTrueFalse(String question, String answer) {
        super(question, answer, "True or False");
    }
}
