package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.routebasic;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

// try http://domain/greet/vaadin --> works
// try http://domain/greet --> not work
@Route("greet")
public class GreetingComponent extends Div implements HasUrlParameter<String> {

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        H1 h1 = new H1("Welcome " + parameter);
        add(h1);
    }

}
