package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StudentWithBeanValidation {
    // The message is defined in ValidationMessages.properties
    @NotBlank(message = "{student.firstname.required}")
    @Size(min = 5, max = 50, message = "{student.firstname.size}")
    private String firstName;
    
    @NotNull(message = "Lastname cannot be null")
    private String lastName;
    
    @Min(value = 0, message = "Minimum age is 0")
    @Max(value = 120, message = "Maximum age is 120")
    private int age;
    
    @Email(message = "Email should be valid")
    private String email;
    private Address address;
    private StudentType type;

    public StudentWithBeanValidation() {}

    public StudentWithBeanValidation(String firstName, String lastName, int age, String email, Address address, StudentType type) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.address = address;
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

    @Override
    public String toString() {
        return "Student [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", email=" + email + ", address=" + address + ", type=" + type
                + "]";
    }



}
