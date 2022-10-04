package DOA;

import dbConnectionProvider.DbConnection;
import model.Post;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostDatabase {

    private Connection dbConnection;

    public PostDatabase(Connection connection) {
        this.dbConnection = connection;
    }

    public boolean createPost(int userId, Post post) {
        boolean result = false;
        try {

            String query = "insert into posts(_id,title,body,imagename) " +
                    "values (?,?,?,?)";

            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, post.getTitle());
            preparedStatement.setString(3, post.getBody());
            preparedStatement.setString(4, post.getImageName());

            System.out.println(userId);
            preparedStatement.executeUpdate();
            result = true;

            this.dbConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * GET by id operation on Post
     *
     * @return post object
     * @params postId
     */
    public Post getPostById(int postId) {
        Post post = null;

        try {
            String query = "select posts._id, posts.title, posts.body, posts.imagename, user.email from posts"
                    + "  INNER JOIN user ON posts.userID = user.ID where posts._id=?";
            PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, postId);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                post = new Post();

                post.setId(result.getInt("_id"));
                post.setTitle(result.getString("title"));
                post.setBody(result.getString("body"));
                post.setImageName(result.getString("imagename"));
                post.setEmail(result.getString("email"));

                return post;
            }
            this.dbConnection.close();
        } catch (Exception e) {
        }

        return post;
    }


    /**
     * GET operation on Post
     *
     * @param currentUser
     * @return list of posts
     */
    public List<Post> getPosts(User currentUser) {

        List<Post> posts = new ArrayList<>();

        try {
            String query = "select posts._id, posts.title, posts.body, posts.imagename, user.firstname, user.lastname " +
                    " FROM posts INNER JOIN user ON posts.userID = user.ID ORDER BY posts._id DESC";
            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
            Post post;

            while (result.next()) {
                post = new Post();
                post.setId(result.getInt("_id"));
                post.setTitle(result.getString("title"));
                post.setBody(result.getString("body"));
                post.setImageName(result.getString("imagename"));
                post.setName(result.getString("firstname") + " " + result.getString("lastname"));

                //the total number of likes on this particular post
                String que = "select * from likes where _id=" + post.getId();
                PreparedStatement prepared = this.dbConnection.prepareStatement(que);
                ResultSet res = prepared.executeQuery();
                res.last();
                int noOfLikes = res.getRow();
                post.setNoLikes(noOfLikes);

                //no of comments made on that particular posts
                String que1 = "select * from comment where _id=" + post.getId();
                PreparedStatement prepared1 = this.dbConnection.prepareStatement(que1);
                ResultSet res1 = prepared1.executeQuery();
                res1.last();
                int noOfComments = res1.getRow();
                post.setNoComments(noOfComments);

                //return true if current user liked this post, else false
                String que2 = "select * from likes where _id=" + post.getId() + " and userID=" + currentUser.getId();
                PreparedStatement prepared2 = this.dbConnection.prepareStatement(que2);
                ResultSet res2 = prepared2.executeQuery();
                if (res2.next()) {
                    post.setLikedPost(true);
                }

                //add post to arraylist
                posts.add(post);
                this.dbConnection.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return posts;
    }

    public boolean updatePost(int postId, Post newPost) {
        boolean success = false;

        try {
            String query = "update posts set title=?, body=? where _id=?";
            PreparedStatement prepared = this.dbConnection.prepareStatement(query);
            System.out.println(postId);

            prepared.setString(1, newPost.getTitle());
            prepared.setString(2, newPost.getBody());
            prepared.setInt(3, postId);

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

    public boolean deletePost(int userId, int postId) {
        boolean success = false;
        try {
            String query = "delete from posts where _id = ? and userID = ?";
            PreparedStatement prepared = this.dbConnection.prepareStatement(query);
            prepared.setInt(1, postId);
            prepared.setInt(2, userId);
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
