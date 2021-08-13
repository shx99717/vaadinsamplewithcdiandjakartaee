package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.route;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

//@Route("") this will be the root context
@PageTitle("This is the page 1")
@Route("page1")
@RouteAlias("page_one")
@RouteAlias("page_yi")
@RouteAlias("page_I")
public class Page1 extends VerticalLayout {
    public Page1() {
        H1 h1 = new H1("Page1");
        add(h1);
    }
}
