package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.component_4_using_composite;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * Visit directly by http://localhost:8080/component_composite
 */
@Route("component_composite")
public class ComponentView4 extends VerticalLayout {

	@PostConstruct
	public void init() {
		InputFieldWithLabel2 inputField = new InputFieldWithLabel2("the label", "the value");
		add(inputField);

		Button setValueButton = new Button("set the value");
		Button getValueButton = new Button("get the value");

		setValueButton.addClickListener(e -> {
			inputField.setValue("ni hao value");
		});

		getValueButton.addClickListener(e -> {
			String value = inputField.getValue();
			Notification.show("the value is : " + value);
		});

		add(setValueButton, getValueButton);

	}

}
