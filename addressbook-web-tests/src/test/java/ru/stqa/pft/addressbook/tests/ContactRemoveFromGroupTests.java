package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by maksym on 9/3/16.
 */
public class ContactRemoveFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (APP_MANAGER.db().groups().size() == 0) {
      APP_MANAGER.goTo().groupPage();
      APP_MANAGER.group().create(new GroupData()
              .withName("groupName")
              .withHeader("header")
              .withFooter("footer"));
    }
  }

  @Test
  public void testRemoveContactFromGroup() {

    ContactData contact;
    Contacts allContactsBefore = APP_MANAGER.db().contacts();
    Groups allGroups = APP_MANAGER.db().groups();
    GroupData groupToAddContactTo = allGroups.iterator().next();

    APP_MANAGER.contact().create(new ContactData()
            .withFirstName("contactFirstName")
            .withLastName("contactLastName")
            .withFirstAddress("address")
            .withHomePhone("123456789")
            .withFirstEmail("email@t.com")
            .inGroup(groupToAddContactTo));
    Contacts allContactsAfter = APP_MANAGER.db().contacts();
    if (allContactsBefore.size() == 0) {
      contact = allContactsAfter.iterator().next();
    } else {
      allContactsAfter.removeAll(allContactsBefore);
      contact = allContactsAfter.iterator().next();

    }

    Groups contactGroupsInitial = APP_MANAGER.db().contactGroups(contact);

    APP_MANAGER.goTo().homePage();
    APP_MANAGER.contact()
            .filterGroups(groupToAddContactTo)
            .selectContactById(contact.getId())
            .removeContactFromGroup();

    Groups contactGroupsAfterRemove = APP_MANAGER.db().contactGroups(contact);

    assertThat(contactGroupsInitial.size(), equalTo(contactGroupsAfterRemove.size() + 1));
  }
}
