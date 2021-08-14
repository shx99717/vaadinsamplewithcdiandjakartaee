package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.routeadvanced;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.Optional;

/**
 * Compare the Feature3View with Feature4NoCompositeView, the benefit of using a Composite rather than Div directly is
 * it hides the unnecessary api from the component, details please see {@link Composite}
 */
@Route(value="feature3", layout = MainLayout.class)
public class Feature3View extends Composite<Div> implements HasComponents {
    public Feature3View() {
        setId("id-feature3");
        add(new H2("This is the feature 3 view"));
    }
}
