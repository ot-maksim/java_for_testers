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
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private DbHelper dbHelper;
  private NavigationHelper navigationHelper;
  private ManageHelper manageHelper;
  private SessionHelper sessionHelper;
  private SoapHelper soapHelper;


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

  public HttpSessionHelper newSession() {
    return new HttpSessionHelper(this);
  }

  public RegistrationHelper registration() {
    if (registration == null) {
      registration = new RegistrationHelper(this);
    }
    return registration;
  }

  public FtpHelper ftp(){
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public MailHelper mail() {
    if (mailHelper == null) {
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public DbHelper db() {
    if (dbHelper == null) {
      dbHelper = new DbHelper();
    }
    return dbHelper;
  }

  public NavigationHelper goTo() {
    if (navigationHelper == null) {
      navigationHelper = new NavigationHelper(this);
    }
    return navigationHelper;
  }

  public ManageHelper manage() {
    if (manageHelper == null) {
      manageHelper = new ManageHelper(this);
    }
    return manageHelper;
  }

  public SessionHelper session() {
    if (sessionHelper == null) {
      sessionHelper = new SessionHelper(this);
    }
    return sessionHelper;
  }

  public SoapHelper soap() {
    if (soapHelper == null) {
      soapHelper = new SoapHelper(this);
    }
    return soapHelper;
  }
}
