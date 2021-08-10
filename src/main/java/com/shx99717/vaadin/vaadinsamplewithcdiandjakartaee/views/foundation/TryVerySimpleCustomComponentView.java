package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.AppMainEntry;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.component.DateTimePicker;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.component.ToggleButton;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Try very simple custom component")
@Route(value = "tryverysimplecustomcomponent", layout = AppMainEntry.class)
public class TryVerySimpleCustomComponentView extends VerticalLayout {
    private static final Logger LOGGER = LoggerFactory.getLogger(TryVerySimpleCustomComponentView.class);


    public TryVerySimpleCustomComponentView() {
        H1 customComponent1H1 = new H1("DateTimePicker made of DatePicker and TimePicker");
        DateTimePicker dateTimePicker = new DateTimePicker();

        add(customComponent1H1, dateTimePicker);


        H1 webComponent1H1 = new H1("simple integration of @polymer/paper-toggle-button ");
        ToggleButton toggleButton = new ToggleButton();
        add(webComponent1H1, toggleButton);
    }



}
