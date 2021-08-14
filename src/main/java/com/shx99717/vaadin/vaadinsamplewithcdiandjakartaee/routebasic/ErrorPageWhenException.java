package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.routebasic;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;

public class ErrorPageWhenException extends Div implements HasErrorParameter<Exception> {

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<Exception> parameter) {
        setText("Internal error happend :(");
        return 500;
    }

}
