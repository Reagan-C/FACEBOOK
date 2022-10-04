package DOA;

import model.User;
import utils.PasswordHashing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Create and Delete operations on the User table
 */
public class UserDatabase {
    private Connection dbConnection;

    public UserDatabase(Connection connection) {
        this.dbConnection = connection;
    }

    public boolean registerUser(User user) {
        boolean set = false;
        try {
            String query = " INSERT INTO user (firstName,lastName,email,password) values (?,?,?,?)";

            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.executeUpdate();
            set = true;

            this.dbConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    public User loginUser(String email, String password) {
        User user = null;
        String query;

        try {
            query = "select * from user where email=?";

            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(query);

            preparedStatement.setString(1, email);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                user = new User();

                user.setFirstName(result.getString("firstname"));
                user.setLastName(result.getString("lastname"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setId(result.getInt("ID"));

                String decryptPass = PasswordHashing.decryptPassword(result.getString("password"));

                if (!decryptPass.equals(password)) {
                    return null;
                }
            }
            this.dbConnection.close();
        } catch (Exception e) {
        }

        return user;
    }

    /**
     * DELETE operation on User
     *
     * @param email
     * @return boolean(true for successful deletion and false on failure to delete)
     */

    public boolean deleteUser(String email) {
        boolean success = false;
        try {
            String query = "delete from user where email= ?";
            PreparedStatement prepared = this.dbConnection.prepareStatement(query);
            prepared.setString(1, email);

            int result = prepared.executeUpdate();

            if (result > 0) {
                success = true;
            }
            this.dbConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }
}