package TestRunner;

import freemarker.log.Logger;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@CucumberOptions(features = {"PlaceHolder/src/test/java/FeatureFile/API"}
                    , glue = {"StepDefinition"}
                    , tags = ("@TestScenario")
, plugin = {
        "pretty","html:test-output/DefaultReport/DefaultReport.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
}
        )

public class TestRunnerAPI extends AbstractTestNGCucumberTests
{
        @BeforeTest
        public static void initializeLog() throws ClassNotFoundException, IOException {
                BasicConfigurator.configure();
                Logger.selectLoggerLibrary(Logger.LIBRARY_SLF4J);
                PropertyConfigurator.configure("PlaceHolder/src/main/resources/log4j2.properties");
        }
}
