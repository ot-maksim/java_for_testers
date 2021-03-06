package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by maksym on 7/25/16.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd, ApplicationManager applicationManager) {
    super(wd, applicationManager);
  }

  public ContactHelper initCreation() {
    click(By.linkText("add new"));
    return this;
  }

  public ContactHelper fillForm(ContactData contactData, boolean isContactCreation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getFirstAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getFirstEmail());
    type(By.name("email2"), contactData.getSecondEmail());
    type(By.name("email3"), contactData.getThirdEmail());
    if (!contactData.getPath().equals("")) {
      attach(By.name("photo"), contactData.getPath());
    }

    if (isContactCreation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(webDriver().findElement(By.name("new_group")))
                .selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")), "group selection element should NOT exist on contact modification form");
    }
    return this;
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

  public ContactHelper selectContactById(int id) {
    click(By.xpath(".//input[@id='" + id + "']"));
    return this;
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

  public void modify(ContactData contact) {
    initModificationById(contact.getId());
    fillForm(contact, false);
    submitModification();
    goToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    submitDeletion();
    acceptDeletion();
    appManager().goTo().homePage();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();

    //option 1 - me
//    int elements = webDriver().findElements(By.xpath(".//*[@id='maintable']//input[@type='checkbox']")).size();
//    int index = 0;
//    for (int i = 1; i <= elements; i++) {
//      index = i + 1;
//      String lastName = webDriver().findElement(By.xpath(".//*[@id='maintable']/tbody/tr[" + index + "]/td[2]")).getText();
//      String firstName = webDriver().findElement(By.xpath(".//*[@id='maintable']/tbody/tr[" + index + "]/td[3]")).getText();
//      int id = Integer.parseInt(webDriver().findElement(By.xpath(".//*[@id='maintable']/tbody/tr[" + index + "]/td[1]/input")).getAttribute("id"));
//      String[] allPhones = webDriver().findElement(By.xpath(".//*[@id='maintable']/tbody/tr[" + index + "]/td[6]")).getText().split("\n");
//      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withHomePhone(allPhones[0]).
//              withMobilePhone(allPhones[1]).withWorkPhone(allPhones[2]));
//    }

    //option 2 - Barancev
    List<WebElement> rows = webDriver().findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String firstAddress = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();

      contacts.add(new ContactData()
              .withId(id)
              .withLastName(lastname)
              .withFirstName(firstname)
              .withFirstAddress(firstAddress)
              .withAllEmails(allEmails)
              .withAllPhones(allPhones));
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    appManager().goTo().homePage();
    initModificationById(contact.getId());
    String firstName = webDriver().findElement(By.name("firstname")).getAttribute("value");
    String lastName = webDriver().findElement(By.name("lastname")).getAttribute("value");
    String homePhoneNumber = webDriver().findElement(By.name("home")).getAttribute("value");
    String mobilePhoneNumber = webDriver().findElement(By.name("mobile")).getAttribute("value");
    String workPhoneNumber = webDriver().findElement(By.name("work")).getAttribute("value");
    String firstAddress = webDriver().findElement(By.name("address")).getAttribute("value");
    String firstEmail = webDriver().findElement(By.name("email")).getAttribute("value");
    String secondEmail = webDriver().findElement(By.name("email2")).getAttribute("value");
    String thirdEmail = webDriver().findElement(By.name("email3")).getAttribute("value");
    webDriver().navigate().back();

    return new ContactData()
            .withLastName(lastName)
            .withFirstName(firstName)
            .withHomePhone(homePhoneNumber)
            .withMobilePhone(mobilePhoneNumber)
            .withWorkPhone(workPhoneNumber)
            .withFirstAddress(firstAddress)
            .withFirstEmail(firstEmail)
            .withSecondEmail(secondEmail)
            .withThirdEmail(thirdEmail);
  }

  public void openDetailsPageById(int id) {
    click(By.xpath(".//a[@href='view.php?id=" + id + "']"));
  }

  public String infoFromDetailsPage(ContactData contact) {
    openDetailsPageById(contact.getId());
    return webDriver().findElement(By.id("content")).getText();
  }

  public ContactHelper selectGroupById(int id) {
    new Select(webDriver().findElement(By.name("to_group"))).selectByValue(String.valueOf(id));
    return this;
  }

  public void addToGroup() {
    webDriver().findElement(By.name("add")).click();
  }

  public ContactHelper filterGroups(GroupData group) {
    new Select(webDriver()
            .findElement(By.xpath(".//form[@id='right']/select[@name='group']")))
            .selectByValue(String.valueOf(group.getId()));
    return this;
  }

  public ContactHelper removeContactFromGroup() {
    click(By.xpath(".//*[@id='content']//input[@name='remove']"));
    return this;
  }
}
