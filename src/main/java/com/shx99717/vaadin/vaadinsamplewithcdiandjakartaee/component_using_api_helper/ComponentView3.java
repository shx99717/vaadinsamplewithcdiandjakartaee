package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.component_using_api_helper;

import java.util.Optional;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * Visit directly by http://localhost:8080/component_api_helper
 */
@Route("component_api_helper")
public class ComponentView3 extends VerticalLayout {

    @PostConstruct
    public void init() {
    	InputField2 inputField = new InputField2("this is the default value");
        add(inputField);
        
        Button setValuePropertyButton = new Button("set the value property");
        Button getValuePropertyButton = new Button("get the value property");
        
        setValuePropertyButton.addClickListener(e -> {
        	inputField.setValue("ni hao value property");
        });
        
        getValuePropertyButton.addClickListener(e -> {
        	String value = inputField.getValue();
        	Notification.show("the value property is : " + value);
        });
        
        add(setValuePropertyButton, getValuePropertyButton);
        
        
        
        
        
        
        
        
        
        
        Button setDummyAttributeButton = new Button("set the dummy attribute");
        Button getDummyAttributeButton = new Button("get the dummy attribute");
        
        setDummyAttributeButton.addClickListener(e -> {
        	inputField.setDummy("ni hao dummy attribute");
        });
        
        getDummyAttributeButton.addClickListener(e -> {
        	String dummy = inputField.getDummy();
        	Notification.show("the dummy attribute is : " + dummy);
        });
        
        add(setDummyAttributeButton, getDummyAttributeButton);
        
        
        
        
        
        
        
        
        
        
        
        
        Button setOptionalAttributeButton = new Button("set the optional attribute");
        Button getOptionalAttributeButton = new Button("get the optional attribute");
        
        setOptionalAttributeButton.addClickListener(e -> {
        	inputField.setOptional("ni hao optional attribute");
        });
        
        getOptionalAttributeButton.addClickListener(e -> {
        	Optional<String> optional = inputField.getOptional();
        	optional.ifPresent(v -> {
        		
        	});
        	if(optional.isPresent()) {
        		Notification.show("the optional attribute is : " + optional.get());
        	} else {
        		Notification.show("the optional attribute is not presented ");
        	}
        });
        
        add(setOptionalAttributeButton, getOptionalAttributeButton);
        
        
    }

}
