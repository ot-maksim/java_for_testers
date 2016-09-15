package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by maksym on 9/12/16.
 */
public class SoapTests extends TestBase {
  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = APP_MANAGER.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = APP_MANAGER.soap().getProjects();
    Issue issue = new Issue()
            .withSummary("Test issue1")
            .withDescription("Test issue description1")
            .withProject(projects.iterator().next());
    Issue created = APP_MANAGER.soap().addIssue(issue);
    assertThat(issue.getSummary(), equalTo(created.getSummary()));
  }

  @Test
  public void testResolutionOfTest() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(0000005);
    System.out.println("test takes place");
  }
}