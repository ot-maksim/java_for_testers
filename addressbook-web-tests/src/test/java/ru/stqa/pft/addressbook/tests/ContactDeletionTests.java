package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by maksym on 7/25/16.
 */
public class ContactDeletionTests extends TestBase {
  @Test (enabled = false)
  public void testContactDeletion(){
    APP_MANAGER.getNavigationHelper().goToHomePage();

    if(!APP_MANAGER.getContactHelper().isThereAnyContact()){
      APP_MANAGER.getContactHelper().createContact(new ContactData("firstName3", "lastName3", "address3", "123456789", "email3@t.com", "test1"));
      APP_MANAGER.getNavigationHelper().goToHomePage();
    }
    List<ContactData> before = APP_MANAGER.getContactHelper().getContactList();
    APP_MANAGER.getContactHelper().selectContact(before.size());
    APP_MANAGER.getContactHelper().deleteContacts();
    APP_MANAGER.getContactHelper().acceptContactsDeletion();
    APP_MANAGER.getNavigationHelper().goToHomePage();
    List<ContactData> after = APP_MANAGER.getContactHelper().getContactList();

    Assert.assertEquals(before.size() - 1, after.size());

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
