package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.component_simple_with_element_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Synchronize;
import com.vaadin.flow.component.Tag;

@Tag(Tag.INPUT)
// or There are constants for most, but not all, tag names.
//@Tag("input")
public class InputField extends Component {
	private static Logger logger = LoggerFactory.getLogger(InputField.class);
	public InputField(String value) {
		// We are calling setProperty not setAttribute, therefore you won't see the value
		// at the dom tag, for the difference between attribute and property, please look
		// at InputField2
		getElement().setProperty("value", value);
		getElement().setProperty("id", "dummy-input");
	}
	
	
	/**
	 * To make the component easier to use, you can add an API to get and set the value.
	 * 
	 */
	
	
	// Adding the @Synchronize annotation to the getter ensures that the browser sends property changes to the server.
	// The annotation defines the name of the DOM event that triggers synchronization, in this case a change event.
	// The @Synchronize annotation can specify multiple events and override the name of the property, if necessary.
	// By default deduces the name of the property from the name of the getterunless it has been specified using property().
	// This means, the change will watch on property called 'value' due to the automatical deduction
//	@Synchronize("change")
//	public String getValue() {
//		System.out.println("here ");
//		return getElement().getProperty("value");
//	}
	// if the name of the method can not match the property name, use the 'property' attribute
	@Synchronize(value = "change", property = "value")
	public String getInputValue() {
		logger.info("getter called ... ");
		return getElement().getProperty("value");
	}
	
	
	public void setValue(String value) {
	    getElement().setProperty("value", value);
	}
}
