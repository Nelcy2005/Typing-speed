package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ScoreDAO {

    // SAVE TYPING RESULT
    public static void saveScore(String username, int wpm, double accuracy) {

        try {

            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO scores(username,wpm,accuracy) VALUES(?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setInt(2, wpm);
            ps.setDouble(3, accuracy);

            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET SCORE HISTORY
    public static ArrayList<String> getHistory(String username) {

        ArrayList<String> history = new ArrayList<>();

        try {

            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM scores WHERE username=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int wpm = rs.getInt("wpm");
                double acc = rs.getDouble("accuracy");

                history.add("WPM: " + wpm + " | Accuracy: " + acc + "%");
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return history;
    }
}