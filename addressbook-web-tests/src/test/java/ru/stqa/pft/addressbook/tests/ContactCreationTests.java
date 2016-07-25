package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.initContactCreation();
    app.fillContactCreationForm(new ContactData("firstName3", "lastName3", "address3", "123456789", "email3@t.com"));
    app.submitContactCreation();
  }

}
