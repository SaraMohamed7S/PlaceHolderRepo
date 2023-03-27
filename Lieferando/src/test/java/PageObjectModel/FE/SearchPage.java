package PageObjectModel.FE;

import utilities.Paths;
import utilities.TestBase;

public class SearchPage extends TestBase {
    public void navigateToSeachURL() {
        getDriver().navigate().to(Paths.searchPath);
    }
}
