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

	// -------------------------------------------------------------------------
	// There is a very good article explaining the how flexbox works
	// https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Flexible_Box_Layout/Basic_Concepts_of_Flexbox
	// -------------------------------------------------------------------------
	
	// There are also some predefined shorthand values which cover most of the use cases. 
	// You will often see these used in tutorials, and in many cases these are all you will need to use. 
	// The predefined values are as follows:
	// the shorthand is:
	// the flex property is applied on the item, NOT on the container
	// flex: <flex-grow> <flex-shrink> <flex-basis>
	// flex: initial == flex: 0 1 auto
    // flex: auto == flex: 1 1 auto
	// flex: none == flex: 0 0 auto
    // flex: <positive-number> == flex: <positive-number> <positive-number> 0
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
        
        // Change the item layouting direction <-- the main axis/primary axis
        // row|row-reverse|column|column-reverse
        // when main axis is decided, the cross axis is perpendicular to the main axis
        flexLayout.setFlexDirection(FlexDirection.COLUMN_REVERSE);
        
        
        HorizontalLayout buttonsContainer = new HorizontalLayout();
        Arrays.asList(FlexDirection.values()).stream().forEach(mode -> {
            Button button = new Button(mode.name());
            button.addClickListener(e -> flexLayout.setFlexDirection(mode));
            buttonsContainer.add(button);
        });
        
        add(h1, flexLayout, buttonsContainer);
    }
    
    // Control wrapping onto multiple lines if there is no available space
    // flex-warp: wrap|nowrap
    
    
    // Control how to distribute the available space on the main axis
    // flex-grow|flex-shrink|flex-basis

    
    
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
        
        // deal with the main axis alignment, NOT the available space
        // This sets the alignment for the primary axis/main axis
        // This adjusts the alignment of the items on the main axis. flex-start|flex-end|center|space-around|space-between|space-evenly
        flexLayout.setJustifyContentMode(JustifyContentMode.START);
        
        HorizontalLayout buttonsContainer = new HorizontalLayout();
        Arrays.asList(JustifyContentMode.values()).stream().forEach(mode -> {
            Button button = new Button(mode.name());
            button.addClickListener(e -> flexLayout.setJustifyContentMode(mode));
            buttonsContainer.add(button);
        });
       
        H3 h3_1 = new H3("alignment for the primary axis");
        add(h1, flexLayout, h3_1, buttonsContainer);
        
        // deal with the cross axis alignment, NOT the available space
        // This sets the alignment for the secondary axis/cross axis
        // This adjusts the alignment of the items on the cross axis. stretch|flex-start|flex-end|center
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
