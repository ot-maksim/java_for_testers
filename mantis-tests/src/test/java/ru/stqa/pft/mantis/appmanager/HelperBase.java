package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.nio.file.Paths;

/**
 * Created by maksym on 7/25/16.
 */
public class HelperBase {
  private WebDriver wd;
  private ApplicationManager appManager;

  protected HelperBase(ApplicationManager appManager) {
    this.wd = appManager.getWebDriver();
    this.appManager = appManager;
  }

  protected WebDriver getWebDriver() {
    return wd;
  }

  protected ApplicationManager getAppManager() {
    return appManager;
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = getWebDriver().findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        getWebDriver().findElement(locator).clear();
        getWebDriver().findElement(locator).sendKeys(text);
      }
    }
  }

  protected void attach(By locator, String path) {
    if (path != null) {
      getWebDriver().findElement(locator).sendKeys(Paths.get(path).toAbsolutePath().toString());
    }
  }

  protected void click(By locator) {
    getWebDriver().findElement(locator).click();
  }

  protected boolean isAlertPresent() {
    try {
      getWebDriver().switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected boolean isElementPresent(By locator) {
    try {
      getWebDriver().findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}
