package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by maksym on 7/25/16.
 */
public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd, ApplicationManager applicationManager) {
    super(wd, applicationManager);
  }

  public void goToGroupPage() {
    ;
    click(By.xpath(".//*[@id='content']/div/i/a"));
  }

  public void initCreation() {
    click(By.name("new"));
  }

  public void fillForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void submitCreation() {
    click(By.name("submit"));
  }

  public void selectGroupById(int id) {
    webDriver().findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void submitDeletion() {
    click(By.xpath("//div[@id='content']/form/input[5]"));
  }

  public void initModification() {
    click(By.name("edit"));
  }

  public void submitModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    initCreation();
    fillForm(group);
    submitCreation();
    goToGroupPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initModification();
    fillForm(group);
    submitModification();
    goToGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    submitDeletion();
    goToGroupPage();
  }

  public Set<GroupData> all() {
    Set<GroupData> groups = new HashSet<>();
    List<WebElement> elements = webDriver().findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withId(id).withName(name);
      groups.add(group);
    }
    return groups;
  }
}
