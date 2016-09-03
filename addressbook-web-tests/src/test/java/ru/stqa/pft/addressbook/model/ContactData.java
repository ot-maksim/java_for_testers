package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = 0;

  @Expose
  @Column(name = "firstname")
  private String firstName = "";

  @Expose
  @Column(name = "lastname")
  private String lastName = "";

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String firstAddress = "";

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone = "";

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone = "";

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone = "";

  @Transient
  private String allPhones;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String firstEmail = "";

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String secondEmail = "";

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String thirdEmail = "";

  @Transient
  private String allEmails;

//  @Transient
//  private String group;

//  public String getGroup() {
//    return group;
//  }
//
//  public ContactData withGroup(String group) {
//    this.group = group;
//    return this;
//  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"),
          inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<>();

  @Expose
  @Column(name = "photo")
  @Type(type = "text")
  private String path = "";

  public Groups getGroups() {
    return new Groups(groups);
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }

  public String getPath() {
    return path;
  }

  public ContactData withPath(String path) {
    this.path = path;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;

  }

  public String getLastName() {
    return lastName;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getFirstAddress() {
    return firstAddress;
  }

  public ContactData withFirstAddress(String address) {
    this.firstAddress = address;
    return this;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getFirstEmail() {
    return firstEmail;
  }

  public ContactData withFirstEmail(String firstEmail) {
    this.firstEmail = firstEmail;
    return this;
  }

  public String getSecondEmail() {
    return secondEmail;
  }

  public ContactData withSecondEmail(String secondEmail) {
    this.secondEmail = secondEmail;
    return this;
  }

  public String getThirdEmail() {
    return thirdEmail;
  }

  public ContactData withThirdEmail(String thirdEmail) {
    this.thirdEmail = thirdEmail;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
    if (firstAddress != null ? !firstAddress.equals(that.firstAddress) : that.firstAddress != null) return false;
    if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
    if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
    if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) return false;
    if (firstEmail != null ? !firstEmail.equals(that.firstEmail) : that.firstEmail != null) return false;
    if (secondEmail != null ? !secondEmail.equals(that.secondEmail) : that.secondEmail != null) return false;
    return thirdEmail != null ? thirdEmail.equals(that.thirdEmail) : that.thirdEmail == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (firstAddress != null ? firstAddress.hashCode() : 0);
    result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
    result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
    result = 31 * result + (firstEmail != null ? firstEmail.hashCode() : 0);
    result = 31 * result + (secondEmail != null ? secondEmail.hashCode() : 0);
    result = 31 * result + (thirdEmail != null ? thirdEmail.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", firstAddress='" + firstAddress + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", firstEmail='" + firstEmail + '\'' +
            ", secondEmail='" + secondEmail + '\'' +
            ", thirdEmail='" + thirdEmail + '\'' +
            '}';
  }
}
