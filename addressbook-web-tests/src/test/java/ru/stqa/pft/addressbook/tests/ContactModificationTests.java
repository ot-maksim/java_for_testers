package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by maksym on 7/25/16.
 */
public class ContactModificationTests extends TestBase {

  @Test (enabled = false)
  public void testContactModification(){
    APP_MANAGER.getNavigationHelper().goToHomePage();

    if(!APP_MANAGER.getContactHelper().isThereAnyContact()){
      APP_MANAGER.getContactHelper().createContact(new ContactData("firstName3", "lastName3", "address3", "123456789", "email3@t.com", "test1"));
      APP_MANAGER.getNavigationHelper().goToHomePage();
    }
    List<ContactData> before = APP_MANAGER.getContactHelper().getContactList();
    APP_MANAGER.getContactHelper().initContactModification(before.size());

    ContactData contact = new ContactData("firstName123", "lastName123", "address123", "123456789", "email123@t.com", null);
    APP_MANAGER.getContactHelper().fillContactForm(contact, false);

    APP_MANAGER.getContactHelper().submitContactModification();
    APP_MANAGER.getContactHelper().returnToHomePage();
    List<ContactData> after = APP_MANAGER.getContactHelper().getContactList();

    Assert.assertEquals(before.size(), after.size());

    before.remove(before.size() - 1);
    before.add(contact);

    Comparator<? super ContactData> byLastName = (contact1, contact2) -> contact1.getLastName().compareTo(contact2.getLastName());
    before.sort(byLastName);
    after.sort(byLastName);

    Assert.assertEquals(before, after);
  }
}