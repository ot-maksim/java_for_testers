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
public class ContactModificationTests extends TestBase {

  @BeforeMethod (alwaysRun = true)
  public void ensurePreconditions() {
    if (APP_MANAGER.db().contacts().size() == 0) {
      APP_MANAGER.goTo().homePage();
      APP_MANAGER.contact().create(new ContactData()
              .withFirstName("firstName3")
              .withLastName("lastName3")
              .withFirstAddress("address3")
              .withHomePhone("123456789")
              .withFirstEmail("email3@t.com"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = APP_MANAGER.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("firstName123")
            .withLastName("lastName123")
            .withFirstAddress("address123")
            .withHomePhone("123456789")
            .withFirstEmail("email1@t.com");

    APP_MANAGER.goTo().homePage();
    APP_MANAGER.contact().modify(contact);

    Contacts after = APP_MANAGER.db().contacts();

    assertThat(before.size(), equalTo(after.size()));
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    verifyContactListInUI();
  }

}