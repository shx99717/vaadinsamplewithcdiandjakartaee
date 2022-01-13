package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.routebasic;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;

//see PageThrowsType2Exception.java
public class ErrorPageWhenType2Exception extends Div implements HasErrorParameter<Type2Exception> {

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<Type2Exception> parameter) {
        setText("Internal error happend -- type2 :(" + parameter.getException());
        return 500;
    }

}
