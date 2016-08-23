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

  protected ApplicationManager appManager() {
    return applicationManager;
  }

  protected WebDriver webDriver() {
    return wd;
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = webDriver().findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        webDriver().findElement(locator).clear();
        webDriver().findElement(locator).sendKeys(text);
      }
    }
  }

  protected void click(By locator) {
    webDriver().findElement(locator).click();
  }

  protected boolean isAlertPresent() {
    try {
      webDriver().switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected boolean isElementPresent(By locator) {
    try {
      webDriver().findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}
