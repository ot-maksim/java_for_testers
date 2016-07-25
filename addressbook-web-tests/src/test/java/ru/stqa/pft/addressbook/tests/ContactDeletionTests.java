package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by maksym on 7/25/16.
 */
public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContacts();
    app.getContactHelper().acceptContactsDeletion();
    app.getNavigationHelper().goToHomePage();
  }
}
