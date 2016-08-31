package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by maksym on 7/25/16.
 */
public class TestBase {

  private Logger log = LoggerFactory.getLogger(GroupCreationTests.class);

  protected static final ApplicationManager APP_MANAGER = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    APP_MANAGER.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    APP_MANAGER.stop();
  }

  @BeforeMethod(alwaysRun = true)
  public void logTestStart(Method method, Object[] parameters) {
    log.info("Start test " + method.getName() + " with parameters " + Arrays.asList(parameters));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestEnd(Method method, Object[] parameters) {
    log.info("End test " + method.getName());
  }

  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      log.warn("verifyUI is set");
      Groups dbGroups = APP_MANAGER.db().groups();
      APP_MANAGER.goTo().groupPage();
      Groups uiGroups = APP_MANAGER.group().all();

      assertThat(uiGroups,
              equalTo(dbGroups
                      .stream()
                      .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                      .collect(Collectors.toSet())));
    }
  }

  public void verifyContactListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      log.warn("verifyUI is set");
      Contacts dbContacts = APP_MANAGER.db().contacts();
      APP_MANAGER.goTo().homePage();
      Contacts uiContacts = APP_MANAGER.contact().all();

      assertThat(uiContacts.stream()
                      .map((c) -> new ContactData().withId(c.getId()).withFirstName(c.getFirstName()).withLastName(c.getLastName()))
                      .collect(Collectors.toSet()),
              equalTo(dbContacts.stream()
                      .map((c) -> new ContactData().withId(c.getId()).withFirstName(c.getFirstName()).withLastName(c.getLastName()))
                      .collect(Collectors.toSet())));
    }
  }

}
