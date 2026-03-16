package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    // REGISTER NEW USER
    public static boolean register(String username, String password) {

        try {

            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO users(username,password) VALUES(?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ps.executeUpdate();

            ps.close();
            conn.close();

            return true;

        } catch (Exception e) {
            System.out.println("Registration Failed");
            e.printStackTrace();
        }

        return false;
    }

    // LOGIN USER
    public static boolean login(String username, String password) {

        try {

            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            boolean exists = rs.next();

            rs.close();
            ps.close();
            conn.close();

            return exists;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // UPDATE USERNAME OR PASSWORD
    public static boolean updateUser(String oldUser, String newUser, String newPass) {

        try {

            Connection conn = DBConnection.getConnection();

            String sql = "UPDATE users SET username=?, password=? WHERE username=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newUser);
            ps.setString(2, newPass);
            ps.setString(3, oldUser);

            ps.executeUpdate();

            ps.close();
            conn.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // DELETE ACCOUNT
    public static boolean deleteUser(String username) {

        try {

            Connection conn = DBConnection.getConnection();

            String sql = "DELETE FROM users WHERE username=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ps.executeUpdate();

            ps.close();
            conn.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}