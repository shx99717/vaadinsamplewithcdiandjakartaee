package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.foundation;

import com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.AppMainEntry;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Try Vaadin board")
@Route(value = "tryvaadinboard", layout = AppMainEntry.class)
public class TryVaadinBoardView extends VerticalLayout {

    public TryVaadinBoardView() {
        
        tryDummyBoard();

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

}
