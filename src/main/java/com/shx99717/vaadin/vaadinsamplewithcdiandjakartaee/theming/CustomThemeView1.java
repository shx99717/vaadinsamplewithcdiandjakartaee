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
import com.vaadin.flow.theme.NoTheme;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;

/**
 * Visit directly by http://localhost:8080/theme4
 * 
 * 
 * frontend
 * └── themes            (1)
 *   └── my-theme        (2)
 *       ├── components/ (3)
 *       └── styles.css  (4)
 * 
 * 1. The themes folder can contain multiple custom themes (but only one can be applied to the application at a time).
 * 2. Each theme is in its own sub-folder. The name of this folder is provided as a parameter to the @Theme annotation to apply the theme to the application.
 * 3. The components sub-folder is for component style sheets that target the (local CSS) internals of Vaadin components.
 * 4. styles.css is the theme’s master style sheet that is automatically loaded when the theme is applied.
 * 
 * read more on frontend/themes/my-dummy-theme-a/styles.css and components_styling_explained.txt
 */
@Route("theme4")
// if the @Theme here is enabled, please disable all other @Theme annotation, otherwise during the bootup
// Vaadin will scan the classes and find multiple definition of used theme
// also comment out for @CssImport on elsewhere, otherwise it will overwrite the component styling under themes/<theme-name>/components/
//@Theme(themeFolder = "my-dummy-theme-a")
public class CustomThemeView1 extends VerticalLayout {
    
    @Inject
    private GreetService greetService;

    @PostConstruct
    public void init() {
        addSomeBasic();
        
        addStudentForm1();
        
        addGrid1();
    }
    
    private void addSomeBasic() {
        TextField textField = new TextField("Your name");

        /**
         * Theme variants
         * Some components have predefined variants, which allows to change a component's look and
         * feel quickly
         */
        Button button = new Button("Say hello",
                e -> Notification.show(greetService.greet(textField.getValue())));
        button.addThemeVariants(ButtonVariant.LUMO_ERROR);
        
        add(textField, button);

        /**
         * Most components implement the HasStyle interface, which allows 
         * 1. Inline styling with getStyle.set()
         * 2. Add CSS class names with addClassName(s)()
         * 3. Remove CSS class name(s) with removeClassName(s)()
         */
        H1 customComponent1H1 = new H1("DateTimePicker made of DatePicker and TimePicker");
        customComponent1H1.getStyle().set("background-color", "green");
        customComponent1H1.addClassNames("dummy-class-1", "dummy-class-2");
        DateTimePicker dateTimePicker = new DateTimePicker();

        add(customComponent1H1, dateTimePicker);


        /**
         * For components that are NOT implementing the HasStyle interface, we can use Element API
         * 1. Inline styling with component.getElement().getStyle().set()
         * 2. Add CSS class names with component.getElement().getClassList().add()
         * 3. Remove CSS class name(s) with component.getElement().getClassList().remove()
         */
        H1 webComponent1H1 = new H1("simple integration of @polymer/paper-toggle-button ");
        ToggleButton toggleButton = new ToggleButton();
        toggleButton.getElement().getStyle().set("background-color", "green");
        toggleButton.getElement().getClassList().add("dummy-class-3");
        add(webComponent1H1, toggleButton);
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
