package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String address;
  private final String homePhoneNumber;
  private final String email;

  public ContactData(String firstName, String lastName, String address, String homePhoneNumber, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.homePhoneNumber = homePhoneNumber;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhoneNumber() {
    return homePhoneNumber;
  }

  public String getEmail() {
    return email;
  }
}
