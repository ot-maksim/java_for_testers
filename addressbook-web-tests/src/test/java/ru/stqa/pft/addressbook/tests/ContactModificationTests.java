package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by maksym on 7/25/16.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod(enabled = false)
  public void ensurePreconditions() {
    APP_MANAGER.goTo().homePage();
    if (APP_MANAGER.contact().list().size() == 0) {
      APP_MANAGER.contact().create(new ContactData("firstName3", "lastName3", "address3", "123456789", "email3@t.com", "test1"));
      APP_MANAGER.goTo().homePage();
    }
  }

  @Test(enabled = false)
  public void testContactModification() {

    List<ContactData> before = APP_MANAGER.contact().list();
    ContactData contact = new ContactData("firstName123", "lastName123", "address123", "123456789", "email123@t.com", null);
    int index = before.size();

    APP_MANAGER.contact().modify(contact, index);

    List<ContactData> after = APP_MANAGER.contact().list();

    Assert.assertEquals(before.size(), after.size());

    before.remove(before.size() - 1);
    before.add(contact);

    Comparator<? super ContactData> byLastName = (contact1, contact2) -> contact1.getLastName().compareTo(contact2.getLastName());
    before.sort(byLastName);
    after.sort(byLastName);

    Assert.assertEquals(before, after);
  }

}