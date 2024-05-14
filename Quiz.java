package Assignment;

/**
 * Represents one Quiz entity
 */
public class Quiz {
    /** the question of the quiz */
    private String question;
    /** the correct answer to the quiz */
    private String answer;
    private String type;

    /**
     * Class constructor
     * @param question gives the question of the quiz
     * @param answer gives the correct answer to the quiz
     * @param type the type of question (multiple choice or true/false)
     */
    public Quiz(String question, String answer, String type) {
        this.question = question;
        this.answer = answer;
        this.type = type;
    }

    /**
     * Gives the question of the quiz
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Gives the type of question
     * @return the type of question (multiple choice or true/false)
     */
    public String getType() {
        return type;
    }

    /**
     * Checks if a string contains the correct answer to the quiz
     * @param answer the string to be checked
     * @return true id string is correct, false otherwise
     */
    public boolean isCorrect(String answer) {
        return this.answer.equals(answer);
    }

    @Override
    public String toString() {
        return "Q: " + type + question + "\nA: " + answer;
    }
}
