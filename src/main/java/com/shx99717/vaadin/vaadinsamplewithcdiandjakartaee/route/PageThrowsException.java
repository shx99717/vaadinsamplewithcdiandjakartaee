package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.route;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

// @Route("") this will be the root context
@Route("pagethrowsexception")
public class PageThrowsException extends VerticalLayout {
    public PageThrowsException() throws Exception {
        H1 h1 = new H1("Page throws Exception");
        add(h1);

        // This will show ErrorPageWhenException.java
        throw new Exception("some exception thrown");
    }
}
