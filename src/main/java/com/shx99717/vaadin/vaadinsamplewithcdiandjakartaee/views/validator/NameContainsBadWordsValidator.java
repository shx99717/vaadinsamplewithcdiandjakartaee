package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.validator;

import java.util.Set;

import org.apache.commons.compress.utils.Sets;

import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;

public class NameContainsBadWordsValidator implements Validator<String> {
    private static final long serialVersionUID = 1L;
    
    private Set<String> badNames = Sets.newHashSet("bad", "fuck", "suck", "damn", "stupid");
    private String errorMessage;
    
    public NameContainsBadWordsValidator(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    @Override
    public ValidationResult apply(String value, ValueContext context) {
        if(value == null) {
            return ValidationResult.ok();
        }
        value = value.toLowerCase();
        if(badNames.stream().anyMatch(value::contains)) {
            return ValidationResult.error(errorMessage);
        } else {
            return ValidationResult.ok();
        }
    }

}
