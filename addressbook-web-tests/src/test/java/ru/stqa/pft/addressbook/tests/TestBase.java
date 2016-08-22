package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

/**
 * Created by maksym on 7/25/16.
 */
public class TestBase {

  protected static final ApplicationManager APP_MANAGER = new ApplicationManager(BrowserType.FIREFOX);

  @BeforeSuite
  public void setUp() throws Exception {
    APP_MANAGER.init();
  }

  @AfterSuite
  public void tearDown() {
    APP_MANAGER.stop();
  }

}
