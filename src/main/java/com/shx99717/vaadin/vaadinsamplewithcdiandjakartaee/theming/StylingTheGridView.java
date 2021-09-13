package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.theming;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.service.GreetService;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.component.DateTimePicker;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.component.ToggleButton;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.Address;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.Student;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.StudentType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * Visit directly by http://localhost:8080/theme3
 */
@Route("theme3")
//if the @Theme here is enabled, please disable all other @Theme annotation, otherwise during the bootup
//Vaadin will scan the classes and find multiple definition of used theme
//also comment out for @CssImport on elsewhere, otherwise it will overwrite the component styling under themes/<theme-name>/components/
//@CssImport(value = "styles/module/highlighted-grid.css", themeFor = "vaadin-grid")
public class StylingTheGridView extends VerticalLayout {
    
    @Inject
    private GreetService greetService;

    @PostConstruct
    public void init() {
        addGrid1();
    }
    
    
    private void addGrid1() {
        H1 h1 = new H1("Grid");
        Grid<Student> grid = new Grid<>(Student.class);
        
        // The property names are set as the column keys, so you can use them for further configuring the columns.
        grid.getColumnByKey("age").setHeader("A | G | E").setFrozen(true);

        add(h1, grid);
        
        grid.setItems(getDummyStudents(20));
        
        
        // This is for a row
        grid.setClassNameGenerator(student -> {
            if(student.getFirstName().contains("1")) {
                // This will add to each cell of the grid a class where the row of student rendering with the first name contains 1
                return "firstname-with-1";
            } else {
                return "firstname-without-1";
            }
        });
        
        grid.getColumnByKey("age").setClassNameGenerator(student -> {
            // This will mark with the css 'lastname-with-2' to the cell at column of age where the backend instance(student) for 
            // this row has lastname contains 2
            if(student.getLastName().contains("2")) {
                return "lastname-with-2";
            } else {
                return null;
            }
        });
        
    }
    
    private List<Student> getDummyStudents(int amount) {
        List<Student> students = new ArrayList<>();
        for(int i = 0; i < amount; i++) {
            students.add(getDummyStudent(i));
        }
        return students;
    }
     
    
    private Student getDummyStudent(int index) {
        Student student = new Student("Mike" + index, "Moose" + index, 31, "mike.moose@dummy.com", getDummyAddress(index), LocalDate.now(), StudentType.INTERNATIONAL);
        return student;
    }
    
    private Address getDummyAddress(int index) {
        Address address = new Address("China" + index, "Lane 1002, Room 801, Shanghai" + index, "200201");
        return address;
    }
}
