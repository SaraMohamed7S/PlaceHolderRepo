package PlaceHolder.framework;

import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlaceHolderConfiguration{
	static Logger logger = LogManager.getLogger(PlaceHolderConfiguration.class);
	@BeforeMethod(alwaysRun = true)
	public void configure() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	}


@Test
public static void HandlingExceptions(String exceptionReason)
{
	logger.error(exceptionReason);
	throw new SkipException(exceptionReason);
}
}