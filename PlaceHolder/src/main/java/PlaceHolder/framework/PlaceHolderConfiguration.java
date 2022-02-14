package PlaceHolder.framework;

import io.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlaceHolderConfiguration{
	@BeforeMethod(alwaysRun = true)
	public void configure() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	}


@Test
public static void HandlingExceptions(String exceptionReason)
{
	throw new SkipException(exceptionReason);
}
}