package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.AppMainEntry;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.validator.NameContainsBadWordsValidator;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.Student;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.StudentType;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.StudentWithBeanValidation;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.IntegerRangeValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Try form validation")
@Route(value = "tryformvalidation", layout = AppMainEntry.class)
public class TryFormValidationView extends VerticalLayout {
    private static final Logger LOGGER = LoggerFactory.getLogger(TryFormValidationView.class);
    
    
    public TryFormValidationView() {
        addStudentForm1();
        
        addStudentForm2();
        
        addStudentForm3();
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
        
        
        H1 h1 = new H1("Student Form Validation - Blur will trigger validation - Field level");
        FormLayout formLayout = new FormLayout();
        
        studentType.setLabel("Student Type");
        studentType.setItems(Arrays.asList(StudentType.values()));
        
        
        formLayout.add(firstName, secondName, age, email, studentType);
        formLayout.add(country, street, postcode);
        
        add(h1, formLayout);
        
        H3 errorTitle = new H3("Error will be printed here - field level");
        Paragraph errorContainer = new Paragraph();
        add(errorTitle, errorContainer);
        
        // Option 1: Does not support binding with property names
        // Pros: use of method reference --> type safe, allow use IDE refactoring tools
        // Cons: can not use bean validation
         Binder<Student> binder = new Binder<>();
         // Use customized validator(field level validation) + display below
         binder.forField(firstName)
             .withValidator(new NameContainsBadWordsValidator("Name contains bad word"))
             .bind(Student::getFirstName, Student::setFirstName);
         
         // Use customized validator(field level validation) + display in a specified component
         binder.forField(secondName)
             .withValidator(new NameContainsBadWordsValidator("Name contains bad word"))
             .withStatusLabel(errorContainer) // Display error message in a specified component
             .bind(Student::getLastName, Student::setLastName);
         
         // Use customized validator(field level validation) + required + display in a specified component with logic
         binder.forField(age)
             .withValidator(new IntegerRangeValidator("Please check your age input", 0, 120))
             .asRequired("Need input here")
             .withValidationStatusHandler(status -> { // Display error message with some logic
                 errorContainer.setText("See the message here | " + status.getMessage().get());
             })
             .bind(Student::getAge, Student::setAge);
         binder.forField(email)
             .withValidator(new EmailValidator("This doesn't look like a valid email address"))
             .bind(Student::getEmail, Student::setEmail);
         binder.forField(studentType).bind(Student::getType, Student::setType);
         
         
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
        
         
    }
    
    
    private void addStudentForm2() {
        TextField firstName = new TextField("First Name");
        TextField secondName = new TextField("Last Name");
        IntegerField age = new IntegerField("Age");
        EmailField email = new EmailField("Email");
        Select<StudentType> studentType = new Select<>();
        TextField country = new TextField("Address -> Country");
        TextField street = new TextField("Address -> Street");
        TextField postcode = new TextField("Address -> Postcode");
        
        
        H1 h1 = new H1("Student Form Validation - Blur will trigger validation - Binder level");
        FormLayout formLayout = new FormLayout();
        
        studentType.setLabel("Student Type");
        studentType.setItems(Arrays.asList(StudentType.values()));
        
        
        formLayout.add(firstName, secondName, age, email, studentType);
        formLayout.add(country, street, postcode);
        
        add(h1, formLayout);
        
        H3 errorTitle = new H3("Error will be printed here - binder level");
        Paragraph errorContainer = new Paragraph();
        add(errorTitle, errorContainer);
        
        // Option 1: Does not support binding with property names
        // Pros: use of method reference --> type safe, allow use IDE refactoring tools
        // Cons: can not use bean validation
         Binder<Student> binder = new Binder<>();
         binder.forField(firstName)
             .withValidator(new NameContainsBadWordsValidator("Name contains bad word"))
             .bind(Student::getFirstName, Student::setFirstName);
         
         binder.forField(secondName)
             .bind(Student::getLastName, Student::setLastName);
         
         binder.forField(age)
             .bind(Student::getAge, Student::setAge);
         binder.forField(email)
             .bind(Student::getEmail, Student::setEmail);
         binder.forField(studentType).bind(Student::getType, Student::setType);
         
         
         // Cross field validation at binder level
         // It will trigger field level validation first, if passes then trigger binder level validation
         binder.withValidator(student -> {
             LOGGER.info("Trigger binder level validation");
             return student.getAge() >= 18 && (student.getEmail() != null && student.getEmail().contains("@student.com"));
         }, "Student level cross field validation failed, student must have age bigger than 18 and from `student.com`");
         binder.setStatusLabel(errorContainer);
         // or use some logic
//         binder.setValidationStatusHandler(status -> {
//             // do something
//         });

         // In order to make the binder level validation work, a bean must bean binded
         binder.setBean(new Student());
         
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
        
         
    }
    
    
    
    private void addStudentForm3() {
        TextField firstName = new TextField("First Name");
        TextField secondName = new TextField("Last Name");
        IntegerField age = new IntegerField("Age");
        EmailField email = new EmailField("Email");
        Select<StudentType> studentType = new Select<>();
        
        
        H1 h1 = new H1("Student Form Validation - Bean validation");
        FormLayout formLayout = new FormLayout();
        
        studentType.setLabel("Student Type");
        studentType.setItems(Arrays.asList(StudentType.values()));
        
        
        formLayout.add(firstName, secondName, age, email, studentType);
        
        add(h1, formLayout);
        
        
        // Option 2: Use reflection to scan class for properties so you can bind by property name
        // Pros: able to use bean validation
        // Cons: no type safe, the code will be broken if the property name changes
         BeanValidationBinder<StudentWithBeanValidation> binder = new BeanValidationBinder<>(StudentWithBeanValidation.class);
         binder.forField(firstName).bind("firstName"); 
         binder.forField(secondName).bind("lastName");
         binder.forField(age).bind("age");
         binder.forField(email).bind("email");
         binder.forField(studentType).bind("type");
         
    }

}
