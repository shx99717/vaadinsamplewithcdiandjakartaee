package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo;

import java.time.LocalDate;

public class Student {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private Address address;
    private LocalDate dob;
    private StudentType type;

    public Student() {}

    public Student(String firstName, String lastName, int age, String email, Address address, LocalDate dob, StudentType type) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public StudentType getType() {
        return type;
    }

    public void setType(StudentType type) {
        this.type = type;
    }

    
    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Student [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", email=" + email + ", address=" + address + ", dob=" + dob
                + ", type=" + type + "]";
    }

}
