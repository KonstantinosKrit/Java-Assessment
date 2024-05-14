package Assignment;

/**
 * Represents a multiple choice Quiz entity
 */
public class QuizMultipleChoice extends Quiz{
    /** Options for the multiple choice question */
    private String[] options;

    /**
     * Class constructor
     * @param question the question of the quiz
     * @param answer the correct answer to the quiz
     * @param options options for the multiple choice question
     */
    public QuizMultipleChoice(String question, String answer, String[] options) {
        super(question, answer, "Multiple Choice");
        this.options = options;
    }

    /**
     * Gives the options for the multiple choice question
     * @return options for the multiple choice question
     */
    public String[] getOptions() {
        return options;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\nOptions:\n");
        for (String option : options) {
            sb.append(option).append("\n");
        }
        return sb.toString();
    }
}
