package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by maksym on 7/25/16.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();

    if(!app.getContactHelper().isThereAnyContact()){
      app.getContactHelper().createContact(new ContactData("firstName3", "lastName3", "address3", "123456789", "email3@t.com", "test1"), true);
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("firstName123", "lastName123", "address123", "123456789", "email123@t.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}