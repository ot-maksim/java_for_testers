package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by maksym on 8/24/16.
 */
public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    APP_MANAGER.goTo().homePage();
    if (APP_MANAGER.contact().all().size() == 0) {
      APP_MANAGER.contact().create(new ContactData().withFirstName("firstName1").withLastName("lastName1").withAddress("address1").
              withHomePhone("123456789").withEmail("email1@t.com").withGroup("test1").withHomePhone("111").
              withMobilePhone("222").withWorkPhone("333"));
    }
  }

  @Test
  public void testContactPhones() {
    APP_MANAGER.goTo().homePage();
    ContactData contact = APP_MANAGER.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = APP_MANAGER.contact().infoFromEditForm(contact);

    assertThat(cleaned(contactInfoFromEditForm.getHomePhone()), equalTo(contact.getHomePhone()));
    assertThat(cleaned(contactInfoFromEditForm.getMobilePhone()), equalTo(contact.getMobilePhone()));
    assertThat(cleaned(contactInfoFromEditForm.getWorkPhone()), equalTo(contact.getWorkPhone()));
  }

  private String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
