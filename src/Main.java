import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            LoginUI login = new LoginUI();
            login.setVisible(true);

        });

    }

}