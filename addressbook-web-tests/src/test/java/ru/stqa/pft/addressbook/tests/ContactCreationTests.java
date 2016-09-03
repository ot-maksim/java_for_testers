package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsAsXml() throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String line;
      String xml = "";
      while ((line = br.readLine()) != null) {
        xml += line;
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsAsJson() throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String line;
      String json = "";
      while ((line = br.readLine()) != null) {
        json += line;
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType()); // List<ContactData>.class

      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (APP_MANAGER.db().groups().size() == 0) {
      APP_MANAGER.goTo().groupPage();
      APP_MANAGER.group().create(new GroupData()
              .withName("test1")
              .withHeader("test2")
              .withFooter("test3"));
    }
  }

  @Test(dataProvider = "validGroupsAsJson")
  public void testContactCreation(ContactData contact) {
    Contacts before = APP_MANAGER.db().contacts();
    Groups groups = APP_MANAGER.db().groups();


    APP_MANAGER.goTo().homePage();
    APP_MANAGER.contact().create(contact.inGroup(groups.iterator().next()));

    Contacts after = APP_MANAGER.db().contacts();

    assertThat(before.size() + 1, equalTo(after.size()));
    assertThat(before.withAdded(contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt())), equalTo(after));

    verifyContactListInUI();
  }
}
