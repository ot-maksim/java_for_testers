package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by maksym on 9/8/16.
 */
public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(ApplicationManager appManager) {
    super(appManager);
  }

  public void start(String username, String email) {
    getAppManager().getWebDriver().get(getAppManager().getProperty("web.baseUrl") + "/signup_page.php");
    type(By.id("username"), username);
    type(By.id("email-field"), email);
    click(By.cssSelector("input[value='Signup']"));
  }

  public void finish(String confirmationLink, String password) {
    getAppManager().getWebDriver().get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }
}
