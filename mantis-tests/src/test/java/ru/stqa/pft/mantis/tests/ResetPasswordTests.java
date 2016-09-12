package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by maksym on 9/12/16.
 */
public class ResetPasswordTests extends TestBase {
  @BeforeMethod
  public void startMailServer() {
    APP_MANAGER.mail().start();
  }

  @Test
  public void testResetPassword() throws IOException {
    String password = "password";
    UserData user = APP_MANAGER.db().users().iterator().next();
    APP_MANAGER.session().login("administrator", "qwerty", false, false);
    APP_MANAGER.goTo()
            .managePage()
            .openManageUsers()
            .selectUser(user)
            .resetPassword();

    List<MailMessage> mailMessages = APP_MANAGER.mail().waitForMail(1, 10000);
    String resetPasswordLink = findResetPasswordLink(mailMessages, user.getEmail());
    APP_MANAGER.registration().finish(resetPasswordLink, password);
    assertThat(APP_MANAGER.newSession().login(user.getUsername(), password), is(true));
  }

  private String findResetPasswordLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    APP_MANAGER.mail().stop();
  }
}
