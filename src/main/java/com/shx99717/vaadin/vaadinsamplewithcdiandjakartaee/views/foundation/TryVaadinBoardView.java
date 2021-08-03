package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.AppMainEntry;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Try Vaadin board")
@Route(value = "tryvaadinboard", layout = AppMainEntry.class)
public class TryVaadinBoardView extends VerticalLayout {

    public TryVaadinBoardView() {
        
        tryDummyBoard();

        // tryLabelAndFieldUpAndDown();

        // tryLabelAndFieldLeftAndRight();
    }

    private void tryDummyBoard() {
        H1 h1 = new H1("Try dummy board");
        
        Board board = new Board();
        
        Div div1 = new Div();
        div1.getStyle().set("background", "red");
        div1.setHeight("300px");
        div1.setText("1");
        
        Div div2 = new Div();
        div2.getStyle().set("background", "blue");
        div2.setHeight("300px");
        div2.setText("2");
        
        Div div3 = new Div();
        div3.getStyle().set("background", "yellow");
        div3.setHeight("300px");
        div3.setText("3");
        
        
        Div div4 = new Div();
        div4.getStyle().set("background", "green");
        div4.setHeight("300px");
        div4.setText("4");
        
        Div div5 = new Div();
        div5.getStyle().set("background", "pink");
        div5.setHeight("300px");
        div5.setText("5");
        
        Div div6 = new Div();
        div6.getStyle().set("background", "black");
        div6.setHeight("300px");
        div6.setText("6");
        
        Div div7 = new Div();
        div7.getStyle().set("background", "gold");
        div7.setHeight("300px");
        div7.setText("7");
        
        Div div8 = new Div();
        div8.getStyle().set("background", "gray");
        div8.setHeight("300px");
        div8.setText("8");
        
        // A row can only hold up to 4 columns
        board.addRow(div1, div2, div3, div4);
        board.addRow(div5, div6);
        board.addRow(div7, div8).setComponentSpan(div7, 2);
        
        add(h1, board);
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
