package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.component_multiple_elements;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.dom.Element;

/**
 * The dom structure expected <div> <label></label> <input> </div>
 */
@Tag(Tag.DIV)
public class InputFieldWithLabel extends Component {
	private Element labelElement = new Element("label");
	private Element inputElement = new Element("input");

	public InputFieldWithLabel(String labelText, String inputText) {
		inputElement.synchronizeProperty("value", "change");
		getElement().appendChild(labelElement, inputElement);
		
		setLabel(labelText);
		setValue(inputText);
	}

	public String getLabel() {
		return labelElement.getText();
	}

	public String getValue() {
		return inputElement.getProperty("value");
	}

	public void setLabel(String label) {
		labelElement.setText(label);
	}

	public void setValue(String value) {
		inputElement.setProperty("value", value);
	}
}
