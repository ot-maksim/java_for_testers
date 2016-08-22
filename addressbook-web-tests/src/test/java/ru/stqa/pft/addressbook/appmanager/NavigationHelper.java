package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by maksym on 7/25/16.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd, ApplicationManager applicationManager) {
    super(wd, applicationManager);
  }

  public void groupPage() {
    if(isElementPresent(By.tagName("h1"))
            && webDriver().findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }

    click(By.linkText("groups"));
  }

  public void homePage() {
    if(isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }
}
