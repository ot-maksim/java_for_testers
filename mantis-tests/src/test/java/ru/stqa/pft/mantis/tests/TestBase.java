package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;


/**
 * Created by maksym on 7/25/16.
 */
public class TestBase {

  protected static final ApplicationManager APP_MANAGER = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    APP_MANAGER.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    APP_MANAGER.stop();
  }


}
