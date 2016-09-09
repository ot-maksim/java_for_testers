package ru.stqa.pft.mantis.tests;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by maksym on 9/8/16.
 */
public class RegistrationTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    APP_MANAGER.mail().start();
  }

  @Test
  public void testRegistration() throws IOException {
    long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost.localdomain", now);
    String username = String.format("user%s", now);
    String password = "password";
    APP_MANAGER.registration().start(username, email);
    List<MailMessage> mailMessages = APP_MANAGER.mail().waitForMail(2, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    APP_MANAGER.registration().finish(confirmationLink, password);
    assertThat( APP_MANAGER.newSession().login(username, password), is(true));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod (alwaysRun = true)
  public void stopMailServer() {
    APP_MANAGER.mail().stop();
  }
}
