package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by maksym on 9/8/16.
 */
public class RegistrationHelper {
  private ApplicationManager appManager;
  private WebDriver wd;

  public RegistrationHelper(ApplicationManager appManager) {
    this.appManager = appManager;
    wd = this.appManager.getWebDriver();
  }

  public void start(String username, String email) {

    wd.get(appManager.getProperty("web.baseUrl") + "/signup_page.php");
  }
}
