import database.UserDAO;
import java.awt.*;
import javax.swing.*;

public class RegisterUI extends JFrame {

    JTextField user = new JTextField(15);
    JPasswordField pass = new JPasswordField(15);

    public RegisterUI() {

        setTitle("Create Account");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JButton create = new JButton("Create Account");

        create.addActionListener(e -> {

            String username = user.getText();
            String password = new String(pass.getPassword());

            if (UserDAO.register(username, password)) {

                JOptionPane.showMessageDialog(this,"Account Created Successfully!");
                dispose();

            } else {

                JOptionPane.showMessageDialog(this,"Username already exists!");

            }

        });

        panel.add(new JLabel("Username"));
        panel.add(user);

        panel.add(new JLabel("Password"));
        panel.add(pass);

        panel.add(new JLabel(""));
        panel.add(create);

        add(panel);

        setSize(350,200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}