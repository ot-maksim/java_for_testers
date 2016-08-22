package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {
  @Test(enabled = false)
  public void testContactCreation() {
    APP_MANAGER.goTo().homePage();
    List<ContactData> before = APP_MANAGER.contact().list();
    ContactData contact = new ContactData("firstName4", "lastName4", "address4", "123456789", "email3@t.com", "test1");

    APP_MANAGER.contact().create(contact);

    List<ContactData> after = APP_MANAGER.contact().list();

    Assert.assertEquals(before.size() + 1, after.size());

    before.add(contact);

    Comparator<? super ContactData> byLastName = (contact1, contact2) -> contact1.getLastName().compareTo(contact2.getLastName());
    before.sort(byLastName);
    after.sort(byLastName);

    Assert.assertEquals(before, after);
  }
}
