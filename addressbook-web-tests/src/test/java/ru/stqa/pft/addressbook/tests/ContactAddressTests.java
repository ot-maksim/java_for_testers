package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by maksym on 8/25/16.
 */
public class ContactAddressTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    APP_MANAGER.goTo().homePage();
    if (APP_MANAGER.contact().all().size() == 0) {
      APP_MANAGER.contact().create(new ContactData()
              .withFirstName("firstName1")
              .withLastName("lastName1")
              .withFirstAddress("address1"));
    }
  }

  @Test
  public void testContactAdresses() {
    ContactData contactFromHomePage = APP_MANAGER.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = APP_MANAGER.contact().infoFromEditForm(contactFromHomePage);

    assertThat(contactFromHomePage.getFirstAddress(), equalTo(mergeAdresses(contactInfoFromEditForm)));

  }

  private String mergeAdresses(ContactData contact) {
    return Arrays.asList(contact.getFirstAddress())
            .stream()
            .filter((s) -> !s.isEmpty())
            .map(ContactAddressTests::cleaned)
            .collect(Collectors.joining());
  }

  private static String cleaned(String address) {
    return address.replaceAll("(?m)^[ ]+", "")
            .replaceAll("(?m)[ ]+$", "")
            .replaceAll("(?m)[ ]{2,}", " ")
            .replaceAll("\\n+$", "");

  }
}
