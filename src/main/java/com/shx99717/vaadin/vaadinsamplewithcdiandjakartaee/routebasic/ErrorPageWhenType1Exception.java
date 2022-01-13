package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.routebasic;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;

// see PageThrowsType1Exception.java
public class ErrorPageWhenType1Exception extends Div implements HasErrorParameter<Type1Exception> {

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<Type1Exception> parameter) {
    	setText("Internal error happend -- type1 :(" + parameter.getException());
        return 500;
    }

}
