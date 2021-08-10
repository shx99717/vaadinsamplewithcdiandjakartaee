package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.converter;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class LowerUpperCaseConverter implements Converter<String, String> {

    private static final long serialVersionUID = 2724514114538011083L;

    
    @Override
    public Result<String> convertToModel(String value, ValueContext context) {
        if(value == null) {
            value = "";
        }
        try {
            return Result.ok(value.toUpperCase());
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public String convertToPresentation(String value, ValueContext context) {
        if(value == null) {
            value = "";
        }
        return value.toLowerCase();
    }

}
