package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.component_5_extending_serverside;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.textfield.TextField;

/**
 * Extending a Component Using the Server-side Approach
 */
@CssImport(value = "styles/module/numeric-field-styles.css", themeFor = "vaadin-text-field")
public class NumericField extends TextField {
	private Button substractBtn;
	private Button addBtn;

	private static final int DEFAULT_VALUE = 0;
	private static final int DEFAULT_INCREMENT = 1;

	private int numericValue;
	private int incrementValue;
	private int decrementValue;

	public NumericField() {
		this(DEFAULT_VALUE, DEFAULT_INCREMENT, -DEFAULT_INCREMENT);
	}

	public NumericField(int value, int incrementValue, int decrementValue) {
		setNumericValue(value);
		this.incrementValue = incrementValue;
		this.decrementValue = decrementValue;

		setPattern("-?[0-9]*");
		setPreventInvalidInput(true);

		addChangeListener(event -> {
			String text = event.getSource().getValue();
			if (StringUtils.isNumeric(text)) {
				setNumericValue(Integer.parseInt(text));
			} else {
				setNumericValue(DEFAULT_VALUE);
			}
		});

		substractBtn = new Button("-", event -> {
			setNumericValue(numericValue + decrementValue);
		});

		addBtn = new Button("+", event -> {
			setNumericValue(numericValue + incrementValue);
		});

		getElement().setAttribute("theme", "numeric");
		styleBtns();

		addToPrefix(substractBtn);
		addToSuffix(addBtn);
	}

	private void styleBtns() {
		// Note: The same as addThemeVariants
		substractBtn.getElement().setAttribute("theme", "icon");
		addBtn.getElement().setAttribute("theme", "icon");
	}

	public void setNumericValue(int value) {
		numericValue = value;
		setValue(value + "");
	}
	
	
}
