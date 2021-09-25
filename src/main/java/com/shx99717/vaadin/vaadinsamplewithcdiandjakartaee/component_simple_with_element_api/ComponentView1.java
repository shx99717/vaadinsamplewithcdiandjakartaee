package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.component_simple_with_element_api;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * Visit directly by http://localhost:8080/component_simple
 */
@Route("component_simple")
public class ComponentView1 extends VerticalLayout {

    @PostConstruct
    public void init() {
    	InputField inputField = new InputField("this is the default value");
        add(inputField);
        
        Button setValueButton = new Button("set the value");
        Button getValueButton = new Button("get the value");
        
        setValueButton.addClickListener(e -> {
        	inputField.setValue("ni hao");
        });
        
        // without putting @Synchronize on the getter method, the change from browser to the input field won't be
        // propagate to the server side counter part value.
        getValueButton.addClickListener(e -> {
        	String value = inputField.getInputValue();
        	Notification.show("the value is : " + value);
        });
        
        add(setValueButton, getValueButton);
    }

}
