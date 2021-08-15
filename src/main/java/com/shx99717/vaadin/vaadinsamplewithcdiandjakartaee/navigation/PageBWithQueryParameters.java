package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.navigation;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.Route;

// Access the page with URL http://localhost:8080/pageb?key1=value1&key2=value2&key1=value11
@Route("pageb")
public class PageBWithQueryParameters extends VerticalLayout implements BeforeEnterObserver {
    private static Logger logger = LoggerFactory.getLogger(PageBWithQueryParameters.class);

    public PageBWithQueryParameters() {
        H1 h1 = new H1("Page B with query parameters");
        add(h1);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Location location = event.getLocation();
        logger.info("first segment = " + location.getFirstSegment());
        logger.info("path = " + location.getPath());
        logger.info("path with query parameters = " + location.getPathWithQueryParameters());

        logger.info("location.getQueryParameters().getQueryString() = " + location.getQueryParameters().getQueryString());
        Map<String, List<String>> params = location.getQueryParameters().getParameters();
        params.forEach((key, value) -> {
            logger.info(key + " -> " + value);
        });
    }

}
