package StepDefinition.API;

import PageObjectModel.API.GetPostComments_POM;
import PlaceHolder.common.SharedValues;
import PlaceHolder.framework.PlaceHolderConfiguration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class GetCommentByPostID_StepDef {
    GetPostComments_POM getPostComments = new GetPostComments_POM();

    @And("Send request to get Comment that's mentioned into these posts")
    public void sendRequestToGetCommentThatSMentionedIntoThesePosts ()
    {
           SharedValues.setCommentsList(getPostComments.getCommentsPerPost(SharedValues.getPostsList()));
        if(SharedValues.getCommentsList().size()==0)
            PlaceHolderConfiguration.HandlingExceptions("This username doesn't exist in our database");
    }

    @Then("Validate the emails formats")
    public void validateTheEmailsFormats()
    {
        getPostComments.validateEmailsFormat(SharedValues.getCommentsList());
    }
}
