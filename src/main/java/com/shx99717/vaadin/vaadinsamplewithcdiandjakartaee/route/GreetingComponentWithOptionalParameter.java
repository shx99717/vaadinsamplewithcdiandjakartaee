package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.route;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

// try http://domain/greet2/vaadin --> works
// try http://domain/greet2 --> works
@Route("greet2")
public class GreetingComponentWithOptionalParameter extends Div implements HasUrlParameter<String> {

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        H1 h1 = new H1("Welcome " + parameter);
        add(h1);
    }

}
