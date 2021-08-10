package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.component;

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
/**
 * see: https://www.webcomponents.org/element/@polymer/paper-toggle-button
 * <p>
 * run
 * <p> 
 * {@code install --save @polymer/paper-toggle-button}
 * <p>
 * to install the web component on project's directory
 * 
 */
@JsModule("@polymer/paper-toggle-button/paper-toggle-button.js")
@Tag("paper-toggle-button")
public class ToggleButton extends AbstractSinglePropertyField<ToggleButton, Boolean> {

    private static final long serialVersionUID = 2532268473293501898L;

    public ToggleButton() {
        super("checked", false, false);
    }

}
