package ru.stqa.pft.addressbook.tests;

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
    String infoFromDetailsPage = APP_MANAGER.contact().infoFromDetailsPage(contact);
    ContactData infoFromEditForm = APP_MANAGER.contact().infoFromEditForm(contact);

    if(!infoFromEditForm.getLastName().isEmpty()) {
      assertThat(infoFromDetailsPage.contains(infoFromEditForm.getLastName()),is(true));
    }

    if(!infoFromEditForm.getFirstName().isEmpty()) {
      assertThat(infoFromDetailsPage.contains(infoFromEditForm.getFirstName()),is(true));
    }

    if(!infoFromEditForm.getFirstAddress().isEmpty()) {
      assertThat(infoFromDetailsPage.contains(infoFromEditForm.getFirstAddress()),is(true));
    }

    if(!infoFromEditForm.getFirstEmail().isEmpty()) {
      assertThat(infoFromDetailsPage.contains(infoFromEditForm.getFirstEmail()),is(true));
    }

    if(!infoFromEditForm.getSecondEmail().isEmpty()) {
      assertThat(infoFromDetailsPage.contains(infoFromEditForm.getSecondEmail()),is(true));
    }

    if(!infoFromEditForm.getThirdEmail().isEmpty()) {
      assertThat(infoFromDetailsPage.contains(infoFromEditForm.getThirdEmail()),is(true));
    }

    if(!infoFromEditForm.getHomePhone().isEmpty()) {
      assertThat(infoFromDetailsPage.contains(infoFromEditForm.getHomePhone()),is(true));
    }

    if(!infoFromEditForm.getMobilePhone().isEmpty()) {
      assertThat(infoFromDetailsPage.contains(infoFromEditForm.getMobilePhone()),is(true));
    }

    if(!infoFromEditForm.getWorkPhone().isEmpty()) {
      assertThat(infoFromDetailsPage.contains(infoFromEditForm.getWorkPhone()),is(true));
    }
  }
}
