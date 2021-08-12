package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.filter;

public class StudentSearchFilter {

    private String firstNameSearchTerm;
    private String lastNameSearchTerm;


    public StudentSearchFilter(String firstNameSearchTerm, String lastNameSearchTerm) {
        this.firstNameSearchTerm = firstNameSearchTerm;
        this.lastNameSearchTerm = lastNameSearchTerm;
    }

    public String getFirstNameSearchTerm() {
        return firstNameSearchTerm;
    }

    public void setFirstNameSearchTerm(String firstNameSearchTerm) {
        this.firstNameSearchTerm = firstNameSearchTerm;
    }

    public String getLastNameSearchTerm() {
        return lastNameSearchTerm;
    }

    public void setLastNameSearchTerm(String lastNameSearchTerm) {
        this.lastNameSearchTerm = lastNameSearchTerm;
    }

    @Override
    public String toString() {
        return "StudentSearchFilter [firstNameSearchTerm=" + firstNameSearchTerm + ", lastNameSearchTerm=" + lastNameSearchTerm + "]";
    }



}
