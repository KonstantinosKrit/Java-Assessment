package Assignment;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * Accesses the quiz API, brings one quiz at a time,
 * and also can convert it to a Quiz object, or even compose a list of many Quiz objects.
 */
public class QuizReader {

    private ArrayList<ErrorObserver> observers;

    public QuizReader() {
        observers = new ArrayList<>();
    }

    /**
     * Reads a quiz from the API and converts to a JSON object.
     * @return The JSON object, or null if an error occurs
     */
    public JSONObject readFromAPI() {
        try {
            URL url = new URL("https://opentdb.com/api.php?amount=1");
            InputStreamReader is = new InputStreamReader(url.openStream());
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(is);
            return json;
        } catch (MalformedURLException e) {
            error(new Date() + ", " + getClass().getSimpleName() + ", " + e.getMessage());
            return null;
        } catch (IOException e) {
            error(new Date() + ", " + getClass().getSimpleName() + ", " + e.getMessage());
            return null;
        } catch (ParseException e) {
            error(new Date() + ", " + getClass().getSimpleName() + ", " + e.getMessage());
            return null;
        }
    }

    public void addErrorObserver(ErrorObserver obs) {
        observers.add(obs);
    }

    public void error(String message) {
        for(ErrorObserver e : observers) {
            e.error(message);
        }
    }

    /**
     * Converts a JSON object retrieved from the API, into A Quiz object
     * @return the Quiz object, or null in case of error
     */
    public Quiz getQuiz() {
        JSONObject json = readFromAPI();
        if (json == null) {
            return null;
        } else {
            try {
                JSONArray array = (JSONArray) json.get("results");
                JSONObject result = (JSONObject) array.get(0);
                String question = (String) result.get("question");
                String answer = (String) result.get("correct_answer");
                String type = (String) result.get("type");

                // Create the appropriate Quiz subclass based on the type
                if ("multiple".equals(type)) {
                    JSONArray optionsArray = (JSONArray) result.get("incorrect_answers");
                    String[] options = new String[optionsArray.size()];
                    for (int i = 0; i < optionsArray.size(); i++) {
                        options[i] = (String) optionsArray.get(i);
                    }
                    return new QuizMultipleChoice(question, answer, options);
                } else if ("boolean".equals(type)) {
                    return new QuizTrueFalse(question, answer);
                } else {
                    // Unsupported quiz type
                    error("Unsupported quiz type: " + type);
                    return null;
                }
            } catch (Exception e) {
                error(new Date() + ", " + getClass().getSimpleName() + ", Error parsing quiz: " + e.getMessage());
                return null;
            }
        }
    }

    /**
     * Retrieves a list of Quiz objects
     * @param n is the number of quizzes we need
     * @return an Array List of Quiz objects, or null in case of error
     */
    public ArrayList<Quiz> getQuizzes(int n) {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            Quiz q = getQuiz();
            if(q == null) {
                return null;
            }
            quizzes.add(q);
        }
        return quizzes;
    }
}
