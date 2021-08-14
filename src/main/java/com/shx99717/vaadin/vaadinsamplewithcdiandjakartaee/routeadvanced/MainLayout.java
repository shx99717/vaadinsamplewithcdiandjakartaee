package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.routeadvanced;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

// If using @ParentLayout here, we can do nested layouting mentioned by
// nested_layouting.png and nested_layouting_code_structure.png
/**
 * This layout will be referred by all ChildViews in the picture typical_application_layout.png
 * when click the link in the menubar, the Header, MenuBar and Footer will stay the same, only the ChildView will
 * be updated with new view, where this is done in the method showRouterLayoutContent()
 * 
 * 
 * 
 * - BeforeEnterEvent is fired when a component is being attached, good place for authentication 
 * - BeforeLeaveEvent is fired when a component is being detached, good place for confirmation
 * - AfterNavigationEvent is really triggered when the navigation is done, good place for update menu selections
 * 
 */
public class MainLayout extends Composite<VerticalLayout> implements HasComponents, RouterLayout, BeforeEnterObserver, BeforeLeaveObserver, AfterNavigationObserver {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainLayout.class);
    
    // According to the ChildView part in the typical_application_layout.png
    private Div childView = new Div();

    public MainLayout() {
        getContent().setSizeFull();

        // According to the Header part in the typical_application_layout.png
        H1 header = new H1("Header");
        add(header);

        // According to the MenuBar + ChildView in the typical_application_layout.png
        HorizontalLayout mainContent = new HorizontalLayout();
        
        // According to the menuBar in the typical_application_layout.png
        VerticalLayout menuBar = new VerticalLayout();
        menuBar.setWidth("20%");
        menuBar.add(new RouterLink("Home", HomeView.class));
        menuBar.add(new RouterLink("Feature1", Feature1View.class));
        menuBar.add(new RouterLink("Feature2", Feature2View.class));
        menuBar.add(new RouterLink("Feature3", Feature3View.class));
        menuBar.add(new RouterLink("Feature4NoComposite", Feature4NoCompositeView.class));
        
        
        mainContent.add(menuBar);
        mainContent.add(childView);
        mainContent.setFlexGrow(1, childView);

        add(mainContent);

        
        // According to the Footer part in the typical_application_layout.png
        H1 footer = new H1("Footer");
        add(footer);

        getContent().setFlexGrow(1, mainContent);
        getContent().setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, header);
        getContent().setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, footer);
        getContent().setHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH, mainContent);
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        LOGGER.info("content with id = " + content.getElement().getAttribute("id"));
        childView.getElement().appendChild(content.getElement());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        LOGGER.info("before enter is called");
        // We could reroute to some page if some condition is not met, e.g. not logined, then navigate to the logic page
        // e.g. reroute to the error page under some condition by event.rerouteToError(XXXException.class);
        // event.rerouteTo("some other page");
    }

    @Override
    public void beforeLeave(BeforeLeaveEvent event) {
        LOGGER.info("before leave is called");
        // e.g. when can show a dialog to confirm the user want to leave the page, this is the good place
        // to add such logic
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        LOGGER.info("after navigation is called");
        // e.g. good place to update the menuBar with some highlight etc
    }
    

}
