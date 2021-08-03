package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.hello_world;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.AppMainEntry;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Hello World")
@Route(value = "helloworld", layout = AppMainEntry.class)
@RouteAlias(value = "", layout = AppMainEntry.class)
public class HelloWorldView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public HelloWorldView() {
        addClassName("hello-world-view");
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> {
            Notification.show("Hello b " + name.getValue());
        });
    }

}
