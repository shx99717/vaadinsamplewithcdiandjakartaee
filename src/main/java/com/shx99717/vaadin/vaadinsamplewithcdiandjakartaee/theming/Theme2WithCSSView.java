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
 * Visit directly by http://localhost:8080/theme2
 * 
 * There are two styles:
 *   - Global styles, traditional CSS styles, usually for styling the application 
 *     | global style won't affect the shadow dom
 *     | should be located under project's frontend folder e.g. frontend/styles/shared-styles.css
 *     | use annotation @CssImport, put on the top most parent layout
 *     | use annotation @StyleSheet
 *     | Most of @CssImport and @StyleSheet are the same, but the main differences are:
 *       & CSS file location
 *         % @CssImport - loads a css file from frontend folder
 *         % @StyleSheet - loads by default src/main/webapp/frontend folder
 *                         it is good to use context:// prefix to load CSS file relative to the context root, it will adapt 
 *                         itself when deployed as a war or a jar
 *                         e.g. @StyleSheet("context://styles/global.css")
 *                         > war packaging -> /src/main/webapp/styles/global.css
 *                         > jar packaging -> /src/main/resources/META-INF/resources/styles/global.css
 *       & Webpack Bundle
 *         % @CssImport - processed by webpack and inlined into the frontend javascipt bundle, due to the inling, no separate HTTP request to the CSS file
 *         % @StyleSheet - loaded as is by the browser, extra http request needed
 *       & External CSS files
 *         % @CssImport - only intended for local files since the contents are inlined during the build
 *         % @StyleSheet - can be used for importing css files from external URL
 *       & Polyfill
 *         % @CssImport - adds polyfill to prevent style leaking into shadow DOM in the browsers without native support, e.g. IE 11 
 *         % @StyleSheet - No polyfill, loaded as-is by the browser
 *       & Relative path in the url()
 *         % @CssImport - relative to the context root, given the resource at src/main/webapp/img/login-bg.jpg
 *                        .login-screen {background-image: url(img/login-bg.jpg)}
 *         % @StyleSheet - relative to the CSS file, given the resource at src/main/webapp/img/login-bg.jpg
 *                                                             css at      src/main/webapp/styles/dummy.css
 *                        in the dummy.css
 *                        .login-screen {background-image: url(../img/login-bg.jpg)}
 *     | Therefore the in most case we will use @CssImport, but the usecases of @StyleSheet are:
 *       & Need separate caching for CSS files that change very rarely
 *       & Need to load styles form an external URL, e.g. CDN serving the web fonts
 *       & Don't need to support IE 11
 *   - Local styles, CSS styles for shadow DOM, usually for styling components
 *     | Create style module for this purpose
 *     | Rules are:
 *         & Each style module should be in a separte CSS file, shared-style.css is usually for global styles, if
 *           we want to change all vaadin-text-field's background to yellow(in the shadow DOM), then
 *           yellow-bg-text-field.css would be a better name
 *         & use part attribute selector in shadow DOM rather than id/css/element where these are considered implementation details
 *           and they can change, on the other hand, the part selector is considered the ONLY public styling API.
 *           <div part="input-field" id="vaadin-text-filed-input-0">..</div> 
 *         
 * 
 */
@Route("theme2")
@CssImport("styles/shared-styles.css") // Global way for non shadow DOM and general etc
@CssImport(value = "styles/module/yellow-bg-text-field.css", themeFor = "vaadin-text-field") // Local way for shadow DOM
public class Theme2WithCSSView extends VerticalLayout {
    
    @Inject
    private GreetService greetService;

    @PostConstruct
    public void init() {
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
