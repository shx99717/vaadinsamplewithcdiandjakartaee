package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.component_2_multiple_elements;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * Visit directly by http://localhost:8080/component_multiple
 */
@Route("component_multiple")
public class ComponentView2 extends VerticalLayout {

    @PostConstruct
    public void init() {
    	InputFieldWithLabel inputFieldWithLabel = new InputFieldWithLabel("super label", "super value");
        add(inputFieldWithLabel);
        
        Button setValueButton = new Button("set the value");
        Button getValueButton = new Button("get the value");
        
        setValueButton.addClickListener(e -> {
        	inputFieldWithLabel.setValue("ni hao");
        });
        
        getValueButton.addClickListener(e -> {
        	String value = inputFieldWithLabel.getValue();
        	Notification.show("the value is : " + value);
        });
        
        add(setValueButton, getValueButton);
    }

}
