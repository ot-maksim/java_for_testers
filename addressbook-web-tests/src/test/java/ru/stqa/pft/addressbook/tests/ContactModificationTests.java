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

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();

    if(!app.getContactHelper().isThereAnyContact()){
      app.getContactHelper().createContact(new ContactData("firstName3", "lastName3", "address3", "123456789", "email3@t.com", "test1"));
      app.getNavigationHelper().goToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size());

    ContactData contact = new ContactData("firstName123", "lastName123", "address123", "123456789", "email123@t.com", null);
    app.getContactHelper().fillContactForm(contact, false);

    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(before.size(), after.size());

    before.remove(before.size() - 1);
    before.add(contact);

    Comparator<? super ContactData> byLastName = (contact1, contact2) -> contact1.getLastName().compareTo(contact2.getLastName());
    before.sort(byLastName);
    after.sort(byLastName);

    Assert.assertEquals(before, after);
  }
}