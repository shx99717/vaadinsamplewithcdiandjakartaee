package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.direct_access;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

/**
 * Visit directly by http://localhost:8080/openinanewtabwithdynamiccontent
 */
@Route("openinanewtabwithdynamiccontent")
public class OpenInANewTabWithDynamicContentView extends VerticalLayout {
    

    @PostConstruct
    public void init() {

        StreamResource dummyDynamicResource = new StreamResource("dummy.svg",
                () -> getImageInputStream());
        
        Anchor theDownloadLink = new Anchor(dummyDynamicResource, "");
        theDownloadLink.setTarget("_blank");
        Button theButton = new Button("See a dynamic content in the tab");
        // end up with <a target="_blank">..<button>See a dynamic content in the tab</button></a>
        theDownloadLink.add(theButton);
        
        add(theDownloadLink);
    }

    
    private InputStream getImageInputStream() {
        String svg = "<?xml version='1.0' encoding='UTF-8' standalone='no'?>"
            + "<svg  xmlns='http://www.w3.org/2000/svg' "
            + "xmlns:xlink='http://www.w3.org/1999/xlink'>"
            + "<rect x='10' y='10' height='100' width='100' "
            + "style=' fill: #90C3D4'/><text x='30' y='30' fill='red'>"
            + "This is dynamic content" + "</text>" + "</svg>";
        return new ByteArrayInputStream(svg.getBytes(StandardCharsets.UTF_8));
    }
}
