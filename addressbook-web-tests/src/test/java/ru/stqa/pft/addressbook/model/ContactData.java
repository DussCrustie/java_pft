package ru.stqa.pft.addressbook.model;
import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name="addressbook")

  public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name="firstname")
  private  String firstname;
  @Transient
  @Column(name="lastname")
  private  String lastname;
  @Transient
  private  String address;
  @Transient
  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private  String home;
  @Transient
  private  String work;
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private  String mobile;
  @Transient
  private  String group;
  @Transient
  private  String email1;
  @Transient
  private  String email2;
  @Transient
  private  String email3;
  @Transient
  private  String allPhones;
  @Transient
  private  String allEmails;
  @Transient
  private  String allDetails;

  @Override
  public String toString() {
    return "ContactData{" + "id=" + id + ", firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + '}';
  }

 /*@Column(name="photo")
  @Type(type = "text")
  private String photo;

  public File getPhoto() {
    return new File (photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }*/

  public String getAllDetails() {
    return allDetails;
  }

  public ContactData withAllDetails(String allDetails) {
    this.allDetails = allDetails;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getEmail1() {
    return email1;
  }
  public String getAddress() {
    return address;
  }

  public ContactData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }
  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHome(String home) {
    this.home = home;
    return this;
  }

  public ContactData withWork(String work) {
    this.work = work;
    return this;
  }

  public ContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
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

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getHome() {
    return home;
  }

  public String getWork() {
    return work;
  }

  public String getMobile() {
    return mobile;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}