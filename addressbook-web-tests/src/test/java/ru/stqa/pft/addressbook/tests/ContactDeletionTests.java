package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by maksym on 7/25/16.
 */
public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().goToHomePage();
    if(!app.getContactHelper().isThereAnyContact()){
      app.getContactHelper().createContact(new ContactData("firstName3", "lastName3", "address3", "123456789", "email3@t.com", "test1"));
    }

    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContacts();
    app.getContactHelper().acceptContactsDeletion();
    app.getNavigationHelper().goToHomePage();
  }
}
