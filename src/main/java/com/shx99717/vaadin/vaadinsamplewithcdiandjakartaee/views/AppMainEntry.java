package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views;

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
        tabs.add(createTab("Hello World 2", HelloWorldView.class));
        tabs.add(createTab("Hello World 3 dfsadf as fdsaf a dfa sfsd fas fsd fa", HelloWorldView.class));
        
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
