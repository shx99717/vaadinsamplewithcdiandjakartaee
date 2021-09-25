package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.component_using_api_helper;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Synchronize;
import com.vaadin.flow.component.Tag;

@Tag(Tag.INPUT)
public class InputField2 extends Component {
	private static Logger logger = LoggerFactory.getLogger(InputField2.class);

	// it also comes with PropertyDescriptors.attributeWithDefault(...), so the
	// question is
	// what is the difference between attribute and property

	// HTML attribute vs. DOM property
	// Attributes are defined by HTML.
	// Properties are defined by the DOM (Document Object Model).
	// | A few HTML attributes have 1:1 mapping to properties. id is one example.
	// | Some HTML attributes don't have corresponding properties. colspan is one
	// example.
	// | Some DOM properties don't have corresponding attributes. textContent is one
	// example.
	// | Many HTML attributes appear to map to properties ... but not in the way you
	// might think!
	// Attributes initialize DOM properties and then they are done. Property values
	// can change; attribute values can't.

	/**
	 * For example, when the browser renders <input type="text" value="Bob">, it
	 * creates a corresponding DOM node with a value property initialized to "Bob".
	 * When the user enters "Sally" into the input box, the DOM element value
	 * property becomes "Sally". But the HTML value attribute remains unchanged as
	 * you discover if you ask the input element about that attribute:
	 * input.getAttribute('value') returns "Bob". The HTML attribute value specifies
	 * the initial value; the DOM value property is the current value.
	 */
	private static PropertyDescriptor<String, String> VALUE = PropertyDescriptors.propertyWithDefault("value",
			"#default value#");
	private static PropertyDescriptor<String, String> DUMMY = PropertyDescriptors.attributeWithDefault("dummy",
			"the dummy value");
	private static PropertyDescriptor<String, Optional<String>> OPTIONAL = PropertyDescriptors
			.optionalAttributeWithDefault("optional", "the optional value");

	public InputField2(String value) {
		setValue(value);
	}

	@Synchronize("change")
	public String getValue() {
		return get(VALUE);
	}

	public void setValue(String value) {
		set(VALUE, value);
	}

	@Synchronize("change")
	public String getDummy() {
		return get(DUMMY);
	}

	public void setDummy(String dummy) {
		set(DUMMY, dummy);
	}

	@Synchronize("change")
	public Optional<String> getOptional() {
		return get(OPTIONAL);
	}

	public void setOptional(String optional) {
		set(OPTIONAL, optional);
	}
}
