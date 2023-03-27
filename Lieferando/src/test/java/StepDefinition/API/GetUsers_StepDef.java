package StepDefinition.API;

import PageObjectModel.API.GetUserId_POM;
import PlaceHolder.common.SharedValues;
import PlaceHolder.framework.PlaceHolderConfiguration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GetUsers_StepDef extends GetUserId_POM {

    GetUserId_POM getUserId = new GetUserId_POM();
    Logger logger = LogManager.getLogger(GetUsers_StepDef.class);

    @When("User send request to get userId for the test user")
    public void user_send_request_to_get_user_id_with_specific() {
       try{
           SharedValues.setUserId(Integer.parseInt(getUserId.getUserID(SharedValues.getUserName())));
           logger.info("The requested username has User ID = "+ SharedValues.getUserId());
            }
       catch (NumberFormatException e)
       {
            PlaceHolderConfiguration.HandlingExceptions("This username doesn't exist in our database");
            logger.error("This username doesn't exist in our database");
       }
   }

    @Given("Tester send {string}")
    public void testerSend(String userName) {
        SharedValues.setUserName(userName);
    }
}
