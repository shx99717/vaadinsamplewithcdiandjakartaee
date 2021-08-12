package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.service.GreetService;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.service.StudentBean;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.AppMainEntry;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.filter.StudentSearchFilter;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.Address;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.Student;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo.StudentType;
import com.vaadin.flow.component.button.Button;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Try data binding and provider")
@Route(value = "trydatabindingandprovider", layout = AppMainEntry.class)
public class TryDataBindingAndProviderView extends VerticalLayout {
    private static final Logger LOGGER = LoggerFactory.getLogger(TryDataBindingAndProviderView.class);
    @Inject
    private StudentBean studentBean;

    public TryDataBindingAndProviderView() {
        addGrid1();
        
        addGrid2();

        addGrid3();
    }


    private void addGrid1() {
        H1 h1 = new H1("Data provider - give the collection directly - In-Memory");
        Grid<Student> grid = new Grid<>(Student.class);

        add(h1, grid);

        // This is the same as grid.setDataProvider(DataProvider.ofCollection(items));
        // when call setItems, it is internally using ListDataProvider
        List<Student> students = getDummyStudents(20);
        grid.setItems(students);


        Button changeOneItem = new Button("Change first item internally");
        changeOneItem.addClickListener(e -> {
            students.get(0).setFirstName("----changed----");
        });

        // If you update any of the data from a provider, the provider will NOT know of the change
        // Whenever you need to refresh a provider, call this
        Button refreshOneItem = new Button("Refresh one item, UI will also be updated");
        refreshOneItem.addClickListener(e -> {
            grid.getDataProvider().refreshItem(students.get(0));
        });

        // If you update any of the data from a provider, the provider will NOT know of the change
        // Whenever you need to refresh a provider, call this
        Button refreshAllItems = new Button("Refresh all items, UI will also be updated");
        refreshAllItems.addClickListener(e -> {
            grid.getDataProvider().refreshAll();
        });

        add(changeOneItem, refreshOneItem, refreshAllItems);
    }
    
    
    private void addGrid2() {
        H1 h1 = new H1("Data provider - Lazy loading provider - CallbackDataProvider - no filtering(Void)");
        Grid<Student> grid = new Grid<>(Student.class);

        add(h1, grid);

        DataProvider<Student, Void> dataProvider = DataProvider.fromCallbacks(
                query -> studentBean.getStudents(query), 
                query -> studentBean.countStudents());
        
        grid.setDataProvider(dataProvider);
    }

    private void addGrid3() {
        H1 h1 = new H1("Data provider - Lazy loading provider - CallbackDataProvider - Filtering provided");
        Grid<Student> grid = new Grid<>(Student.class);

        add(h1, grid);

        DataProvider<Student, StudentSearchFilter> dataProvider = DataProvider.fromFilteringCallbacks(
                query -> studentBean.getStudentsWithFilter(query), 
                query -> studentBean.countStudentsWithFilter());
        ConfigurableFilterDataProvider<Student, Void, StudentSearchFilter> configurableFilterDataProvider = dataProvider.withConfigurableFilter();
        grid.setDataProvider(configurableFilterDataProvider);
        
        
        
        Button filterButton = new Button("Filter the result");
        filterButton.addClickListener(e -> {
            StudentSearchFilter studentSearchFilter = new StudentSearchFilter("he", "wo");
            configurableFilterDataProvider.setFilter(studentSearchFilter);
            // grid will be updated automatically
            
        });
        
        add(filterButton);
        
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
