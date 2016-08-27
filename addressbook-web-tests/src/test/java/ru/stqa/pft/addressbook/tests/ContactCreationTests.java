package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    APP_MANAGER.goTo().homePage();
    Contacts before = APP_MANAGER.contact().all();
    File photo = new File("src/test/resources/pic.jpg");
    ContactData contact = new ContactData()
            .withFirstName("firstName4")
            .withLastName("lastName4")
            .withFirstAddress("address4")
            .withHomePhone("123456789")
            .withFirstEmail("email3@t.com")
            .withPhoto(photo);
    APP_MANAGER.contact().create(contact);
    Contacts after = APP_MANAGER.contact().all();

    assertThat(before.size() + 1, equalTo(after.size()));
    assertThat(before.withAdded(contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt())), equalTo(after));
  }
}
