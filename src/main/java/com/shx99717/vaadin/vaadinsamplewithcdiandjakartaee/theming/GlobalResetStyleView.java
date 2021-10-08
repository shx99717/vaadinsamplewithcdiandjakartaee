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
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
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
 * Visit directly by http://localhost:8080/theme_reset
 */
@Route("theme_reset")
@CssImport("styles/reset.css") // Global way for non shadow DOM and general etc
@CssImport(value = "styles/module/module-reset.css", themeFor = "vaadin-*") // Local way for shadow DOM
public class GlobalResetStyleView extends VerticalLayout {
    
    @Inject
    private GreetService greetService;

    @PostConstruct
    public void init() {
    	// We will reset all styles under class 'my-theme-reset'
    	addClassName("my-theme-reset");
    	
        addSomeBasic();
        
        addStudentForm1();
        
        addGrid1();
    }
    
    private void addSomeBasic() {
        /**
         * Static Resources:
         * - for project with war packaging, the resources should be located under the src/main/webapp directory
         *   e.g. src/main/img/vaadin-logo.png
         * - for jar packaging(Sprint project, Addon project), the resources should be located under
         *   src/main/resources/META-INF/resources directory
         *   e.g. src/main/resources/META-INF/resources/img/logo.jpg
         * 
         */
        Image logo = new Image("img/vaadin-logo.png", "The Vaadin Logo");
        logo.setWidth("300px");
        add(logo);
        
        
        Div divWithLogoAsBackground = new Div();
        divWithLogoAsBackground.setText("the vaadin logo here");
        divWithLogoAsBackground.addClassName("vaadin-logo");
        add(divWithLogoAsBackground);
        
        
        TextField textField = new TextField("Your name");

        Button button = new Button("Say hello",
                e -> Notification.show(greetService.greet(textField.getValue())));
        
        add(textField, button);

        H1 customComponent1H1 = new H1("DateTimePicker made of DatePicker and TimePicker");
        DateTimePicker dateTimePicker = new DateTimePicker();

        add(customComponent1H1, dateTimePicker);


        H1 webComponent1H1 = new H1("simple integration of @polymer/paper-toggle-button ");
        ToggleButton toggleButton = new ToggleButton();
        add(webComponent1H1, toggleButton);
        
        
        TextField textField2 = new TextField("Dummy field 1");
        TextField textField3 = new TextField("Dummy field 2 - with class .special-blue on shadow DOM root");
        textField3.addClassName("special-blue");
        TextField textField4 = new TextField("Dummy field 3");
        TextField textField5 = new TextField("Dummy field 4 - with class .special-blue on shadow DOM root");
        textField5.addClassName("special-blue");
        add(textField2, textField3, textField4, textField5);
    }

    
    private void addStudentForm1() {
        TextField firstName = new TextField("First Name");
        TextField secondName = new TextField("Last Name");
        IntegerField age = new IntegerField("Age");
        EmailField email = new EmailField("Email");
        Select<StudentType> studentType = new Select<>();
        TextField country = new TextField("Address -> Country");
        TextField street = new TextField("Address -> Street");
        TextField postcode = new TextField("Address -> Postcode");
        
        
        H1 h1 = new H1("Student Form");
        /*
         * Here is the place to add the class and the class is in shared-style.css under frontend/styles folder
         */
        h1.addClassName("student-form-header");
        FormLayout formLayout = new FormLayout();
        
        studentType.setLabel("Student Type");
        studentType.setItems(Arrays.asList(StudentType.values()));
        
        
        formLayout.add(firstName, secondName, age, email, studentType);
        formLayout.add(country, street, postcode);
        
        add(h1, formLayout);
        
    }
    
    
    private void addGrid1() {
        H1 h1 = new H1("Grid");
        Grid<Student> grid = new Grid<>(Student.class);
        
        // The property names are set as the column keys, so you can use them for further configuring the columns.
        grid.getColumnByKey("age").setHeader("A | G | E").setFrozen(true);

        add(h1, grid);
        
        // accept collection
        // grid.setItems(getDummyStudents(20));
        // accept stream
        grid.setItems(getDummyStudents(20).stream().filter(s -> s.getFirstName().contains("1")));
        
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
