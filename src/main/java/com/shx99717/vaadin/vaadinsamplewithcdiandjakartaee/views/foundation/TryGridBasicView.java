package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.AppMainEntry;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.Address;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.Student;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.StudentType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Try grid basic")
@Route(value = "trygridbasic", layout = AppMainEntry.class)
public class TryGridBasicView extends VerticalLayout {
    private static final Logger LOGGER = LoggerFactory.getLogger(TryGridBasicView.class);


    public TryGridBasicView() {
        addGrid1();

        addGrid2();
        
        addGrid3();
        
        addGrid4();
    }


    private void addGrid1() {
        H1 h1 = new H1("(given Bean) Scan object and add column automatically");
        Grid<Student> grid = new Grid<>(Student.class);
        
        // The property names are set as the column keys, so you can use them for further configuring the columns.
        grid.getColumnByKey("age").setHeader("A | G | E").setFrozen(true);

        add(h1, grid);
        
        grid.setItems(getDummyStudents(20));
    }
    
    private void addGrid2() {
        H1 h1 = new H1("(given Bean) Turn off scan object, add cols by myself");
        Grid<Student> grid = new Grid<>(Student.class, false);
        
        add(h1, grid);
        
        // Enable drag and drop user reordering of columns
        grid.setColumnReorderingAllowed(true);
        
        
        // Add column way 1
        Column<Student> firstNameCol = grid
                .addColumn(Student::getFirstName)
                .setHeader("1st Name header")
                .setFooter("1st Name footer")
                .setWidth("100px")
                .setResizable(false);
        firstNameCol.setVisible(false);
        // You can set columns to be frozen with the setFrozen() method in Column, 
        // so that they are not scrolled off when scrolling horizontally. Additionally, 
        // user reordering of frozen columns is limited between other frozen columns.
        firstNameCol.setFrozen(true);
        // You can set identifier keys for your columns with the setKey() method. 
        // This allows retrieving the column from the grid at any time.
        // grid.getColumnByKey("firstNameKey") to retrieve the column
        firstNameCol.setKey("firstNameKey");
        
        
        // Add column way 2 - using property
        grid.addColumn("lastName");
        grid.addColumn("address.country");
        
        
        
        // Add column way 3 - using Renderer
        /*
           Columns can be configured to use Renderers to show the data in a more suitable way inside the cells. 
           Conceptually renderers are split into the three categories listed below.
             | Basic renderers - the renderers used to render basic values, such as dates and numbers
             | Template renderer - allows the developer to define cells with HTML markup and Polymer data binding syntax
             | Component renderer - allows the developer to use an arbitrary component inside the cells
         */

        // 3.1 Basic Renderers
        grid.addColumn(new LocalDateRenderer<>(Student::getDob,
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)))
                .setHeader("D|o|B");
              // or given a format
//        grid.addColumn(new LocalDateRenderer<>(Student::getDob, "dd/MM/yyyy"))
//                .setHeader("D|o|B");
        
        // An easy way to create a clickable button inside the grid cells. It creates a native <button> on the client side,
        // and the click and tap events (for touch devices) are treated on the server side.
        grid.addColumn(new NativeButtonRenderer<>("Say hello", clickedItem -> {
            Notification.show("hello world here");
        })).setHeader("Button in this column");
        
        
        
        // 3.2 Use template
        // You can define the contents of the grid cells with HTML markup and use Polymer notation for data binding and event handling. 
        // This is done by providing a TemplateRenderer for the appropriate Column.
        grid.addColumn(TemplateRenderer.<Student>of("=><b>[[item.template_variable_name_email]]</b><=")
                .withProperty("template_variable_name_email", Student::getEmail))
                .setHeader("Email");
        
        grid.addColumn(TemplateRenderer.<Student>of("[[item.template_variable_name_address.street]] | [[item.template_variable_name_address.country]] || [[item.template_variable_name_address.postcode]]")
                .withProperty("template_variable_name_address", Student::getAddress))
                .setHeader("All in one address");
        
