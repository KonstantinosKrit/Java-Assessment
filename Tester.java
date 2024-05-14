package Assignment;

public class Tester {

    public static void main(String[] args) {
        QuizReader qr = new QuizReader();
        Quiz q = qr.getQuiz();
        System.out.println(q);
    }
}
