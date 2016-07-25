package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

  public void fillContactCreationForm(ContactData contactData) {
    type("firstname", contactData.getFirstName());
    type("lastname", contactData.getLastName());
    type("address", contactData.getAddress());
    type("home", contactData.getHomePhoneNumber());
    type("email", contactData.getEmail());
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

  public void goToHomePage() {
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
}
