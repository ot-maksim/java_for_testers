package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

/**
 * Created by maksym on 7/25/16.
 */
public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd, ApplicationManager applicationManager) {
    super(wd, applicationManager);
  }

  public void returnToGroupPage() {
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
    click(By.cssSelector("input[value='" + id + "']"));
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

  public int count() {
    return webDriver().findElements(By.name("selected[]")).size();
  }

  public void create(GroupData group) {
    initCreation();
    fillForm(group);
    submitCreation();
    groupCache = null;
    returnToGroupPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initModification();
    fillForm(group);
    submitModification();
    groupCache = null;
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    submitDeletion();
    groupCache = null;
    returnToGroupPage();
  }

  Groups groupCache = null;

  public Groups all() {
    if (groupCache != null) {
      return new Groups(groupCache);
    }
    groupCache = new Groups();
    List<WebElement> elements = webDriver().findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withId(id).withName(name);
      groupCache.add(group);
    }
    return new Groups(groupCache);
  }
}
