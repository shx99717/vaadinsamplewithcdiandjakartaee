package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.routebasic;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

// @Route("") this will be the root context
@Route("pagethrowstype2exception")
public class PageThrowsType2Exception extends VerticalLayout {
    public PageThrowsType2Exception() throws Exception {
        H1 h1 = new H1("Page throws new Type2Exception()");
        add(h1);

        throw new Type2Exception();
        
    }
}
