package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation_exercise;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.AppMainEntry;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Foundation Exercise")
@Route(value = "foundationexercise", layout = AppMainEntry.class)
public class FoundationExerciseView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public FoundationExerciseView() {
        Label label = new Label("dummy page");
        add(label);
    }

}
