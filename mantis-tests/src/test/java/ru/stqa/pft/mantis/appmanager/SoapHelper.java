package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by maksym on 9/12/16.
 */
public class SoapHelper {
  private final ApplicationManager appManager;
  private String login;
  private String password;

  public SoapHelper(ApplicationManager appManager) {
    this.appManager = appManager;
    login = appManager.getProperty("web.adminLogin");
    password = appManager.getProperty("web.adminPassword");
  }

  public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mantisConnection = getMantisConnect();

    ProjectData[] projects = mantisConnection.mc_projects_get_user_accessible(login, password);

    return Arrays.asList(projects)
            .stream()
            .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
            .collect(Collectors.toSet());
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mantisConnection = getMantisConnect();
    String[] categories = mantisConnection.
            mc_project_get_categories(login, password, BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();

    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);


    BigInteger issueId = mantisConnection.mc_issue_add(login, password, issueData);

    IssueData createdIssueData = mantisConnection.mc_issue_get(login, password, issueId);

    return new Issue()
            .withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary())
            .withDescription(createdIssueData.getDescription())
            .withProject(new Project()
                    .withId(createdIssueData.getProject().getId().intValue())
                    .withName(createdIssueData.getProject().getName()));
  }

  public IssueData getIssueById(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mantisConnection = getMantisConnect();
    return mantisConnection.mc_issue_get(login, password, BigInteger.valueOf(issueId));

  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    String soapURL = appManager.getProperty("soap.url");
    return new MantisConnectLocator()
            .getMantisConnectPort(new URL(soapURL));
  }
}
