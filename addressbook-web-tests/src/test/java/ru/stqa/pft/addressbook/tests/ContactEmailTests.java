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
public class ContactEmailTests extends TestBase {
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
  public void testContactEmails() {
    ContactData contactFromHomePage = APP_MANAGER.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = APP_MANAGER.contact().infoFromEditForm(contactFromHomePage);

    assertThat(contactFromHomePage.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getFirstEmail(), contact.getSecondEmail(), contact.getThirdEmail())
            .stream()
            .filter((s) -> !s.isEmpty())
            .filter((s) -> !s.replaceAll("^\\s*$","").isEmpty())
            .map(ContactEmailTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private static String cleaned(String emails) {
    return emails.trim();

  }
}
