package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.AppMainEntry;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.converter.LowerUpperCaseConverter;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.Student;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.validator.IntegerRangeValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Try form conversion")
@Route(value = "tryformconversion", layout = AppMainEntry.class)
public class TryFormConversionView extends VerticalLayout {
    private static final Logger LOGGER = LoggerFactory.getLogger(TryFormConversionView.class);


    public TryFormConversionView() {
        addStudentForm1();


    }

    private void addStudentForm1() {
        TextField ageString = new TextField("Age");
        TextField lowercaseFirstnameString = new TextField("Lowercase firstname");
        
        H1 h1 = new H1("Student Form conversion");
        FormLayout formLayout = new FormLayout();


        formLayout.add(ageString, lowercaseFirstnameString);

        add(h1, formLayout);


        Binder<Student> binder = new Binder<>();
        // field is String type, and the property age of Student is int type, we need a conversion inbetween
        // setup converter better calling bind
        binder.forField(ageString)
            .withConverter(new StringToIntegerConverter("The input must be an integer"))
            .withValidator(new IntegerRangeValidator("Please check your age input", 0, 120)).asRequired("Need input here")
            .bind(Student::getAge, Student::setAge);
        
        binder.forField(lowercaseFirstnameString)
            .withConverter(new LowerUpperCaseConverter())
            .bind(Student::getFirstName, Student::setFirstName);
        
        Student dummyStudent = new Student(); 
        binder.setBean(dummyStudent);
        
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



}
