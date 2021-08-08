package com.shx99717.tobedeleted.com.vaadin.training.forms.solutions.ex2;

import java.time.LocalDate;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;

public class ProductViewer extends Composite<FormLayout> implements HasSize {
    private final Paragraph nameText = new Paragraph();
    private final Paragraph priceText = new Paragraph();
    private final Paragraph availableText = new Paragraph();

    private Binder<Product> binder = new Binder<>(Product.class);

    public ProductViewer(Product product) {
        final FormLayout layout = getContent();

        layout.addFormItem(nameText, "Name");
        layout.addFormItem(priceText, "Price");
        layout.addFormItem(availableText, "Available");

        binder.forField(new ReadOnlyHasValue<String>(name -> nameText.setText(name)))
                .bind(Product::getName, null);
        binder.forField(new ReadOnlyHasValue<Double>(price -> priceText.setText(String.valueOf(price))))
                .bind(Product::getPrice, null);
        binder.forField(new ReadOnlyHasValue<LocalDate>(available -> availableText.setText(String.valueOf(available))))
                .bind(Product::getAvailable, null);

        refresh(product);
    }

    void refresh(Product product) {
        binder.readBean(product);
    }
}
