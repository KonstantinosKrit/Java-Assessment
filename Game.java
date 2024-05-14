package Assignment;

import java.util.ArrayList;

/**
 * Maintains the logic of a quiz game.
 * Holds a number of quizzes (currently 10) each time we start a game
 * Checks given answers, and updates score.
 * Also saves score to the database and retrieves top scorers to be displayed.
 */
public class Game {
    /** list of quiz for a game */
    private ArrayList<Quiz> quizzes;

    /** score of a game */
    private int score;

    /** username of the user currently playing */
    private String username;

    /** current quiz to be asked */
    private int current;

    private int size;

    private Database database;

    public Game(int size) {
        this.size = size;
        database = new Database();
        database.addErrorObserver(new ErrorLogger());
        database.addErrorObserver(new ErrorTweet());
        database.connect();
    }

    public boolean isGameOK() {
        return quizzes != null && quizzes.size() == size;
    }

    /**
     * Starts a new quiz game by initializing all the necessary variables
     */
    public void startGame() {
        QuizReader reader = new QuizReader();
        reader.addErrorObserver(new ErrorLogger());
        reader.addErrorObserver(new ErrorTweet());
        quizzes = reader.getQuizzes(size);
        score = 0;
        current = 0;
    }

    /**
     * Provides the current question
     * @return current question
     */
    public String getCurrentQuestion() {
        return quizzes.get(current).getQuestion();
    }

    /**
     * Checks the correctness of a given answer, towards the current quiz and updates score if necessary.
     * Then proceeds to the next quiz.
     * @param answer the answer to be checked
     */
    public void checkCurrentAnswer(String answer) {
        if(quizzes.get(current).isCorrect(answer)) {
            increaseScore();
        }
        current++;
    }

    /**
     * Sets the username of the current player
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Increases the score by one
     */
    public void increaseScore() {
        score++;
    }


    /**
     * Provides the score of the current game
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Provides the username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    public int getSize() {
        return size;
    }

    public void saveScore() {
        database.saveScore(username, score);
    }

    public boolean gameOver() {
        return current == size;
    }
}
