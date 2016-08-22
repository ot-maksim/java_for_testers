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
      APP_MANAGER.contact().create(new ContactData().withFirstName("firstName3").withLastName("lastName3").withAddress("address3").
              withHomePhoneNumber("123456789").withEmail("email3@t.com").withGroup("test1"));
      APP_MANAGER.goTo().homePage();
    }
  }

  @Test(enabled = false)
  public void testContactModification() {

    List<ContactData> before = APP_MANAGER.contact().list();
    int index = before.size();
    ContactData contact = new ContactData().withFirstName("firstName123").withLastName("lastName123").
            withAddress("address123").withHomePhoneNumber("123456789").withEmail("email123@t.com");

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