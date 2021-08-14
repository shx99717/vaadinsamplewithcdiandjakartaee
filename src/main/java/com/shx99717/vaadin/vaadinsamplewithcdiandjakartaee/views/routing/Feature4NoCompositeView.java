package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.routing;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.Route;

@Route(value = "feature4", layout = MainLayout.class)
public class Feature4NoCompositeView extends Div implements HasComponents {
    public Feature4NoCompositeView() {
        setId("id-feature4-no-composite");
        add(new H2("This is the feature 4 no composite view"));
    }
}
