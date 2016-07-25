package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by maksym on 7/25/16.
 */
public class SessionHelper extends HelperBase {

  public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(String username, String password) {
    type("user", username);
    type("pass", password);
    click(By.xpath("//form[@id='LoginForm']/input[3]"));
  }
}
