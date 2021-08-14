package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.routebasic;

import java.util.UUID;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;

@Route("page4")
public class Page4 extends Paragraph implements HasDynamicTitle {
    public Page4() {
        H1 h1 = new H1("Page4 - this page has a dynamic content");
        add(h1);
    }

    @Override
    public String getPageTitle() {
        // or set by
        // UI.getCurrent().getPage().setTitle(..);
        return UUID.randomUUID().toString();
    }
}
