package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by maksym on 8/27/16.
 */
public class ContactDetailsTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    APP_MANAGER.goTo().homePage();
    if (APP_MANAGER.contact().all().size() == 0) {
      APP_MANAGER.contact().create(new ContactData()
              .withFirstName("firstName1")
              .withLastName("lastName1")
              .withFirstAddress("address1")
              .withFirstEmail("e1@test.com")
              .withSecondEmail("e2@t.com")
              .withThirdEmail("e3@t.com")
              .withHomePhone("111")
              .withMobilePhone("222")
              .withWorkPhone("333"));
    }
  }

  @Test
  public void testContactDetails() {
    ContactData contact = APP_MANAGER.contact().all().iterator().next();
    String infoFromDetails = APP_MANAGER.contact().getInfoFromDetails(contact);
    APP_MANAGER.goTo().homePage();
    ContactData contactInfoFromEditForm = APP_MANAGER.contact().infoFromEditForm(contact);

    if(!contactInfoFromEditForm.getLastName().isEmpty()) {
      assertThat(infoFromDetails.contains(contactInfoFromEditForm.getLastName()),is(true));
    }

    if(!contactInfoFromEditForm.getFirstName().isEmpty()) {
      assertThat(infoFromDetails.contains(contactInfoFromEditForm.getFirstName()),is(true));
    }

    if(!contactInfoFromEditForm.getFirstAddress().isEmpty()) {
      assertThat(infoFromDetails.contains(contactInfoFromEditForm.getFirstAddress()),is(true));
    }

    if(!contactInfoFromEditForm.getFirstEmail().isEmpty()) {
      assertThat(infoFromDetails.contains(contactInfoFromEditForm.getFirstEmail()),is(true));
    }

    if(!contactInfoFromEditForm.getSecondEmail().isEmpty()) {
      assertThat(infoFromDetails.contains(contactInfoFromEditForm.getSecondEmail()),is(true));
    }

    if(!contactInfoFromEditForm.getThirdEmail().isEmpty()) {
      assertThat(infoFromDetails.contains(contactInfoFromEditForm.getThirdEmail()),is(true));
    }

    if(!contactInfoFromEditForm.getHomePhone().isEmpty()) {
      assertThat(infoFromDetails.contains(contactInfoFromEditForm.getHomePhone()),is(true));
    }

    if(!contactInfoFromEditForm.getMobilePhone().isEmpty()) {
      assertThat(infoFromDetails.contains(contactInfoFromEditForm.getMobilePhone()),is(true));
    }

    if(!contactInfoFromEditForm.getWorkPhone().isEmpty()) {
      assertThat(infoFromDetails.contains(contactInfoFromEditForm.getWorkPhone()),is(true));
    }
  }
}
