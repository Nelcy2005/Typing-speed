import database.ScoreDAO;
import java.util.ArrayList;
import javax.swing.*;

public class HistoryUI {

    public HistoryUI(String username){

        JFrame frame = new JFrame("Typing History");

        JTextArea area = new JTextArea();
        area.setEditable(false);

        JScrollPane scroll = new JScrollPane(area);

        frame.add(scroll);

        frame.setSize(400,300);

        ArrayList<String> history = ScoreDAO.getHistory(username);

        for(String record : history){
            area.append(record + "\n");
        }

        frame.setVisible(true);
    }
}