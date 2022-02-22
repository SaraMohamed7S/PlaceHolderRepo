package PageObjectModel.API;

import PlaceHolder.common.EndPoints;
import PlaceHolder.common.SharedValues;
import PlaceHolder.serelization.Comment;
import StepDefinition.API.GetUsers_StepDef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class GetPostComments_POM {

    ArrayList<Comment> commentsList;
    List<Comment> comments_Per_Post;
    Response CommentsPerPost_Resp;
    JsonPath CommentsPerPost_Path;
    int commentIndexPerPost;
    String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    Pattern Email_pattern = Pattern.compile(regex);
    Matcher matcher;
    Logger logger = LogManager.getLogger(GetUsers_StepDef.class);

    @Test(dependsOnGroups = "2ndStep_GetUserPosts" )
    public List<Comment> getCommentsPerPost(List<Integer> postsList)
    {
        // This method to get comments for list of posts
        // 1- Loop over the list passed
        // 2- for each id in the list retrieve the comments
        // 3- Store the comments in the shared comments list
        comments_Per_Post = new ArrayList<>();
        commentsList = new ArrayList<>();
        int indexOfCommentList =0 ;
        for (Integer postId : postsList) {
            logger.info("Post Id " + postId + "  >>  has the following comments ids");
            CommentsPerPost_Resp = given().when().get(("https://jsonplaceholder.typicode.com".concat(EndPoints.Get_Posts).concat("/").concat(postId.toString()).concat(EndPoints.Get_Comments)));
            CommentsPerPost_Path = CommentsPerPost_Resp.jsonPath();
            comments_Per_Post = CommentsPerPost_Path.getList("", Comment.class);
            for ( commentIndexPerPost = 0; commentIndexPerPost < comments_Per_Post.size(); commentIndexPerPost++)
            {
                logger.info(comments_Per_Post.get(commentIndexPerPost).getId()+" , ");
                commentsList.add(indexOfCommentList, comments_Per_Post.get(commentIndexPerPost));
                indexOfCommentList++;
            }
        }
        SharedValues.setCommentsList(commentsList);
        Assert.assertEquals(CommentsPerPost_Resp.statusCode(),200);
        return commentsList;
    }

    @Test(dependsOnMethods = "getCommentsPerPost")
    public void validateEmailsFormat(List<Comment> commentsList)
    {
        // This method to validate the emails mentioned in each comment
        // matcher method is user ( it accepts regular expression to verify vs )
        for (Comment comment : commentsList)
        {
            logger.info("For comment Id "+ comment.getId());
            logger.info("With email : "+comment.getEmail());
            matcher = Email_pattern.matcher(comment.getEmail());
            if(matcher.matches())
                logger.info(" >> This is a valid email format");
            else
                logger.info(" >> This is not a valid email format");
            logger.info("/n");
        }
    }
}

