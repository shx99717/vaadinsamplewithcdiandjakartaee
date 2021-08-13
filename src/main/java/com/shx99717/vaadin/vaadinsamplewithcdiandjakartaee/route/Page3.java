package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.route;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.Route;

// Route can be on any component
@Route("page3")
public class Page3 extends Paragraph {
    public Page3() {
        H1 h1 = new H1("Page3 - this is a paragraph");
        add(h1);
    }
}
