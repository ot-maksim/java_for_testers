package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

@XStreamAlias("contact")
public class ContactData {
  @XStreamOmitField
  private int id = 0;
  @Expose
  private String firstName;
  @Expose
  private String lastName;
  @Expose
  private String firstAddress;
  @Expose
  private String homePhone;
  @Expose
  private String mobilePhone;
  @Expose
  private String workPhone;
  private String allPhones;
  @Expose
  private String firstEmail;
  @Expose
  private String secondEmail;
  @Expose
  private String thirdEmail;
  private String allEmails;
  private String group;
  private File photo;

  public File getPhoto() {
    return photo;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
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

  public String getGroup() {
    return group;
  }

  public ContactData withGroup(String group) {
    this.group = group;
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
  public String toString() {
    return "ContactData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", id=" + id +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + id;
    return result;
  }
}
