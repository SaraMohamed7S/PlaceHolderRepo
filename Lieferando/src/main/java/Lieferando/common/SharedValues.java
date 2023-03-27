package PlaceHolder.common;

import PlaceHolder.serelization.Comment;

import java.util.ArrayList;
import java.util.List;
public class SharedValues {
    // This class is implemented to share the variable over the multiple scenario steps
    // Java encapsulation concept is used

    private static String userName;
    private static int userID;
    private static ArrayList<Integer> posts ;
    private static List<Comment> comments;

    public static void setUserName(String userNameParm) {
        userName = userNameParm;
    }
    public static String getUserName() {
        return userName;
    }

    public static int getUserId() {
        return userID;
    }
    public static List<Integer> getPostsList() {
        return posts;
    }
    public static List<Comment> getCommentsList() {
        return comments;
    }

    public static void setCommentsList(List<Comment> commentsPerPost) {
        comments = new ArrayList<>();
        for (int i =0 ; i <commentsPerPost.size();i++)
        {
            comments.add(i , commentsPerPost.get(i));
        }
    }

    public static void setPostsList(ArrayList<Integer> postsPerUserId) {
        posts = new ArrayList<>();
        for (int i =0 ; i <postsPerUserId.size();i++)
        {
            posts.add(i , postsPerUserId.get(i));
        }
    }

    public static void setUserId(int userId)
    {
        userID = userId;
    }
}
