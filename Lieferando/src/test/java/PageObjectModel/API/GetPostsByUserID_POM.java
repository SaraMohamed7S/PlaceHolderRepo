package PageObjectModel.API;

import PlaceHolder.common.EndPoints;
import PlaceHolder.serelization.Post;
import StepDefinition.API.GetUsers_StepDef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;

public class GetPostsByUserID_POM {
    int postIndex;
    ArrayList<Integer> postsList = new ArrayList<>();
    Logger logger = LogManager.getLogger(GetPostsByUserID_POM.class);

    @Test (dependsOnGroups = "FirstStep_GetID" , groups = "2ndStep_GetUserPosts")
    public ArrayList<Integer> getPostsWithUserId(int userID) // pass user ID
    {
        // This method to get posts that is posted by specific UserId
        // 1- Get all posts for that user
        Response Posts_resp = given().queryParam("userId", userID).when().get(("https://jsonplaceholder.typicode.com".concat(EndPoints.Get_Posts)));
        Assert.assertEquals(Posts_resp.statusCode(),200);

        //2- Loop over the posts list and store the posts ids in the shared posts list
        JsonPath JPath = Posts_resp.jsonPath();
        List<Post> postsPerUserId = JPath.getList("", Post.class);
        for ( postIndex = 0; postIndex < postsPerUserId.size(); postIndex++)
        {
            postsList.add(postIndex , postsPerUserId.get(postIndex).getId());
        }
        logger.info("This user has the following posts ids "+ postsList);
        return postsList;
    }
}
