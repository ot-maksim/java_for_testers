package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksym on 7/25/16.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd, ApplicationManager applicationManager) {
    super(wd, applicationManager);
  }

  public void initCreation() {
    click(By.linkText("add new"));
  }

  public void fillForm(ContactData contactData, boolean isContactCreation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhoneNumber());
    type(By.name("email"), contactData.getEmail());

    if (isContactCreation) {
      try {
        new Select(getWd().findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } catch (NoSuchElementException ex) {
        getApplicationManager().goTo().groupPage();
        GroupData group = new GroupData(contactData.getGroup(), null, null);
        getApplicationManager().group().create(group);
        initCreation();
        new Select(getWd().findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")), "group selection element should NOT exist on contact modification form");
    }

//    if (isElementPresent(By.name("new_group"))) {
//      new Select(wd.findElement(By.name("new_group"))).selectByValue(contactData.getGroup());
//    }
  }

  public void submitCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void initModification(int index) {
    click(By.xpath(".//*[@id='maintable']/tbody/tr[" + (index + 1) + "]/td[8]/a/img"));
  }

  public void modify(ContactData contact, int index) {
    initModification(index);
    fillForm(contact, false);
    submitModification();
    goToHomePage();
  }

  public void submitModification() {
    click(By.name("update"));
  }

  public void goToHomePage() {
    int elements = getWd().findElements(By.xpath(".//*[@id='content']/div/i/a")).size();

    if (elements == 1) {
      click(By.xpath(".//*[@id='content']/div/i/a[1]"));
    } else if (elements == 2) {
      click(By.xpath(".//*[@id='content']/div/i/a[2]"));
    }
  }

  public void addNextGroup() {
    click(By.xpath(".//*[@id='content']/div/i/a[1]"));
  }

  public void select(int index) {
    click(By.xpath(".//*[@id='maintable']/tbody/tr[" + (index + 1) + "]/td[1]"));
  }

  public void submitDeletion() {
    click(By.xpath("//*[@id='content']/form[2]/div[2]/input"));
  }

  public void acceptDeletion() {
    getWd().switchTo().alert().accept();
  }

  public void create(ContactData contact) {
    initCreation();
    fillForm(contact, true);
    submitCreation();
    goToHomePage();
  }

  public void delete(int index) {
    select(index);
    submitDeletion();
    acceptDeletion();
    goToHomePage();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    int elements = getWd().findElements(By.xpath(".//*[@id='maintable']/tbody/tr")).size();
    for (int i = 2; i <= elements; i++) {
      String lastName = getWd().findElement(By.xpath(".//*[@id='maintable']/tbody/tr[" + i + "]/td[2]")).getText();
      String firstName = getWd().findElement(By.xpath(".//*[@id='maintable']/tbody/tr[" + i + "]/td[3]")).getText();
      ContactData contact = new ContactData(firstName, lastName, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
