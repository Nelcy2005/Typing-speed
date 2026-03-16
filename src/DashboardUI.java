import database.UserDAO;
import java.awt.*;
import javax.swing.*;

public class DashboardUI extends JFrame {

    public DashboardUI(String username){

        setTitle("Typing Test Dashboard");

        JButton practice = new JButton("Start Practice");
        JButton history = new JButton("View History");
        JButton edit = new JButton("Edit Profile");
        JButton delete = new JButton("Delete Account");

        practice.addActionListener(e -> new TypingUI(username));

        history.addActionListener(e -> new HistoryUI(username));

        edit.addActionListener(e -> {

            String newUser = JOptionPane.showInputDialog(this,"Enter New Username:");
            String newPass = JOptionPane.showInputDialog(this,"Enter New Password:");

            if(newUser != null && newPass != null){

                UserDAO.updateUser(username,newUser,newPass);

                JOptionPane.showMessageDialog(this,"Profile Updated!");

            }

        });

        delete.addActionListener(e -> {

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this account?");

            if(confirm == 0){

                UserDAO.deleteUser(username);

                JOptionPane.showMessageDialog(this,"Account Deleted");

                dispose();

                new LoginUI();

            }

        });

        setLayout(new GridLayout(4,1,15,15));
        setSize(350,300);
        setLocationRelativeTo(null);

        add(practice);
        add(history);
        add(edit);
        add(delete);

        setVisible(true);
    }
}