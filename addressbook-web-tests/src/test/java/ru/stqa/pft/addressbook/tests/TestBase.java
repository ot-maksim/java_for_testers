package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by maksym on 7/25/16.
 */
public class TestBase {

  Logger log = LoggerFactory.getLogger(GroupCreationTests.class);

  protected static final ApplicationManager APP_MANAGER = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    APP_MANAGER.init();
  }

  @AfterSuite (alwaysRun = true)
  public void tearDown() {
    APP_MANAGER.stop();
  }

  @BeforeMethod
  public void logTestStart(Method method, Object[] parameters) {
    log.info("Start test " + method.getName() + " with parameters " + Arrays.asList(parameters));
  }

  @AfterMethod (alwaysRun = true)
  public void logTestEnd(Method method, Object[] parameters) {
    log.info("End test " + method.getName());
  }

}
