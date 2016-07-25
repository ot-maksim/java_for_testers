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
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactCreationForm(new ContactData("firstName123", "lastName123", "address123", "123456789", "email123@t.com"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().goToHomePage();
  }
}
