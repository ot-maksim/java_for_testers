package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by maksym on 9/3/16.
 */
public class ContactAddToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (APP_MANAGER.db().groups().size() == 0) {
      APP_MANAGER.goTo().groupPage();
      APP_MANAGER.group().create(new GroupData()
              .withName("groupName")
              .withHeader("header")
              .withFooter("footer"));
    }

    if (APP_MANAGER.db().contacts().size() == 0) {
      APP_MANAGER.goTo().homePage();
      APP_MANAGER.contact().create(new ContactData()
              .withFirstName("contactFirstName")
              .withLastName("contactLastName")
              .withFirstAddress("address")
              .withHomePhone("123456789")
              .withFirstEmail("email@t.com"));
    }
  }

  @Test
  public void testAddContactToGroup() {
    GroupData groupToAddContactTo;
    Contacts contacts = APP_MANAGER.db().contacts();
    ContactData contact = contacts.iterator().next();
    Groups contactGroupsInitial = APP_MANAGER.db().contactGroups(contact);

    Groups allGroups = APP_MANAGER.db().groups();

    allGroups.removeAll(contactGroupsInitial);

    if (allGroups.size() == 0) {
      allGroups = APP_MANAGER.db().groups();
      APP_MANAGER.goTo().groupPage();
      APP_MANAGER.group().create(new GroupData()
              .withName("groupNameTTT")
              .withHeader("header")
              .withFooter("footer"));
      Groups allGroupsUpdated = APP_MANAGER.db().groups();
      allGroupsUpdated.removeAll(allGroups);
      assertThat(allGroupsUpdated.size(), is(1));
      groupToAddContactTo = allGroupsUpdated.iterator().next();
    } else {
      groupToAddContactTo = allGroups.iterator().next();
    }

    APP_MANAGER.goTo().homePage();
    APP_MANAGER.contact()
            .selectContactById(contact.getId())
            .selectGroupById(groupToAddContactTo.getId()).addToGroup();

    Groups contactGroupsAfterAdd = APP_MANAGER.db().contactGroups(contact);

    assertThat(contactGroupsAfterAdd.size(), equalTo(contactGroupsInitial.size() + 1));

  }
}
