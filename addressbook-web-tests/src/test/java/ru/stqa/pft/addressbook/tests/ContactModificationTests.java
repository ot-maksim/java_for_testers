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

  @BeforeMethod
  public void ensurePreconditions() {
    APP_MANAGER.goTo().homePage();
    if (APP_MANAGER.contact().all().size() == 0) {
      APP_MANAGER.contact().create(new ContactData().withFirstName("firstName3").withLastName("lastName3").withAddress("address3").
              withHomePhoneNumber("123456789").withEmail("email3@t.com").withGroup("test1"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = APP_MANAGER.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("firstName123").withLastName("lastName123").
            withAddress("address123").withHomePhoneNumber("123456789").withEmail("email123@t.com");
    APP_MANAGER.contact().modify(contact);
    Contacts after = APP_MANAGER.contact().all();

    assertThat(before.size(), equalTo(after.size()));
    assertThat(before.without(modifiedContact).withAdded(contact), equalTo(after));
  }

}