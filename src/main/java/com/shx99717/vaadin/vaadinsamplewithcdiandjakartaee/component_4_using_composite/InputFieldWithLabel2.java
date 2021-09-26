package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.component_4_using_composite;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.component_2_multiple_elements.InputFieldWithLabel;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.Label;

/**
 * Creating the component based on a Composite is the best practice in these
 * circumstances. While it is possible to create a new component by extending
 * the Div HTML component, this is not advisable, because it unnecessarily
 * exposes Div API methods, such as add(Component), to the user.
 * 
 * Compare to the {@link InputFieldWithLabel}, using Composite way is
 * recommended
 * 
 * The structure is: 
 *  Div 
 *      - Label 
 *      - Input
 * Result in:
 * <div>
 * 		<label>the label</label>
 * 		<input>
 * </div>
 * | The Composite automatically creates the root component (specified using
 * generics (Composite<Div>)). | The root component is available through the
 * getContent() method. | In the constructor, it is only necessary to create the
 * child components and add them to the root Div.
 */
public class InputFieldWithLabel2 extends Composite<Div> {
	private Label label;
	private Input input;

	public InputFieldWithLabel2(String labelText, String value) {
		label = new Label();
		label.setText(labelText);

		input = new Input();
		input.setValue(value);

		getContent().add(label, input);
	}

	/////////////////////////////////////
	// The public API only exposes the defined methods, 
	// and a few generic methods defined in the Component interface.
	/////////////////////////////////////
	
	
	public String getValue() {
		return input.getValue();
	}

	public void setValue(String value) {
		input.setValue(value);
	}

	public String getLabel() {
		return label.getText();
	}

	public void setLabel(String labelText) {
		label.setText(labelText);
	}
}
