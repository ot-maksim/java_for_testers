package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by maksym on 7/25/16.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (APP_MANAGER.db().contacts().size() == 0) {
      APP_MANAGER.goTo().homePage();
      APP_MANAGER.contact().create(new ContactData()
              .withFirstName("firstName3")
              .withLastName("lastName3")
              .withFirstAddress("address3")
              .withHomePhone("123456789")
              .withFirstEmail("email3@t.com")
              .withGroup("test1"));
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = APP_MANAGER.db().contacts();
    ContactData deletedContact = before.iterator().next();

    APP_MANAGER.goTo().homePage();
    APP_MANAGER.contact().delete(deletedContact);

    Contacts after = APP_MANAGER.db().contacts();

    assertThat(before.size() - 1, equalTo(after.size()));
    assertThat(before.without(deletedContact), equalTo(after));

    verifyContactListInUI();
  }
}
