package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by maksym on 9/12/16.
 */
public class SessionHelper extends HelperBase {
  protected SessionHelper(ApplicationManager appManager) {
    super(appManager);
  }

  public void login(String username, String password, boolean isRemember, boolean isSecure) {
    getWebDriver().get(getAppManager().getProperty("web.baseUrl") + "/login.php");
    type(By.id("username"), username);
    type(By.id("password"), password);

    if (isRemember) {
      if (! getWebDriver().findElement(By.id("remember-login")).isSelected()) {
       click(By.id("remember-login"));
      }
    }
    if (isSecure) {
      if (! getWebDriver().findElement(By.id("secure-session")).isSelected()) {
        click(By.id("secure-session"));
      }
    }

    click(By.cssSelector("input.button"));

  }
}
