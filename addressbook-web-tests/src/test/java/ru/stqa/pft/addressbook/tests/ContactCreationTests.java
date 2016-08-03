package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("firstName3", "lastName3", "address3", "123456789", "email3@t.com", "test1"), true);
    app.getContactHelper().submitContactCreation();
  }

}
