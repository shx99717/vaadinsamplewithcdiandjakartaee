package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.filter.StudentSearchFilter;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.Address;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.Student;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.StudentType;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;

public class StudentBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentBean.class);
    private List<Student> students;

    public StudentBean() {
        students = getDummyStudents(1000);
    }

    public Stream<Student> getStudents(Query<Student, Void> query) {
        LOGGER.info("getStudents() called offset = " + query.getOffset() + ", limit = " + query.getLimit());

        List<QuerySortOrder> sorting = query.getSortOrders();
        // Add logic to sort from data fetch here
        // ..
        // ..
        // QuerySortOrder order0 = sorting.get(0);
        // String propertyName = order0.getSorted(); // could be set via grid.addColumn().setSortProperty();
        // SortDirection direction = order0.getDirection();


        return students.subList(query.getOffset(), query.getOffset() + query.getLimit()).stream();
    }

    public int countStudents() {
        LOGGER.info("countStudents() called total = " + students.size());
        return students.size();
    }


    public Stream<Student> getStudentsWithFilter(Query<Student, StudentSearchFilter> query) {
        LOGGER.info("getStudentsWithFilter() called offset = " + query.getOffset() + ", limit = " + query.getLimit());

        List<QuerySortOrder> sorting = query.getSortOrders();
        // Add logic to sort from data fetch here
        // ..
        // ..
        // QuerySortOrder order0 = sorting.get(0);
        // String propertyName = order0.getSorted(); // could be set via grid.addColumn().setSortProperty();
        // SortDirection direction = order0.getDirection();
        
        
        Optional<StudentSearchFilter> filter = query.getFilter();
        // Add logic to filter the data at backend
        if(filter.isPresent()) {
            LOGGER.info("first name search term = " + filter.get().getFirstNameSearchTerm());
            LOGGER.info("last name search term = " + filter.get().getLastNameSearchTerm());
        }
        

        return students.subList(query.getOffset(), query.getOffset() + query.getLimit()).stream();
    }

    public int countStudentsWithFilter() {
        LOGGER.info("countStudentsWithFilter() called total = " + students.size());
        return students.size();
    }

    private List<Student> getDummyStudents(int amount) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            students.add(getDummyStudent(i));
        }
        return students;
    }


    private Student getDummyStudent(int index) {
        Student student =
                new Student("Mike" + index, "Moose" + index, 31, "mike.moose@dummy.com", getDummyAddress(index), LocalDate.now(), StudentType.INTERNATIONAL);
        return student;
    }

    private Address getDummyAddress(int index) {
        Address address = new Address("China" + index, "Lane 1002, Room 801, Shanghai" + index, "200201");
        return address;
    }
}
