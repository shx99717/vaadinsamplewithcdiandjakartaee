package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.component_5_extending_serverside;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * Visit directly by http://localhost:8080/component_server_side_extension
 */
@Route("component_server_side_extension")
public class ComponentView5 extends VerticalLayout {

	@PostConstruct
	public void init() {
		Paragraph paragraph = new Paragraph("Extending a Component Using the Server-side Approach");
		NumericField numericField = new NumericField();
		
		add(paragraph, numericField);
	}

}
