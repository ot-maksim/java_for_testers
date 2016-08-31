package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by maksym on 8/24/16.
 */
public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (APP_MANAGER.db().contacts().size() == 0) {
      APP_MANAGER.goTo().homePage();
      APP_MANAGER.contact().create(new ContactData()
              .withFirstName("firstName1")
              .withLastName("lastName1")
              .withFirstAddress("address1")
              .withHomePhone("123456789")
              .withFirstEmail("email1@t.com")
              .withGroup("test1")
              .withHomePhone("111")
              .withMobilePhone("222")
              .withWorkPhone("333"));
    }
  }

  @Test
  public void testContactPhones() {
    APP_MANAGER.goTo().homePage();
    ContactData contactFromHomePage = APP_MANAGER.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = APP_MANAGER.contact().infoFromEditForm(contactFromHomePage);

    assertThat(contactFromHomePage.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
//   option 1 before java8

//    String result = "";
//    if (contact.getHomePhone() != null) {
//      result = result + contact.getHomePhone() + "\n";
//    }
//    if (contact.getMobilePhone() != null) {
//      result = result + contact.getMobilePhone() + "\n";
//    }
//    if (contact.getWorkPhone() != null) {
//      result = result + contact.getWorkPhone();
//    }
//    return cleaned(result);

//    option 2 - using functions of java8

    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.isEmpty())
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
