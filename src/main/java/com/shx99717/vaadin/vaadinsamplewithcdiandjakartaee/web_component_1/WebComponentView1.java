package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.web_component_1;

import javax.annotation.PostConstruct;

import org.vaadin.addons.PaperSlider;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * Visit directly by http://localhost:8080/web_component_1
 */
@Route("web_component_1")
public class WebComponentView1 extends VerticalLayout {

	@PostConstruct
	public void init() {
		// This PaperSlider is made as a Vaadin Flow add on in a separated jar
		PaperSlider paperSlider = new PaperSlider();
		add(paperSlider);
		
	}

}
