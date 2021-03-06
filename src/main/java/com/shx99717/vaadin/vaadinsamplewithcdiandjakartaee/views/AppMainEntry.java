package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation.TrySelectionComponentsView;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation.TryDataBindingAndProviderView;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation.TryFlexLayoutView;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation.TryFormConversionView;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation.TryFormDatabindingBufferedWayView;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation.TryFormDatabindingUnbufferedWayView;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation.TryFormLayoutView;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation.TryFormValidationView;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation.TryGridBasicView;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation.TryVaadinBoardView;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation.TryVerySimpleCustomComponentView;
import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.hello_world.HelloWorldView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;

@PWA(name = "Project base for CDI and JakartaEE", shortName = "Vaadin Sample With CDI And JakartaEE", enableInstallPrompt = false)
@PageTitle("AppMainEntry")
public class AppMainEntry extends AppLayout {
    private final Tabs tabs = new Tabs();

    public AppMainEntry() {
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.add(createTab("Hello World", HelloWorldView.class));
        tabs.add(createTab("Try flex layout", TryFlexLayoutView.class));
        tabs.add(createTab("Try form layout", TryFormLayoutView.class));
        tabs.add(createTab("Try Vaadin board", TryVaadinBoardView.class));
        tabs.add(createTab("Try form databinding unbuffered way", TryFormDatabindingUnbufferedWayView.class));
        tabs.add(createTab("Try form databinding buffered way", TryFormDatabindingBufferedWayView.class));
        tabs.add(createTab("Try form validation", TryFormValidationView.class));
        tabs.add(createTab("Try form conversion", TryFormConversionView.class));
        tabs.add(createTab("Try very simple custom component", TryVerySimpleCustomComponentView.class));
        tabs.add(createTab("Try grid basic", TryGridBasicView.class));
        tabs.add(createTab("Try selection components", TrySelectionComponentsView.class));
        tabs.add(createTab("Try data binding and provider", TryDataBindingAndProviderView.class));

        VerticalLayout drawerContent = new VerticalLayout();
        drawerContent.add(tabs);
        addToDrawer(drawerContent);
    }


    private Tab createTab(String menuText, Class<? extends Component> view) {
        Tab tab = new Tab();
        RouterLink link = new RouterLink();
        link.setRoute(view);
        link.add(new Text(menuText));
        tab.add(link);
        return tab;
    }

}