        grid.addColumn(TemplateRenderer.<Student>of(
                "<button on-click='handleUpdate'>Update</button><button on-click='handleRemove'>Remove</button>")
                .withEventHandler("handleUpdate", student -> {
                    student.setLastName(student.getLastName() + " Updated");
                    grid.getDataProvider().refreshItem(student);
                    
                    Notification.show("Server side event received");
                }).withEventHandler("handleRemove", student -> {
                    ListDataProvider<Student> dataProvider = (ListDataProvider<Student>) grid.getDataProvider();
                    dataProvider.getItems().remove(student);
                    dataProvider.refreshAll();
                    
                    Notification.show("Server side event received");
                })).setHeader("Actions");
        
        grid.setItems(getDummyStudents(20));
        
        
        
        // 3.3 Use component
        // You can use any component inside the grid cells by providing a ComponentRenderer for the appropriate Column.
        grid.addColumn(new ComponentRenderer<>(student -> {
            if (student.getAge() >= 18) {
                return new Icon(VaadinIcon.LAPTOP);
            } else {
                return new Icon(VaadinIcon.FILE_ADD);
            }
        })).setHeader("Magic Icon");
        
        
        grid.addColumn(new ComponentRenderer<>(student -> {

            // text field for entering a new name for the person
            TextField lastName = new TextField("Last Name");
            lastName.setValue(student.getLastName());

            // button for saving the name to backend
            Button update = new Button("Update", event -> {
                student.setLastName(lastName.getValue());
                grid.getDataProvider().refreshItem(student);
            });

            // button that removes the item
            Button remove = new Button("Remove", event -> {
                ListDataProvider<Student> dataProvider = (ListDataProvider<Student>) grid.getDataProvider();
                dataProvider.getItems().remove(student);
                dataProvider.refreshAll();
            });

            // layouts for placing the text field on top of the buttons
            HorizontalLayout buttons = new HorizontalLayout(update, remove);
            return new VerticalLayout(lastName, buttons);
        })).setHeader("More Actions");
        
        
        
        // Showing Item Details
        // Often you don’t want to overwhelm the user with a complex grid with all the information about each item, 
        // but instead show just the basic information by default and hide the details. For this purpose, grid supports 
        // expanding its rows for showing additional details for the items. 
        grid.setItemDetailsRenderer(new ComponentRenderer<>(student -> {
            VerticalLayout layout = new VerticalLayout();
            layout.add(new Label("Address: " + student.getAddress().getStreet()
                    + " " + student.getAddress().getPostcode()));
            layout.add(new Label("Year of birth: " + student.getDob()));
            return layout;
        }));
        
        
        // Receiving sort events
        grid.addSortListener(event -> {
            Notification.show("Sort click event received");
        });
    }


    private void addGrid3() {
        H1 h1 = new H1("(No bean given) Nothing to scan, add cols by myself");
        Grid<Student> grid = new Grid<>();
        
        add(h1, grid);
        
        
        grid.setItems(getDummyStudents(20));
    }

    
    private void addGrid4() {
        H1 h1 = new H1("Grouping Column");
        Grid<Student> grid = new Grid<>(Student.class, false);
        
        add(h1, grid);
        Column<Student> firstNameCol = grid.addColumn("firstName");
        Column<Student> lastNameCol = grid.addColumn("lastName");
        
        Column<Student> ageCol = grid.addColumn("age");
        Column<Student> emailCol = grid.addColumn("email");
        
        // Grouping Column
        // Create a header row
        HeaderRow topRow = grid.prependHeaderRow();

        
        // group two columns under the same label
        topRow.join(firstNameCol, lastNameCol)
                .setComponent(new Label("Name Information"));

        // group the other two columns in the same header row
        topRow.join(ageCol, emailCol)
                .setComponent(new Label("Other Information"));
        
        
        grid.setItems(getDummyStudents(20));
        
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
