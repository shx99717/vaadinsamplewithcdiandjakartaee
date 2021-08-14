package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.routing;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

/**
 * At the time HomeView entered, if without using the layout = MainLayout.class on the @Route definition
 * then the content of this view will be by default rendered in the body of the html page, but now with
 * the layout setup, it will find the referencing class MainLayout.class, and rendering will be delegate to
 * the MainLayout.class, the content of this view will be rendered by {@link MainLayout#showRouterLayoutContent(com.vaadin.flow.component.HasElement)}
 * 
 */
@Route(value="home", layout = MainLayout.class)
@RouteAlias(value="homealias", layout = MainLayout.class)
@PageTitle("Home")
public class HomeView extends Composite<VerticalLayout> implements HasComponents {

	public HomeView() {
	    setId("id-home");
		add(new H2("This is the home view"));
	}
}
