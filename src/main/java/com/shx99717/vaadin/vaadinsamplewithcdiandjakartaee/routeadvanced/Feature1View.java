package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.routeadvanced;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.Route;

@Route(value="feature1", layout = MainLayout.class)
public class Feature1View extends Composite<Div> implements HasComponents {
    public Feature1View() {
        setId("id-feature1");
        add(new H2("This is the feature 1 view"));
    }
}
