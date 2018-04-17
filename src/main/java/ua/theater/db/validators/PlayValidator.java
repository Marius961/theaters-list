package ua.theater.db.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PlayValidator implements Validator {

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productionDirector", "productionDirector");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.empty");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

}
