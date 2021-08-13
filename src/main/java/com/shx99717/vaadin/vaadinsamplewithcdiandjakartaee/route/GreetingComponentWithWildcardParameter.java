package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.route;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.WildcardParameter;

// try http://domain/greet3/vaadin --> works
// try http://domain/greet3/a --> works
// try http://domain/greet3/b --> works
// try http://domain/greet3/c --> works
// try http://domain/greet3 --> works
@Route("greet3")
public class GreetingComponentWithWildcardParameter extends Div implements HasUrlParameter<String> {

    @Override
    public void setParameter(BeforeEvent event, @WildcardParameter String parameter) {
        H1 h1 = new H1("Welcome " + parameter);
        add(h1);
    }

}
