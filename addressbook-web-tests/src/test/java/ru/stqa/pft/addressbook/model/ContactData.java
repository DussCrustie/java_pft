package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String company;
  private final String address;
  private final String telephoneMobile;
  private final String telephoneWork;
  private final String email;
  private final String homepage;

  public ContactData(String firstname, String middlename, String lastname, String company, String address, String telephoneMobile, String telephoneWork, String email, String homepage) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.company = company;
    this.address = address;
    this.telephoneMobile = telephoneMobile;
    this.telephoneWork = telephoneWork;
    this.email = email;
    this.homepage = homepage;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getTelephoneMobile() {
    return telephoneMobile;
  }

  public String getTelephoneWork() {
    return telephoneWork;
  }

  public String getEmail() {
    return email;
  }

  public String getHomepage() {
    return homepage;
  }
}