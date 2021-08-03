package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.AppMainEntry;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Try form layout")
@Route(value = "tryformlayout", layout = AppMainEntry.class)
public class TryFormLayoutView extends VerticalLayout {

    public TryFormLayoutView() {
        
        tryResponsiveSteps();
        
        tryLabelAndFieldUpAndDown();
        
        tryLabelAndFieldLeftAndRight();
    }

    private void tryResponsiveSteps() {
        H1 h1 = new H1("Try responsive steps");
        FormLayout formLayout = new FormLayout();
        
        // Show 4 columns when the width is >= 800px
        // Show 3 columns when the width is >= 500px
        // Show 2 columns when the width is >= 200px
        // Show 1 column when the width is [0-200)px
        formLayout.setResponsiveSteps(
                new ResponsiveStep("0px", 1), //
                new ResponsiveStep("200px", 2), //
                new ResponsiveStep("500px", 3), //
                new ResponsiveStep("800px", 4) //
        );
        
        TextField textField1 = new TextField("Field 1");
        TextField textField2 = new TextField("Field 2");
        TextField textField3 = new TextField("Field 3");
        TextField textField4 = new TextField("Field 4");
        TextField textField5 = new TextField("Field 5");
        TextField textField6 = new TextField("Field 6");
        TextField textField7 = new TextField("Field 7");
        TextField textField8 = new TextField("Field 8");
        TextField textField9 = new TextField("Field 9");
        
        
        formLayout.add(textField1, textField2, textField3, textField4, textField5, textField6, textField7, textField8, textField9);
        add(h1, formLayout);
    }
    
    private void tryLabelAndFieldUpAndDown() {
        H1 h1 = new H1("Label at top and field at bottom");
        FormLayout formLayout = new FormLayout();
        
        
        TextField textField1 = new TextField("Field 1");
        TextField textField2 = new TextField("Field 2");
        TextField textField3 = new TextField("Field 3");
        TextField textField4 = new TextField("Field 4");
        TextField textField5 = new TextField("Field 5");
        TextField textField6 = new TextField("Field 6");
        TextField textField7 = new TextField("Field 7");
        TextField textField8 = new TextField("Field 8");
        TextField textField9 = new TextField("Field 9");
        
        
        formLayout.add(textField1, textField2, textField3, textField4, textField5, textField6, textField7, textField8, textField9);
        add(h1, formLayout);
        
    }
    
    
    private void tryLabelAndFieldLeftAndRight() {
        H1 h1 = new H1("Label at left and field at right");
        FormLayout formLayout = new FormLayout();

        TextField textField1 = new TextField();
        textField1.setSizeFull();
        TextField textField2 = new TextField();
        textField2.setSizeFull();
        TextField textField3 = new TextField();
        textField3.setSizeFull();
        TextField textField4 = new TextField();
        textField4.setSizeFull();
        TextField textField5 = new TextField();
        textField5.setSizeFull();
        TextField textField6 = new TextField();
        textField6.setSizeFull();
        TextField textField7 = new TextField();
        textField7.setSizeFull();
        TextField textField8 = new TextField();
        textField8.setSizeFull();
        TextField textField9 = new TextField();
        textField9.setSizeFull();
        
        formLayout.addFormItem(textField1, "Field 1");
        formLayout.addFormItem(textField2, "Field 2");
        formLayout.addFormItem(textField3, "Field 3");
        formLayout.addFormItem(textField4, "Field 4");
        formLayout.addFormItem(textField5, "Field 5");
        formLayout.addFormItem(textField6, "Field 6");
        formLayout.addFormItem(textField7, "Field 7");
        formLayout.addFormItem(textField8, "Field 8");
        formLayout.addFormItem(textField9, "Field 9");
        
        add(h1, formLayout);
        
    }
}
