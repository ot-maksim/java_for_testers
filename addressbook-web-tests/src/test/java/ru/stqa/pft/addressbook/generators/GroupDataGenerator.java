package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksym on 8/27/16.
 */
public class GroupDataGenerator {
  @Parameter(names = "-c", description = "Group count")
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file;
  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    GroupDataGenerator generator = new GroupDataGenerator();
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
    List<GroupData> groups = generateGroups(count);

    if (format.equals("csv")) {
      saveAsCsv(groups, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(groups, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }

  }

  private void saveAsXml(List<GroupData> groups, File file) throws IOException {
    XStream xStream = new XStream();
//    two options how to create create aliases
//    option 1
//    xStream.alias("group", GroupData.class);
//    option 2 - it requires to add @XStreamAlias("group") annotation before class
    xStream.processAnnotations(GroupData.class);
    String xml = xStream.toXML(groups);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsCsv(List<GroupData> groups, File file) throws IOException {
//    System.out.println(new File(".").getAbsolutePath());
//    System.out.println(file.getAbsolutePath());
    Writer writer = new FileWriter(file);

    for (GroupData group : groups) {
      writer.write(String.format("%s;%s;%s\n", group.getName(), group.getFooter(), group.getHeader()));
    }
    writer.close();
  }

  private List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      GroupData group = new GroupData()
              .withName(String.format("test %d", i))
              .withHeader(String.format("header\n%d", i))
              .withFooter(String.format("footer\n%d", i));
      groups.add(group);
    }
    return groups;
  }
}
