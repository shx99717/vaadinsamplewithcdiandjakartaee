package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.component_2_multiple_elements;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.component_4_using_composite.InputFieldWithLabel2;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.dom.Element;

/**
 * The dom structure will result in:
 *  <div>
 *  	<label>super label</label>
 *  	<input>
 *  </div>
 * This is working at pure element level which is regarded as working with low level API,
 * for the recommended way, please refer to {@link InputFieldWithLabel2} which is using
 * a higher API and the Vaadin flow recommended way to make a complex reusable component
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
