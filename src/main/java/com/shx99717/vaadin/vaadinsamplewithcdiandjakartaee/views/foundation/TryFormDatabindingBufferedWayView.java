package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.AppMainEntry;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.Address;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.Student;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.StudentType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Try form databinding buffered")
@Route(value = "tryformdatabindingbuffered", layout = AppMainEntry.class)
public class TryFormDatabindingBufferedWayView extends VerticalLayout {
    private static final Logger LOGGER = LoggerFactory.getLogger(TryFormDatabindingBufferedWayView.class);
    
    @PropertyId("firstName") // @PropertyId annotation is used for automatic binding if the name of the field definition is different from the name of the property of the bean, it is not needed for option 1 and option 2
                             // we can omit the @PerpertyId annotation if the field name `firstName` is the same as the property name `firstName` in the Student class
    private TextField firstName = new TextField("First Name");
    @PropertyId("lastName") // We need this, as `lastName` of Bean is different from the `secondName` of the field here
    private TextField secondName = new TextField("Last Name");
    private IntegerField age = new IntegerField("Age");
    private EmailField email = new EmailField("Email");
    @PropertyId("type")
    private Select<StudentType> studentType = new Select<>();
    
    // Nested fields binding only works when we set the second parameter of Binder to true
    // e.g. Binder<Student> binder = new Binder<>(Student.class, true);
    // It will instruct the binder to scan the nested properties
    @PropertyId("address.country")
    private TextField country = new TextField("Address -> Country");
    @PropertyId("address.street")
    private TextField street = new TextField("Address -> Street");
    @PropertyId("address.postcode")
    private TextField postcode = new TextField("Address -> Postcode");
    
    public TryFormDatabindingBufferedWayView() {
        addStudentForm();
    }

    private void addStudentForm() {
        H1 h1 = new H1("Student Form - Buffered way");
        H3 h3 = new H3("Load Bean in buffered way, this approach more suits for a form with Save|Cancel button, if want to update the fields by bean's value, need to call readBean, and when want to update the Bean by fields value, need to call writeBean");
        FormLayout formLayout = new FormLayout();
        
        studentType.setLabel("Student Type");
        studentType.setItems(Arrays.asList(StudentType.values()));
        
        
        formLayout.add(firstName, secondName, age, email, studentType);
        formLayout.add(country, street, postcode);
        
        add(h1, h3, formLayout);
        
        
        // Option 1: Does not support binding with property names
        // Pros: use of method reference --> type safe, allow use IDE refactoring tools
        // Cons: can not use bean validation
        // Binder<Student> binder = new Binder<>();
        // binder.forField(firstName).bind(Student::getFirstName, Student::setFirstName); // or use shorthand binder.bind(firstName, Student::getFirstName, Student::setFirstName);
        // binder.forField(lastName).bind(Student::getLastName, Student::setLastName);
        // binder.forField(age).bind(Student::getAge, Student::setAge);
        // binder.forField(email).bind(Student::getEmail, Student::setEmail);
        // binder.forField(studentType).bind(Student::getType, Student::setType);
        
        // Option 2: Use reflection to scan class for properties so you can bind by property name
        // Pros: able to use bean validation
        // Cons: no type safe, the code will be broken if the property name changes
        // Binder<Student> binder = new Binder<>(Student.class);
        // binder.forField(firstName).bind("firstName"); // or use shorthand binder.bind(firstName, "firstName");
        // binder.forField(secondName).bind("lastName");
        // binder.forField(age).bind("age");
        // binder.forField(email).bind("email");
        // binder.forField(studentType).bind("type");
        // binder.forField(country).bind("address.country");
        // binder.forField(street).bind("address.street");
        // binder.forField(postcode).bind("address.postcode");
        
        // Option 3: Automatic bindings
        // set scan nested fields to false, we want to handle the nested fields by oursevles
        Binder<Student> binder = new Binder<>(Student.class, false);
        // It will scan the class, and bind automatically, and process @PropertyId accordingly
        // firstName is now bound to "firstName" of bean underlying, therefore can be ignored
        // secondName is now bound to "lastName" of bean underlying
        // ..
        // ..
        // It is recommended to add @PropertyId the fields explicitly, as it is easier to read and understand it is using automatically binding
        // bind normal fields
        binder.bindInstanceFields(this);
        
        
        // The nested fields need to be processed explicitly because scan nested fields has been set to false at binder (@PropertyId will not be processed for nested field)
        // (method A) bind nested fields with name
        // but if the address property of student is null, NPE will be thrown
        // binder.forField(country).bind("address.country");
        // binder.forField(street).bind("address.street");
        // binder.forField(postcode).bind("address.postcode");

        
        // (method B) bind nested fields with given lambda function taking care the null value of address property 
        // this works even the address of student instance is null
        binder.forField(country).bind(student -> {
            if(student.getAddress() != null) {
                return student.getAddress().getCountry();
            } else {
                return "";
            }
        }, (student, country) -> {
            if(student.getAddress() != null) {
                student.getAddress().setCountry(country);
            } else {
                Address address = new Address();
                address.setCountry(country);
                student.setAddress(address);
            }
        });
        
        binder.forField(street).bind(student -> {
            if(student.getAddress() != null) {
                return student.getAddress().getStreet();
            } else {
                return "";
            }
        }, (student, street) -> {
            if(student.getAddress() != null) {
                student.getAddress().setStreet(street);
            } else {
                Address address = new Address();
                address.setStreet(street);
                student.setAddress(address);
            }
        });
        
        binder.forField(postcode).bind(student -> {
            if(student.getAddress() != null) {
                return student.getAddress().getCountry();
            } else {
                return "";
            }
        }, (student, postcode) -> {
            if(student.getAddress() != null) {
                student.getAddress().setPostcode(postcode);
            } else {
                Address address = new Address();
                address.setPostcode(postcode);
                student.setAddress(address);
            }
        });
        
        
        //**********************************
        // Reading and writing values
        //**********************************
        // Buffered reading and writing
        Student dummyStudent = getDummyStudent();
        Button readBeanButton = new Button("Display Fields by Bean's value");
        readBeanButton.addClickListener(e -> {
            binder.readBean(dummyStudent);
        });
        
        
        Button writeBeanButton = new Button("Write Fields' value to Bean");
        writeBeanButton.addClickListener(e -> {
           try {
                binder.writeBean(dummyStudent);
            } catch (ValidationException e1) {
                LOGGER.error(e1.getMessage(), e1);
            } 
        });
        
        formLayout.add(readBeanButton, writeBeanButton);
        
        Paragraph studentDisplayParagraph = new Paragraph();
        ReadOnlyHasValue<String> studentDisplayReadOnlyField = new ReadOnlyHasValue<>(text -> studentDisplayParagraph.setText(text));
        binder.forField(studentDisplayReadOnlyField).bind(
                Student::toString /* getter, the whole student object will be printed */, 
                null /* no setter needed for read only */);
        
        
        add(new H3("Student object value printting here ..."));
        Button refreshOutputButton = new Button("Refresh output below");
        refreshOutputButton.addClickListener(e -> {
           binder.readBean(dummyStudent); 
        });
        add(refreshOutputButton);
        add(studentDisplayParagraph);
    }

    
    private Student getDummyStudent() {
        Student student = new Student("Mike", "Moose", 31, "mike.moose@dummy.com", getDummyAddress(), StudentType.INTERNATIONAL);
        return student;
    }
    
    private Address getDummyAddress() {
        Address address = new Address("China", "Lane 1002, Room 801, Shanghai", "200201");
        return address;
    }
}
