package DOA;

import dbConnectionProvider.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;


/**
 * Create and Delete operations on the Like table
 */
public class LikeDataBase {

    private final Connection dbConnection;

    public LikeDataBase(Connection connection) {
        this.dbConnection = connection;
    }

    public boolean likePost(int userId, int postId, int action) {
        boolean success = false;
        try {
            String query;
            PreparedStatement preparedStatement;

            if (action == 1) {
                query = "insert into likes(post_id,user_id) " +
                        "values (?,?)";

                preparedStatement = DbConnection.getConnection().prepareStatement(query);
                preparedStatement.setInt(1, postId);
                preparedStatement.setInt(2, userId);

                preparedStatement.executeUpdate();
                success = true;
            } else {
                query = "delete from likes where user_id=" + userId + " and post_id=" + postId;
                preparedStatement = DbConnection.getConnection().prepareStatement(query);
                int result = preparedStatement.executeUpdate();

                if (result > 0) {
                    success = true;
                }
            }
            this.dbConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

}