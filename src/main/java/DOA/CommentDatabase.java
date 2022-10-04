package DOA;

import dbConnectionProvider.DbConnection;
import model.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD operations on the Comment Table
 */
public class CommentDatabase {

    private Connection dbConnection;

    public CommentDatabase(Connection connection) {
        this.dbConnection = connection;
    }

    public boolean createComment(int userId, int postId, String comment) {
        boolean result = false;
        try {
            String query = "insert into comment(comments,post_id,comment_user_id) " +
                    "values (?,?,?)";

            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(query);
            preparedStatement.setInt(2, postId);
            preparedStatement.setInt(3, userId);
            preparedStatement.setString(1, comment);

            preparedStatement.executeUpdate();
            result = true;
            this.dbConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Comment> getComments(int postId) {
        List<Comment> comments = new ArrayList<>();
        try {
            String query = "select user.ID, posts.title, posts.imagename, comment.comments from comment "
                    + "  INNER JOIN posts on comment.post_id=posts._id INNER JOIN user  on user.ID=comment.comment_user_id" +
                    " where posts._id=" + postId;

            PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setUserId(resultSet.getInt("user_id"));
                comment.setTitle(resultSet.getString("title"));
                comment.setPostImage(resultSet.getString("imagename"));
                comment.setComment(resultSet.getString("comments"));
                comments.add(comment);
            }
            this.dbConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return comments;
    }

    public boolean editComment(int userId, int postId, String comment) {
        boolean status = false;
        try {
            String query = "update comment set comments=? where post_id=? and comment_user_id=?";
            PreparedStatement prepared = this.dbConnection.prepareStatement(query);
            prepared.setString(1, comment);
            prepared.setInt(2, postId);
            prepared.setInt(3, userId);

            int result = prepared.executeUpdate();
            if (result > 0) {
                status = true;
            }
            this.dbConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean deleteComment(int postId, int userId) {
        boolean status = false;

        try {
            String query = "delete from comment where post_id=? and comment_user_id=?";
            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(query);
            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, userId);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                status = true;
            }
            this.dbConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
