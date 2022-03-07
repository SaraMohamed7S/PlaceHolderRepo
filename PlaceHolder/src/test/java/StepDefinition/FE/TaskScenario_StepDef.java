package StepDefinition.FE;

import PageObjectModel.FE.SearchPage;
import PlaceHolder.common.SharedValues;
import PlaceHolder.framework.PlaceHolderConfiguration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class TaskScenario_StepDef {

    SearchPage searchPage = new SearchPage();
    @Given("Search URL Opened")
    public void openSearchURL()
    {
        searchPage.navigateToSeachURL();
    }

}
