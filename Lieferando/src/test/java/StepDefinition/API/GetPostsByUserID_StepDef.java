package StepDefinition.API;

import PageObjectModel.API.GetPostsByUserID_POM;
import PlaceHolder.common.SharedValues;
import PlaceHolder.framework.PlaceHolderConfiguration;
import io.cucumber.java.en.And;

public class GetPostsByUserID_StepDef extends GetPostsByUserID_POM {
    GetPostsByUserID_POM getPostsByUserID = new GetPostsByUserID_POM();
    @And("Send request to get Posts that's posted by this UserId")
    public void sendRequestToGetPostsThatSPostedByThisUserId()
    {
            SharedValues.setPostsList(getPostsByUserID.getPostsWithUserId(SharedValues.getUserId()));
        if(SharedValues.getPostsList().size()==0)
        {
            PlaceHolderConfiguration.HandlingExceptions("This username doesn't exist in our database");
        }
    }
}
