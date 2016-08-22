package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by maksym on 7/25/16.
 */
public class ApplicationManager {
  private NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  private WebDriver wd;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public WebDriver getWebDriver() {
    return wd;
  }

  public void init() {
    if (Objects.equals(browser, BrowserType.FIREFOX)){
      wd = new FirefoxDriver();
    } else if (Objects.equals(browser, BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (Objects.equals(browser, BrowserType.SAFARI)) {
      wd = new SafariDriver();
    } else if (Objects.equals(browser, BrowserType.OPERA_BLINK)) {
      wd = new OperaDriver();
    }
    groupHelper = new GroupHelper(wd, this);
    contactHelper = new ContactHelper(wd, this);
    navigationHelper = new NavigationHelper(wd, this);
    sessionHelper = new SessionHelper(wd, this);
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get("http://localhost/~maksym/addressbook");
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public SessionHelper session() {
    return sessionHelper;
  }
}
