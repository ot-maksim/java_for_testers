package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by maksym on 7/25/16.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    APP_MANAGER.goTo().homePage();
    if (APP_MANAGER.contact().list().size() == 0) {
      APP_MANAGER.contact().create(new ContactData().withFirstName("firstName3").withLastName("lastName3").withAddress("address3").
              withHomePhoneNumber("123456789").withEmail("email3@t.com").withGroup("test1"));
    }
  }

  @Test
  public void testContactDeletion() {
    List<ContactData> before = APP_MANAGER.contact().list();
    int index = before.size();

    APP_MANAGER.contact().delete(index);

    List<ContactData> after = APP_MANAGER.contact().list();

    Assert.assertEquals(before.size() - 1, after.size());

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
