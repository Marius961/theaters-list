package ua.theater.db.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.theater.db.models.Theater;

@Component
public class TheaterValidator implements Validator {


    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tel", "field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "field.empty");

        if (target instanceof Theater) {
            Theater theater = (Theater) target;

            String test = theater.getTel().trim();
            try {
                int tel = Integer.parseInt(test);
            } catch (NumberFormatException e) {
                errors.rejectValue("tel", "tel.incorrect");
            }
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }


}
