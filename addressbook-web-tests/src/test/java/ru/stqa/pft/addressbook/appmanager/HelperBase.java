package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * Created by maksym on 7/25/16.
 */
public class HelperBase {
  private WebDriver wd;
  private ApplicationManager applicationManager;

  protected HelperBase(WebDriver wd, ApplicationManager applicationManager) {
    this.wd = wd;
    this.applicationManager = applicationManager;
  }

  protected ApplicationManager getApplicationManager() {
    return applicationManager;
  }

  protected WebDriver getWd() {
    return wd;
  }

  protected void type(By locator, String text) {
    click((locator));
    if (text != null) {
      String existingText = getWd().findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        getWd().findElement(locator).clear();
        getWd().findElement(locator).sendKeys(text);
      }
    }
  }

  protected void click(By locator) {
    getWd().findElement(locator).click();
  }

  protected boolean isAlertPresent() {
    try {
      getWd().switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected boolean isElementPresent(By locator) {
    try {
      getWd().findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}
