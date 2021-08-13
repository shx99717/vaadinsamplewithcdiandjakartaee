package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.navigation;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.route.Page1;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.route.Page2;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;


@PageTitle("This is the page A")
@Route("pagea")
public class PageA extends VerticalLayout {

    public PageA() {

        addNav1();

        addNav2();

        addNav3();
    }

    private void addNav1() {
        H1 h1 = new H1("Navigation - RouterLink");
        H3 h3 = new H3("RouterLink only works for navigation inside a website or web application");
        VerticalLayout menuContainer = new VerticalLayout();
        menuContainer.add(new RouterLink("Page1", Page1.class));
        menuContainer.add(new RouterLink("Page2", Page2.class));

        add(h1, h3, menuContainer);
    }


    private void addNav2() {
        H1 h1 = new H1("Navigation - Anchor");
        H3 h3 = new H3("Anchor works for navigation outside of the website and web application");
        VerticalLayout menuContainer = new VerticalLayout();
        menuContainer.add(new Anchor("https://vaadin.com/", "Vaadin"));
        menuContainer.add(new Anchor("https://google.com/", "Google"));

        add(h1, h3, menuContainer);
    }


    private void addNav3() {
        H1 h1 = new H1("Navigation - UI.navigate() e.g. when click a button");
        VerticalLayout menuContainer = new VerticalLayout();

        Button button1 = new Button("Navigate to Page1");
        button1.addClickListener(e -> {
            button1.getUI().ifPresent(ui -> ui.navigate(Page1.class));
        });

        Button button2 = new Button("Navigate to Page3");
        button2.addClickListener(e -> {
            button2.getUI().ifPresent(ui -> ui.navigate("page3"));
        });


        menuContainer.add(button1, button2);

        add(h1, menuContainer);
    }
}
