package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.routing;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.Optional;

@Route(value="feature2", layout = MainLayout.class)
public class Feature2View extends Composite<Div> implements HasComponents {
    public Feature2View() {
        setId("id-feature2");
        add(new H2("This is the feature 2 view"));
    }
}
