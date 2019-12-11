import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.Timer;

/**
 * @author calebcramer
 * This is the panel that runs the quiz tab
 *
 * It has a problem in the middle of the panel and a timer on the top
 * There are also a few buttons and a label on the bottom
 *
 * It is a timed quiz that will save all of the answers they give and return their score
 */
public class QuizPanel extends JPanel {
    Timer timer = null;
    int time = 10;
    JLabel timeLabel;
    JLabel statusLabel = new JLabel("");
    JLabel instructions = new JLabel("<html> <div align=\"left\">This is The Quiz! When you are ready, press the start quiz button. We will keep track of your right and wrong answers and give you your score at the end. Good Luck!</div> </html> ");
    JButton startQuiz =  new JButton("Start Quiz");
    JButton submit = new JButton("Submit");
//    JButton restart = new JButton("Reset quiz");
    DivisionPanel divisionPanel;
    private QuizController qc;
    LinkedList<Integer> answers = new LinkedList<>();
    private Random rand = new Random();

    public QuizPanel(AppModel ap){
        super(new BorderLayout());
        divisionPanel = newDivisionPanel();
        submit.setEnabled(false);
        qc = new QuizController(this, ap);
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
        timeLabel  = new JLabel(Integer.toString(time));
        timeLabel.setFont(new Font("Times Roman", Font.BOLD, 25));
        //timeLabel.setAlignmentX(JLabel.CENTER);
        northPanel.add(timeLabel);
        northPanel.add(instructions);
        this.add(northPanel, BorderLayout.NORTH);

        this.add(divisionPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        southPanel.add(startQuiz);
        southPanel.add(submit);
        southPanel.add(statusLabel);
//        southPanel.add(restart);
        this.add(southPanel, BorderLayout.SOUTH);
    }
    void addToList(String answer){
        answers.add(Integer.parseInt(answer));
    }
    String getQuotient(){
       return divisionPanel.quotient.getText();
    }
    int getRightAnswer(){
        return Integer.parseInt(divisionPanel.dividend.getText()) / Integer.parseInt(divisionPanel.divisor.getText());
    }
    private DivisionPanel newDivisionPanel(){
        int divisor = rand.nextInt(30) + 2;
        int dividend = rand.nextInt(30) + 2;
        int quotient = dividend * divisor;

        return new DivisionPanel(divisor, quotient, dividend);
    }
    void newQuestion(){
        remove(divisionPanel);
        divisionPanel = newDivisionPanel();
        add(divisionPanel);
        validate();
    }
}
