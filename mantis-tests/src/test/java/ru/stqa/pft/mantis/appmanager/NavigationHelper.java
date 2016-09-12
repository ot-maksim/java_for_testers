package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by maksym on 9/12/16.
 */
public class NavigationHelper extends HelperBase {
  ApplicationManager appManager;
  public NavigationHelper(ApplicationManager appManager) {
    super(appManager);
    this.appManager = appManager;
  }

  public ManageHelper managePage() {
    click(By.cssSelector("a.manage-menu-link"));
    return new ManageHelper(appManager);
  }
}
