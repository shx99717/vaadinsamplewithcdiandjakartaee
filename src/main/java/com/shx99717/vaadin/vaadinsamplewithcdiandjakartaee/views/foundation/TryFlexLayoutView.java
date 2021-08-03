package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation;

import java.util.Arrays;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.AppMainEntry;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Try flex layout")
@Route(value = "tryflexlayout", layout = AppMainEntry.class)
public class TryFlexLayoutView extends VerticalLayout {

    public TryFlexLayoutView() {
        
        tryFlexDirection();
        
        tryJustifyContentMode();
    }

    private void tryFlexDirection() {
        H1 h1 = new H1("Try flex direction");
        Div div1 = new Div();
        div1.getStyle().set("background", "red");
        div1.setHeight("50px");
        div1.setWidth("150px");
        div1.setText("1");
        
        Div div2 = new Div();
        div2.getStyle().set("background", "blue");
        div2.setHeight("80px");
        div2.setWidth("220px");
        div2.setText("2");
        
        Div div3 = new Div();
        div3.getStyle().set("background", "yellow");
        div3.setHeight("150px");
        div3.setWidth("60px");
        div3.setText("3");
        
        FlexLayout flexLayout = new FlexLayout();
        flexLayout.add(div1, div2, div3);
        
        flexLayout.setFlexDirection(FlexDirection.COLUMN_REVERSE);
        
        
        HorizontalLayout buttonsContainer = new HorizontalLayout();
        Arrays.asList(FlexDirection.values()).stream().forEach(mode -> {
            Button button = new Button(mode.name());
            button.addClickListener(e -> flexLayout.setFlexDirection(mode));
            buttonsContainer.add(button);
        });
        
        add(h1, flexLayout, buttonsContainer);
    }
    
    private void tryJustifyContentMode() {
        H1 h1 = new H1("try justify content mode");
        Div div1 = new Div();
        div1.getStyle().set("background", "red");
        div1.setHeight("50px");
        div1.setWidth("150px");
        div1.setText("1");
        
        Div div2 = new Div();
        div2.getStyle().set("background", "blue");
        div2.setHeight("80px");
        div2.setWidth("220px");
        div2.setText("2");
        
        Div div3 = new Div();
        div3.getStyle().set("background", "yellow");
        div3.setHeight("150px");
        div3.setWidth("60px");
        div3.setText("3");
        
        FlexLayout flexLayout = new FlexLayout();
        flexLayout.add(div1, div2, div3);
        flexLayout.setSizeFull();
        
        // This sets the alignment for the primary axis
        flexLayout.setJustifyContentMode(JustifyContentMode.START);
        
        HorizontalLayout buttonsContainer = new HorizontalLayout();
        Arrays.asList(JustifyContentMode.values()).stream().forEach(mode -> {
            Button button = new Button(mode.name());
            button.addClickListener(e -> flexLayout.setJustifyContentMode(mode));
            buttonsContainer.add(button);
        });
       
        H3 h3_1 = new H3("alignment for the primary axis");
        add(h1, flexLayout, h3_1, buttonsContainer);
        
        // This sets the alignment for the secondary axis
        HorizontalLayout buttonsContainer2 = new HorizontalLayout();
        Arrays.asList(Alignment.values()).stream().forEach(mode -> {
            Button button = new Button(mode.name());
            button.addClickListener(e -> flexLayout.setAlignItems(mode));
            buttonsContainer2.add(button);
        });
        H3 h3_2 = new H3("alignment for the secondary axis");
        add(h3_2, buttonsContainer2);
        
    }
}
