package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    initContactCreation();
    fillContactCreationForm(new ContactData("firstName3", "lastName3", "address3", "123456789", "email3@t.com"));
    submitContactCreation();
  }

}
