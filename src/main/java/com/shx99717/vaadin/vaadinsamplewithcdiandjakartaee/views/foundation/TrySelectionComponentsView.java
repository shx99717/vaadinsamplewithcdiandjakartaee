package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.AppMainEntry;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.Address;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.Student;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.StudentType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Try selection components")
@Route(value = "tryselectioncomponents", layout = AppMainEntry.class)
public class TrySelectionComponentsView extends VerticalLayout {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrySelectionComponentsView.class);


    public TrySelectionComponentsView() {
        addComboBox1();

        addComboBox2();

        addRadioButtonGroup1();
    }



    private void addComboBox1() {
        H1 h1 = new H1("Combox box - use toString as the item caption by default");
        ComboBox<Student> comboBox = new ComboBox<>("Students");
        comboBox.setItems(getDummyStudents(30));

        add(h1, comboBox);
    }

    private void addComboBox2() {
        H1 h1 = new H1("Combox box - use ItemLabelGenerator for caption");
        ComboBox<Student> comboBox = new ComboBox<>("Students");
        comboBox.setItems(getDummyStudents(30));
        comboBox.setItemLabelGenerator(student -> student.getEmail());

        add(h1, comboBox);
    }

    private void addRadioButtonGroup1() {
        H1 h1 = new H1("Radio button group");
        RadioButtonGroup<String> options = new RadioButtonGroup<>();
        options.setItems("One", "Two", "Three");

        add(h1, options);

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
