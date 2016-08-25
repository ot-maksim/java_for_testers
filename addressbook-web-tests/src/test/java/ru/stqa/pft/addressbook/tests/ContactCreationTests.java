package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    APP_MANAGER.goTo().homePage();
    Contacts before = APP_MANAGER.contact().all();
    ContactData contact = new ContactData().withFirstName("firstName4").withLastName("lastName4").withAddress("address4").
            withHomePhone("123456789").withEmail("email3@t.com").withGroup("test1");
    APP_MANAGER.contact().create(contact);
    Contacts after = APP_MANAGER.contact().all();

    assertThat(before.size() + 1, equalTo(after.size()));
    assertThat(before.withAdded(contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt())), equalTo(after));
  }
}
