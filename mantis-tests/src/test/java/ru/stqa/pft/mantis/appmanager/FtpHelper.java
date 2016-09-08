package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by maksym on 9/8/16.
 */
public class FtpHelper {
  private final ApplicationManager appManager;
  private FTPClient ftp;

  public FtpHelper(ApplicationManager appManager) {
    this.appManager = appManager;
    ftp = new FTPClient();
  }

  public void upload(File file, String target, String backup) throws IOException {
    ftp.connect(appManager.getProperty("ftp.host"));
    ftp.login(appManager.getProperty("ftp.login"), appManager.getProperty("ftp.password"));
    String workDir = ftp.printWorkingDirectory() + "/Sites/mantisbt-1.3.1/config/";
    ftp.changeWorkingDirectory(workDir);
    ftp.deleteFile(backup);
    ftp.rename(target, backup);
    ftp.enterLocalPassiveMode();
    ftp.storeFile(target, new FileInputStream(file));
    ftp.disconnect();
  }

  public void restore(String backup, String target) throws IOException {
    ftp.connect(appManager.getProperty("ftp.host"));
    ftp.login(appManager.getProperty("ftp.login"), appManager.getProperty("ftp.password"));
    String workDir = ftp.printWorkingDirectory() + "/Sites/mantisbt-1.3.1/config/";
    ftp.changeWorkingDirectory(workDir);
    ftp.deleteFile(target);
    ftp.rename(backup, target);
    ftp.disconnect();
  }
}
