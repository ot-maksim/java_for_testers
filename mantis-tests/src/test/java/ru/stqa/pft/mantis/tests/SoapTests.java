package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

/**
 * Created by maksym on 9/12/16.
 */
public class SoapTests {
  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mantisConnectPort = new MantisConnectLocator()
            .getMantisConnectPort(new URL("http://localhost/~maksym/mantisbt-1.3.1/api/soap/mantisconnect.php"));
    ProjectData[] projects = mantisConnectPort.mc_projects_get_user_accessible("administrator", "qwerty");
    System.out.println(projects.length);
    for (ProjectData project : projects) {
      System.out.println(project.getName());
    }
  }
}