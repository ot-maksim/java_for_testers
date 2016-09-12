package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

/**
 * Created by maksym on 9/12/16.
 */
public class ManageHelper extends HelperBase {

  public ManageHelper(ApplicationManager appManager) {
    super(appManager);
  }

  public ManageHelper openManageUsers() {
    click(By.cssSelector("a[href$='manage_user_page.php']"));
    return this;
  }

  public ManageHelper selectUser(UserData user) {
    click(By.cssSelector(String.format("a[href$='user_id=%s']", user.getId())));
    return this;
  }

  public void resetPassword() {
    click(By.xpath(".//*[@id='manage-user-reset-form']/fieldset/span/input"));
  }
}
