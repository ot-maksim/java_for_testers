package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksym on 8/28/16.
 */
public class ContactDataGenerator {
  @Parameter(names = "-c", description = "Contact count")
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file;
  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);

    if ("csv".equals(format)) {
      saveAsCsv(contacts, new File(file));
    } else if ("xml".equals(format)) {
      saveAsXml(contacts, new File(file));
    } else if ("json".equals(format)) {
      saveAsJson(contacts, new File(file));
    } else {
      if (format != null) {
        System.out.println("Unrecognized format " + format);
      } else {
        System.out.println("Provide parameters to generate test data");
      }
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contactList = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      ContactData contact = new ContactData()
              .withFirstName(String.format("firstName %s", i))
              .withLastName(String.format("lastName %s", i))
              .withPath("src/test/resources/pic.jpg")
              .withFirstAddress(String.format("address %s", i))
              .withHomePhone(String.format("123456789 %s", i))
              .withMobilePhone(String.format("123456789 %s", i))
              .withWorkPhone(String.format("123456789 %s", i))
              .withFirstEmail(String.format("firstEmail%s@t.com", i))
              .withSecondEmail(String.format("secondEmail%s@t.com", i))
              .withThirdEmail(String.format("thirdEmail%s@t.com", i));
      contactList.add(contact);
    }
    return contactList;
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(ContactData.class);
    String xml = xStream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {

    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPath(),
                contact.getFirstAddress(),
                contact.getHomePhone(),
                contact.getMobilePhone(),
                contact.getWorkPhone(),
                contact.getFirstEmail(),
                contact.getSecondEmail(),
                contact.getThirdEmail()));
      }
    }
  }
}
