package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.component;

import java.time.LocalDateTime;

import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.timepicker.TimePicker;

public class DateTimePicker extends CustomField<LocalDateTime> {

    private static final long serialVersionUID = -759580416033503076L;
    
    private DatePicker datePicker = new DatePicker();
    private TimePicker timePicker = new TimePicker();
    
    public DateTimePicker() {
        HorizontalLayout container = new HorizontalLayout();
        container.add(datePicker, timePicker);
        add(container);
    }
    
    
    @Override
    protected LocalDateTime generateModelValue() {
        return LocalDateTime.of(datePicker.getValue(), timePicker.getValue());
    }

    @Override
    protected void setPresentationValue(LocalDateTime newPresentationValue) {
        datePicker.setValue(newPresentationValue.toLocalDate());
        timePicker.setValue(newPresentationValue.toLocalTime());
    }

}
