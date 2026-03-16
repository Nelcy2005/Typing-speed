import database.UserDAO;
import java.awt.*;
import javax.swing.*;

public class LoginUI extends JFrame {

    JTextField user = new JTextField(15);
    JPasswordField pass = new JPasswordField(15);

    public LoginUI() {

        setTitle("Typing Speed Tester - Login");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JButton login = new JButton("Login");
        JButton register = new JButton("Create Account");

        login.addActionListener(e -> {

            String u = user.getText();
            String p = new String(pass.getPassword());

            if (UserDAO.login(u, p)) {

                dispose();
                new DashboardUI(u);

            } else {

                JOptionPane.showMessageDialog(this,"Invalid Login");

            }

        });

        register.addActionListener(e -> new RegisterUI());

        panel.add(new JLabel("Username"));
        panel.add(user);

        panel.add(new JLabel("Password"));
        panel.add(pass);

        panel.add(login);
        panel.add(register);

        add(panel);

        setSize(350,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}