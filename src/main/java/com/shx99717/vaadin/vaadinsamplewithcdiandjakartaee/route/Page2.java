package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.route;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("page2/here")
public class Page2 extends VerticalLayout {
    public Page2() {
        H1 h1 = new H1("Page2");
        add(h1);
    }
}
