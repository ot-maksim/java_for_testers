package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by maksym on 7/25/16.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean isContactCreation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhoneNumber());
    type(By.name("email"), contactData.getEmail());

    if (isContactCreation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")), "group selection element should NOT exist on contact modification form");
    }

//    if (isElementPresent(By.name("new_group"))) {
//      new Select(wd.findElement(By.name("new_group"))).selectByValue(contactData.getGroup());
//    }
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void selectContact() {
    click(By.xpath("//form[2]/table/tbody/tr[2]/td[1]/input"));
  }

  public void deleteContacts() {
    click(By.xpath("//*[@id='content']/form[2]/div[2]/input"));
  }

  public void acceptContactsDeletion() {
    wd.switchTo().alert().accept();
  }

  public boolean isThereAnyContact() {
      return isElementPresent(By.xpath("//form[2]/table/tbody/tr[2]/td[1]/input"));
  }

  public void createContact(ContactData contact, boolean isContactCreation) {
    initContactCreation();
    fillContactForm(contact, isContactCreation);
    submitContactCreation();
  }
}
