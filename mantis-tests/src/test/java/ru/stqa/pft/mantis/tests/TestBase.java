package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;


/**
 * Created by maksym on 7/25/16.
 */
public class TestBase {

  protected static final ApplicationManager APP_MANAGER = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    APP_MANAGER.init();
    APP_MANAGER.ftp().upload(new File("src/test/resources/config_inc.php"), "Sites/mantisbt-1.3.1/config/", "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    APP_MANAGER.ftp().restore("Sites/mantisbt-1.3.1/config/", "config_inc.php.bak", "config_inc.php");
    APP_MANAGER.stop();
  }


}
