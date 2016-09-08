package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSessionHelper;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by maksym on 9/7/16.
 */
public class LoginHttpTests extends TestBase {

  @Test
  public void testLogin() throws IOException {
    HttpSessionHelper session = APP_MANAGER.newSession();
    boolean actual = session.login("administrator", "qwerty");
    assertThat(actual, is(true));
    assertThat(session.isLoggedInAs("administrator"),is(true));
  }
}