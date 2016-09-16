package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Objects;


/**
 * Created by maksym on 7/25/16.
 */
public class TestBase {

  static final ApplicationManager APP_MANAGER =
          new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  private boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    IssueData issueById = APP_MANAGER.soap().getIssueById(issueId);
    int resolutionId = issueById.getResolution().getId().intValue();
    return resolutionId != 20;
  }

  void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  @BeforeSuite
  public void setUp() throws Exception {
    APP_MANAGER.init();
    APP_MANAGER.ftp().upload(new File("src/test/resources/config_inc.php"), "Sites/mantisbt-1.3.1/config/", "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    APP_MANAGER.ftp().restore("Sites/mantisbt-1.3.1/config/", "config_inc.php.bak", "config_inc.php");
    APP_MANAGER.stop();
  }


}
