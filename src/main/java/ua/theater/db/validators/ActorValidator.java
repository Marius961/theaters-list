package ua.theater.db.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class ActorValidator implements Validator {


    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "actorName", "field.empty");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }


}
