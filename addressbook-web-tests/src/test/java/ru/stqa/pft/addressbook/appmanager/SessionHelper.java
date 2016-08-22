package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by maksym on 7/25/16.
 */
public class SessionHelper extends HelperBase {

  public SessionHelper(WebDriver wd, ApplicationManager applicationManager) {
    super(wd, applicationManager);
  }

  public void login(String username, String password) {
    type(By.xpath(".//*[@id='LoginForm']/input[1]"), username);
    type(By.xpath(".//*[@id='LoginForm']/input[2]"), password);
    click(By.xpath("//form[@id='LoginForm']/input[3]"));
  }
}
