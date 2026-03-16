import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import util.StoryProvider;
import database.ScoreDAO;

public class TypingUI {

    private String username;

    JFrame frame = new JFrame("Typing Speed Test");

    JTextPane storyPane = new JTextPane();
    JTextPane typingPane = new JTextPane();

    JLabel timerLabel = new JLabel("60");
    JLabel resultLabel = new JLabel("");

    String story;

    int timeLeft = 60;

    Timer timer;

    public TypingUI(String username){

        this.username = username;

        story = StoryProvider.getRandomStory();

        frame.setSize(700,500);
        frame.setLayout(new BorderLayout());

        frame.getContentPane().setBackground(new Color(240,235,255));

        storyPane.setText(story);
        storyPane.setEditable(false);

        storyPane.setFont(new Font("Serif",Font.PLAIN,18));
        storyPane.setBackground(new Color(250,245,255));

        typingPane.setFont(new Font("Serif",Font.PLAIN,18));
        typingPane.setBackground(new Color(255,250,250));

        JScrollPane storyScroll = new JScrollPane(storyPane);
        JScrollPane typingScroll = new JScrollPane(typingPane);

        timerLabel.setFont(new Font("Arial",Font.BOLD,28));
        timerLabel.setHorizontalAlignment(JLabel.CENTER);

        resultLabel.setFont(new Font("Arial",Font.BOLD,16));
        resultLabel.setHorizontalAlignment(JLabel.CENTER);

        frame.add(timerLabel,BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(2,1,10,10));
        center.setBackground(new Color(240,235,255));

        center.add(storyScroll);
        center.add(typingScroll);

        frame.add(center,BorderLayout.CENTER);

        frame.add(resultLabel,BorderLayout.SOUTH);

        startTimer();

        typingPane.addKeyListener(new KeyAdapter(){

            public void keyReleased(KeyEvent e){

                checkTyping();

            }

        });

        frame.setVisible(true);
    }

    void startTimer(){

        timer = new Timer(1000,new ActionListener(){

            public void actionPerformed(ActionEvent e){

                timeLeft--;

                timerLabel.setText("Time: "+timeLeft);

                if(timeLeft==0){

                    timer.stop();

                    calculateResult();
                }

            }

        });

        timer.start();
    }

    void checkTyping(){

        String typed = typingPane.getText();

        StyledDocument doc = typingPane.getStyledDocument();

        Style correct = typingPane.addStyle("correct",null);
        StyleConstants.setForeground(correct,Color.GREEN);

        Style wrong = typingPane.addStyle("wrong",null);
        StyleConstants.setForeground(wrong,Color.RED);

        for(int i=0;i<typed.length();i++){

            try{

                if(i < story.length() && typed.charAt(i)==story.charAt(i)){

                    doc.setCharacterAttributes(i,1,correct,true);

                }else{

                    doc.setCharacterAttributes(i,1,wrong,true);

                }

            }catch(Exception ex){}
        }
    }

    void calculateResult(){

        String typed = typingPane.getText();

        int words = typed.trim().split("\\s+").length;

        int wpm = words;

        int correct = 0;

        for(int i=0;i<typed.length() && i<story.length();i++){

            if(typed.charAt(i)==story.charAt(i)){

                correct++;
            }
        }

        double accuracy = (correct*100.0)/story.length();

        resultLabel.setText("WPM: "+wpm+" | Accuracy: "+String.format("%.2f",accuracy)+"%");

        ScoreDAO.saveScore(username,wpm,accuracy);
    }
}