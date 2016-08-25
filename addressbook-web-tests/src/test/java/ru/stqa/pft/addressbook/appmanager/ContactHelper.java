package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

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
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());

    if (isContactCreation) {
      try {
        new Select(webDriver().findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } catch (NoSuchElementException ex) {
        appManager().goTo().groupPage();
        GroupData group = new GroupData().withName(contactData.getGroup());
        appManager().group().create(group);
        initCreation();
        new Select(webDriver().findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")), "group selection element should NOT exist on contact modification form");
    }
  }

  public void submitCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void initModificationById(int id) {

    //several options how to locate the same element

    //option 1
//    WebElement checkbox = webDriver().findElement(By.cssSelector(String.format("input[id='%d']",id)));
//    WebElement row = checkbox.findElement(By.xpath("./../.."));
//    List<WebElement> cells = row.findElements(By.tagName("td"));
//    cells.get(7).findElement(By.tagName("a")).click();

    //option 2
//    click(By.xpath(String.format(".//input[@id='%d']/../../td[8]/a", id)));

    //option 3
//    click(By.xpath(String.format(".//tr[.//input[@value='%s']]/td[8]/a", id)));

    //option 4
    click(By.xpath(".//a[@href='edit.php?id=" + id + "']"));
  }

  public void modify(ContactData contact) {
    initModificationById(contact.getId());
    fillForm(contact, false);
    submitModification();
    goToHomePage();
  }

  public void submitModification() {
    click(By.name("update"));
  }

  public void goToHomePage() {
    int elements = webDriver().findElements(By.xpath(".//*[@id='content']/div/i/a")).size();

    if (elements == 1) {
      click(By.xpath(".//*[@id='content']/div/i/a[1]"));
    } else if (elements == 2) {
      click(By.xpath(".//*[@id='content']/div/i/a[2]"));
    }
  }

  public void addNextGroup() {
    click(By.xpath(".//*[@id='content']/div/i/a[1]"));
  }

  public void selectById(int id) {
    click(By.xpath(".//input[@id='" + id + "']"));
  }

  public void submitDeletion() {
    click(By.xpath("//*[@id='content']/form[2]/div[2]/input"));
  }

  public void acceptDeletion() {
    webDriver().switchTo().alert().accept();
  }

  public void create(ContactData contact) {
    initCreation();
    fillForm(contact, true);
    submitCreation();
    goToHomePage();
  }

  public void delete(ContactData contact) {
    selectById(contact.getId());
    submitDeletion();
    acceptDeletion();
    appManager().goTo().homePage();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    int elements = webDriver().findElements(By.xpath(".//*[@id='maintable']//input[@type='checkbox']")).size();
    int index = 0;
    for (int i = 1; i <= elements; i++) {
      index = i + 1;
      String lastName = webDriver().findElement(By.xpath(".//*[@id='maintable']/tbody/tr[" + index + "]/td[2]")).getText();
      String firstName = webDriver().findElement(By.xpath(".//*[@id='maintable']/tbody/tr[" + index + "]/td[3]")).getText();
      int id = Integer.parseInt(webDriver().findElement(By.xpath(".//*[@id='maintable']/tbody/tr[" + index + "]/td[1]/input")).getAttribute("id"));
      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);
      contacts.add(contact);
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initModificationById(contact.getId());
    String firstName = webDriver().findElement(By.name("firstname")).getAttribute("value");
    String lastName = webDriver().findElement(By.name("lastname")).getAttribute("value");
    String homePhoneNumber = webDriver().findElement(By.name("home")).getAttribute("value");
    String mobilePhoneNumber = webDriver().findElement(By.name("mobile")).getAttribute("value");
    String workPhoneNumber = webDriver().findElement(By.name("work")).getAttribute("value");
    webDriver().navigate().back();

    return new ContactData().withFirstName(firstName).withLastName(lastName).withHomePhone(homePhoneNumber).
            withMobilePhone(mobilePhoneNumber).withWorkPhone(workPhoneNumber);
  }
}
