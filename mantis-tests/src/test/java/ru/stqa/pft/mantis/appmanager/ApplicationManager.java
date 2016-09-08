package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by maksym on 7/25/16.
 */
public class ApplicationManager {
  private Properties properties;
  private WebDriver wd;
  private String browser;
  private RegistrationHelper registration;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public WebDriver getWebDriver() {

    if (wd == null) {
      if (Objects.equals(browser, BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (Objects.equals(browser, BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (Objects.equals(browser, BrowserType.SAFARI)) {
        wd = new SafariDriver();
      } else if (Objects.equals(browser, BrowserType.OPERA_BLINK)) {
        wd = new OperaDriver();
      }
      wd.get(properties.getProperty("web.baseUrl"));
      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }
    return wd;
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

  public void stop() {
    if (wd != null) {
      wd.quit();
    }
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public RegistrationHelper registration() {
    if (registration == null) {
      registration = new RegistrationHelper(this);
    }
    return registration;
  }
}
