package com.userManagementsystem.model;

public class User {
private int id;
private String name;
private String surname;
private String country;
private String gmail;
public User() {
	}
public User(int id, String name, String surname, String country, String gmail) {
	this.id = id;
	this.name = name;
	this.surname = surname;
	this.country = country;
	this.gmail = gmail;
}

public User(String name, String surname, String country, String gmail) {
	this.name = name;
	this.surname = surname;
	this.country = country;
	this.gmail = gmail;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSurname() {
	return surname;
}
public void setSurname(String surname) {
	this.surname = surname;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getGmail() {
	return gmail;
}
public void setGmail(String gmail) {
	this.gmail = gmail;
}
@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", country=" + country + ", gmail=" + gmail
			+ "]";
}
}
