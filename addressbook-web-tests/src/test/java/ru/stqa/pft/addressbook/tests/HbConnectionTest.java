package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by maksym on 8/29/16.
 */
public class HbConnectionTest {

  private SessionFactory sessionFactory;

  @BeforeClass
  protected void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    } catch (Exception e) {
      e.printStackTrace();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }

  @Test(enabled = false)
  public void testHbConnectionGroups() {

    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List result = session.createQuery("from GroupData").list();
    ((List<GroupData>) result).forEach(System.out::println);
    session.getTransaction().commit();
    session.close();

  }

  @Test
  public void testHbConnectionContacts() {

    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> resultContacts = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();

//    ((List<ContactData>) result).forEach(System.out::println);

    session.getTransaction().commit();
    session.close();

    for (ContactData contact : resultContacts) {
      System.out.println(contact);
      System.out.println(contact.getGroups());

//      for (Iterator<GroupData> iterator = contact.getGroups().iterator(); iterator.hasNext();){
//        System.out.print("Group Id " + iterator.next().getId());
//      }
    }
  }

  @Test
  public void testHbConnectionContact() {

    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ContactData resultContact = (ContactData) session.createQuery("from ContactData where id =" + 171).uniqueResult();
    session.getTransaction().commit();
    session.close();

    System.out.println(resultContact.getGroups());
    }
}
